package com.kauepiovan.biblioteca.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.kauepiovan.biblioteca.domain.enums.GeneroLiterario;
import br.com.kauepiovan.biblioteca.domain.enums.StatusLivro;
import br.com.kauepiovan.biblioteca.domain.enums.TipoUsuario;
import br.com.kauepiovan.biblioteca.domain.model.Bibliotecario;
import br.com.kauepiovan.biblioteca.domain.model.Emprestimo;
import br.com.kauepiovan.biblioteca.domain.model.Livro;
import br.com.kauepiovan.biblioteca.domain.model.Usuario;
import br.com.kauepiovan.biblioteca.exceptions.BookAlreadyBorrowedException;
import br.com.kauepiovan.biblioteca.exceptions.BookNotFoundException;
import br.com.kauepiovan.biblioteca.exceptions.IdNotFoundException;
import br.com.kauepiovan.biblioteca.exceptions.LibrarianNotFoundException;
import br.com.kauepiovan.biblioteca.exceptions.LimitBookReachedException;
import br.com.kauepiovan.biblioteca.exceptions.UserNotFoundException;
import br.com.kauepiovan.biblioteca.repository.impl.BibliotecarioRepositoryImpl;
import br.com.kauepiovan.biblioteca.repository.impl.EmprestimoRepositoryImpl;
import br.com.kauepiovan.biblioteca.repository.impl.LivroRepositoryImpl;
import br.com.kauepiovan.biblioteca.repository.impl.UsuarioRepositoryImpl;
import br.com.kauepiovan.biblioteca.services.BibliotecarioService;
import br.com.kauepiovan.biblioteca.services.EmprestimoService;
import br.com.kauepiovan.biblioteca.services.LivroService;
import br.com.kauepiovan.biblioteca.services.UsuarioService;

public class EmprestimoServiceTest {
    private UsuarioRepositoryImpl usuarioRepository;
    private LivroRepositoryImpl livroRepository;
    private EmprestimoRepositoryImpl emprestimoRepository;
    private BibliotecarioRepositoryImpl bibliotecarioRepository;
    private UsuarioService usuarioService;
    private BibliotecarioService bibliotecarioService;
    private LivroService livroService;
    private EmprestimoService emprestimoService;
    private jakarta.persistence.EntityManagerFactory emf;
    private jakarta.persistence.EntityManager em;

    @BeforeEach
    void setup() {
        java.util.Map<String, String> properties = new java.util.HashMap<>();
        properties.put("javax.persistence.jdbc.url", "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
        properties.put("hibernate.hbm2ddl.auto", "create-drop");

        emf = jakarta.persistence.Persistence.createEntityManagerFactory("biblioteca-pu", properties);
        em = emf.createEntityManager();

        usuarioRepository = new UsuarioRepositoryImpl(em);
        livroRepository = new LivroRepositoryImpl(em);
        emprestimoRepository = new EmprestimoRepositoryImpl(em);
        bibliotecarioRepository = new BibliotecarioRepositoryImpl(em);

        usuarioService = new UsuarioService(usuarioRepository);
        bibliotecarioService = new BibliotecarioService(bibliotecarioRepository);
        livroService = new LivroService(livroRepository);
        emprestimoService = new EmprestimoService(usuarioRepository, livroRepository, emprestimoRepository,
                bibliotecarioRepository);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        if (em != null)
            em.close();
        if (emf != null)
            emf.close();
    }

    @Test
    @DisplayName("Deve cadastrar um emprestimo no repository")
    void deveCadastrarUmEmprestimo() throws Exception {

        usuarioService.cadastrarUsuario("usuario", "usuario@email.com", TipoUsuario.COMUM);
        bibliotecarioService.cadastrarBibliotecario("bibliotecario", "bibli@email.com");
        livroService.cadastrarLivro("titulo do livro", "autor", GeneroLiterario.ACAO_E_AVENTURA);

        Usuario usuarioEmprestimo = usuarioService.listarUsuarios().getFirst();
        Bibliotecario bibliotecarioEmprestimo = bibliotecarioService.listarBibliotecarios().getFirst();
        Livro livroEmprestimo = livroService.listarLivros().getFirst();

        var date = LocalDate.now();
        emprestimoService.realizarEmprestimo("usuario@email.com", "titulo do livro", "bibli@email.com");
        Emprestimo emprestimo = emprestimoService.listarEmprestimos().getLast();

        assertEquals(1, emprestimoRepository.findAll().size());
        assertEquals(usuarioEmprestimo, emprestimo.getUsuario());
        assertEquals(bibliotecarioEmprestimo, emprestimo.getBibliotecario());
        assertEquals(livroEmprestimo, emprestimo.getLivro());
        assertEquals(StatusLivro.EMPRESTADO, livroEmprestimo.getStatus());
        assertTrue(usuarioEmprestimo.getLimiteLivros() <= 3);
        assertTrue(usuarioEmprestimo.getLivrosEmprestados().contains(livroEmprestimo));
        assertTrue(date.equals(emprestimo.getDataCriacao()));
        assertTrue(date.plusDays(7).equals(emprestimo.getDataVencimento()));

    }

    @Test
    @DisplayName("Deve lancar um exception se o livro ja esta sendo emprestado")
    void deveLancarExceptionSeLivroEstaSendoEmprestado() throws Exception {
        usuarioService.cadastrarUsuario("usuario1", "usuario1@email.com", TipoUsuario.COMUM);
        usuarioService.cadastrarUsuario("usuario2", "usuario2@email.com", TipoUsuario.PREMIUM);

        livroService.cadastrarLivro("titulo do livro", "autor", GeneroLiterario.ACAO_E_AVENTURA);

        bibliotecarioService.cadastrarBibliotecario("bibliotecario", "bibli@email.com");

        emprestimoService.realizarEmprestimo("usuario1@email.com", "titulo do livro", "bibli@email.com");

        assertThrows(BookAlreadyBorrowedException.class, () -> {
            emprestimoService.realizarEmprestimo("usuario2@email.com", "titulo do livro", "bibli@email.com");
        });

    }

    @Test
    @DisplayName("Deve lancar uma exception se o usuario ultrapassar o limite de emprestimos de seu plano")
    void deveLancarExceptionSeUsuarioUltrapassarLimiteDeEmprestimos() throws Exception {
        usuarioService.cadastrarUsuario("usuario1", "usuario1@email.com", TipoUsuario.COMUM);
        usuarioService.cadastrarUsuario("usuario2", "usuario2@email.com", TipoUsuario.PREMIUM);

        bibliotecarioService.cadastrarBibliotecario("bibliotecario", "bibli@email.com");

        livroService.cadastrarLivro("titulo do livro1", "autor", GeneroLiterario.ACAO_E_AVENTURA);
        livroService.cadastrarLivro("titulo do livro2", "autor", GeneroLiterario.ACAO_E_AVENTURA);
        livroService.cadastrarLivro("titulo do livro3", "autor", GeneroLiterario.ACAO_E_AVENTURA);
        livroService.cadastrarLivro("titulo do livro4", "autor", GeneroLiterario.ACAO_E_AVENTURA);
        livroService.cadastrarLivro("titulo do livro5", "autor", GeneroLiterario.ACAO_E_AVENTURA);
        livroService.cadastrarLivro("titulo do livro6", "autor", GeneroLiterario.ACAO_E_AVENTURA);
        livroService.cadastrarLivro("titulo do livro7", "autor", GeneroLiterario.ACAO_E_AVENTURA);
        livroService.cadastrarLivro("titulo do livro8", "autor", GeneroLiterario.ACAO_E_AVENTURA);
        livroService.cadastrarLivro("titulo do livro9", "autor", GeneroLiterario.ACAO_E_AVENTURA);
        livroService.cadastrarLivro("titulo do livro10", "autor", GeneroLiterario.ACAO_E_AVENTURA);

        emprestimoService.realizarEmprestimo("usuario1@email.com", "titulo do livro1", "bibli@email.com");
        emprestimoService.realizarEmprestimo("usuario1@email.com", "titulo do livro2", "bibli@email.com");
        emprestimoService.realizarEmprestimo("usuario1@email.com", "titulo do livro3", "bibli@email.com");

        emprestimoService.realizarEmprestimo("usuario2@email.com", "titulo do livro4", "bibli@email.com");
        emprestimoService.realizarEmprestimo("usuario2@email.com", "titulo do livro5", "bibli@email.com");
        emprestimoService.realizarEmprestimo("usuario2@email.com", "titulo do livro6", "bibli@email.com");
        emprestimoService.realizarEmprestimo("usuario2@email.com", "titulo do livro7", "bibli@email.com");
        emprestimoService.realizarEmprestimo("usuario2@email.com", "titulo do livro8", "bibli@email.com");

        assertThrows(LimitBookReachedException.class, () -> {
            emprestimoService.realizarEmprestimo("usuario1@email.com", "titulo do livro9", "bibli@email.com");
        });

        assertThrows(LimitBookReachedException.class, () -> {
            emprestimoService.realizarEmprestimo("usuario2@email.com", "titulo do livro10", "bibli@email.com");
        });

    }

    @Test
    @DisplayName("Deve lancar uma exception se o usuario nao for encontrado")
    void deveLancarExceptionSeUsuarioNaoEncontrado() throws Exception {
        usuarioService.cadastrarUsuario("usuario1", "usuario1@email.com", TipoUsuario.COMUM);

        bibliotecarioService.cadastrarBibliotecario("bibliotecario", "bibli@email.com");

        livroService.cadastrarLivro("titulo do livro1", "autor", GeneroLiterario.ACAO_E_AVENTURA);

        assertThrows(UserNotFoundException.class, () -> {
            emprestimoService.realizarEmprestimo("usuario2@email.com", "titulo do livro1", "bibli@email.com");
        });
    }

    @Test
    @DisplayName("Deve lancar uma exception se o usuario nao for encontrado")
    void deveLancarExceptionSeLivroNaoEncontrado() throws Exception {
        usuarioService.cadastrarUsuario("usuario", "usuario1@email.com", TipoUsuario.COMUM);

        bibliotecarioService.cadastrarBibliotecario("bibliotecario", "bibli@email.com");

        livroService.cadastrarLivro("titulo do livro1", "autor", GeneroLiterario.ACAO_E_AVENTURA);

        assertThrows(BookNotFoundException.class, () -> {
            emprestimoService.realizarEmprestimo("usuario1@email.com", "titulo do livro2", "bibli@email.com");
        });
    }

    @Test
    @DisplayName("Deve lancar uma exception se o usuario nao for encontrado")
    void deveLancarExceptionSeBibliotecarioNaoEncontrado() throws Exception {
        usuarioService.cadastrarUsuario("usuario", "usuario1@email.com", TipoUsuario.COMUM);

        bibliotecarioService.cadastrarBibliotecario("bibliotecario", "bibli@email.com");

        livroService.cadastrarLivro("titulo do livro1", "autor", GeneroLiterario.ACAO_E_AVENTURA);

        assertThrows(LibrarianNotFoundException.class, () -> {
            emprestimoService.realizarEmprestimo("usuario1@email.com", "titulo do livro1", "b@email.com");
        });
    }

    @Test
    @DisplayName("")
    void deveFinalizarEmprestimo() throws Exception {
        usuarioService.cadastrarUsuario("usuario", "usuario@email.com", TipoUsuario.COMUM);
        bibliotecarioService.cadastrarBibliotecario("bibliotecario", "bibli@email.com");
        livroService.cadastrarLivro("titulo do livro", "autor", GeneroLiterario.ACAO_E_AVENTURA);

        Usuario usuarioEmprestimo = usuarioService.listarUsuarios().getFirst();
        Livro livroEmprestimo = livroService.listarLivros().getFirst();

        emprestimoService.realizarEmprestimo("usuario@email.com", "titulo do livro", "bibli@email.com");

        Emprestimo emprestimo = emprestimoService.listarEmprestimos().getFirst();

        emprestimoService.finalizarEmprestimo(emprestimo.getId());

        assertFalse(usuarioEmprestimo.getLivrosEmprestados().contains(livroEmprestimo));
        assertTrue(livroEmprestimo.getStatus().equals(StatusLivro.DISPONIVEL));
        assertTrue(emprestimo.getFinalizado());

    }

    @Test
    @DisplayName("Deve lancar uma exception se o id do emprestimo nao ser econtrado")
    void deveLancarExceptionSeEmprestimoNaoEncontrado() throws Exception {
        usuarioService.cadastrarUsuario("usuario", "usuario@email.com", TipoUsuario.COMUM);
        bibliotecarioService.cadastrarBibliotecario("bibliotecario", "bibli@email.com");
        livroService.cadastrarLivro("titulo do livro", "autor", GeneroLiterario.ACAO_E_AVENTURA);

        emprestimoService.realizarEmprestimo("usuario@email.com", "titulo do livro", "bibli@email.com");

        assertThrows(IdNotFoundException.class, () -> {
            emprestimoService.finalizarEmprestimo(UUID.randomUUID());
        });

    }

    @Test
    @DisplayName("")
    void deveListarTodosOsEmprestimos() throws Exception {
        usuarioService.cadastrarUsuario("usuario1", "usuario1@email.com", TipoUsuario.COMUM);
        usuarioService.cadastrarUsuario("usuario2", "usuario2@email.com", TipoUsuario.PREMIUM);

        bibliotecarioService.cadastrarBibliotecario("bibliotecario", "bibli@email.com");

        livroService.cadastrarLivro("titulo do livro1", "autor", GeneroLiterario.ACAO_E_AVENTURA);
        livroService.cadastrarLivro("titulo do livro2", "autor", GeneroLiterario.ACAO_E_AVENTURA);
        livroService.cadastrarLivro("titulo do livro3", "autor", GeneroLiterario.ACAO_E_AVENTURA);
        livroService.cadastrarLivro("titulo do livro4", "autor", GeneroLiterario.ACAO_E_AVENTURA);
        livroService.cadastrarLivro("titulo do livro5", "autor", GeneroLiterario.ACAO_E_AVENTURA);
        livroService.cadastrarLivro("titulo do livro6", "autor", GeneroLiterario.ACAO_E_AVENTURA);
        livroService.cadastrarLivro("titulo do livro7", "autor", GeneroLiterario.ACAO_E_AVENTURA);
        livroService.cadastrarLivro("titulo do livro8", "autor", GeneroLiterario.ACAO_E_AVENTURA);

        emprestimoService.realizarEmprestimo("usuario1@email.com", "titulo do livro1", "bibli@email.com");
        emprestimoService.realizarEmprestimo("usuario1@email.com", "titulo do livro2", "bibli@email.com");
        emprestimoService.realizarEmprestimo("usuario1@email.com", "titulo do livro3", "bibli@email.com");
        emprestimoService.realizarEmprestimo("usuario2@email.com", "titulo do livro4", "bibli@email.com");
        emprestimoService.realizarEmprestimo("usuario2@email.com", "titulo do livro5", "bibli@email.com");
        emprestimoService.realizarEmprestimo("usuario2@email.com", "titulo do livro6", "bibli@email.com");
        emprestimoService.realizarEmprestimo("usuario2@email.com", "titulo do livro7", "bibli@email.com");
        emprestimoService.realizarEmprestimo("usuario2@email.com", "titulo do livro8", "bibli@email.com");

        assertEquals(8, emprestimoService.listarEmprestimos().size());
    }

}
