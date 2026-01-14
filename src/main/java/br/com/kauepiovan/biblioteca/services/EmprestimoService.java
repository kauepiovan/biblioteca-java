package br.com.kauepiovan.biblioteca.services;

import br.com.kauepiovan.biblioteca.repository.impl.BibliotecarioRepositoryImpl;
import br.com.kauepiovan.biblioteca.repository.impl.EmprestimoRepositoryImpl;
import br.com.kauepiovan.biblioteca.repository.impl.LivroRepositoryImpl;
import br.com.kauepiovan.biblioteca.repository.impl.UsuarioRepositoryImpl;
import br.com.kauepiovan.biblioteca.repository.interfaces.BibliotecarioRepository;
import br.com.kauepiovan.biblioteca.repository.interfaces.EmprestimoRepository;
import br.com.kauepiovan.biblioteca.repository.interfaces.LivroRepository;
import br.com.kauepiovan.biblioteca.repository.interfaces.UsuarioRepository;
import br.com.kauepiovan.biblioteca.domain.model.Usuario;

import java.util.UUID;

import br.com.kauepiovan.biblioteca.domain.enums.StatusLivro;
import br.com.kauepiovan.biblioteca.domain.model.Bibliotecario;
import br.com.kauepiovan.biblioteca.domain.model.Emprestimo;
import br.com.kauepiovan.biblioteca.domain.model.Livro;

public class EmprestimoService {

    private final UsuarioRepository usuarioRepository;
    private final LivroRepository livroRepository;
    private final EmprestimoRepository emprestimoRepository;
    private final BibliotecarioRepository bibliotecarioRepository;

    public EmprestimoService(
            UsuarioRepositoryImpl usuarioRepository,
            LivroRepositoryImpl livroRepository,
            EmprestimoRepositoryImpl emprestimoRepository,
            BibliotecarioRepositoryImpl bibliotecarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.livroRepository = livroRepository;
        this.emprestimoRepository = emprestimoRepository;
        this.bibliotecarioRepository = bibliotecarioRepository;
    }

    private Usuario findUsuario(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuario não encontrado: " + email));
    }

    private Livro findLivro(String titulo) {
        return livroRepository.findByTitulo(titulo)
                .orElseThrow(() -> new IllegalArgumentException("Livro não encontrado: " + titulo));
    }

    private Emprestimo findEmprestimo(UUID id) {
        return emprestimoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id fornecido não encontrado: " + id));
    }

    private Bibliotecario findBibliotecario(String email) {
        return bibliotecarioRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Bibliotecario não encontrado: " + email));
    }

    public void realizarEmprestimo(String email, String titulo, String emailBibliotecario) {
        Usuario usuario = findUsuario(email);
        Livro livro = findLivro(titulo);
        var bibliotecario = findBibliotecario(emailBibliotecario);

        if (usuario.getLivrosEmprestados().size() + 1 > usuario.getLimiteLivros()) {
            throw new IllegalStateException(
                    "Usuario ja atingiu o limite de emprestimos de " + usuario.getLimiteLivros());
        }

        if (livro.getStatus() == StatusLivro.EMPRESTADO) {
            throw new IllegalStateException("Livro ja esta sendo emprestado para outro usuario");
        }

        usuario.criarEmprestimo(livro);
        livro.setStatus(StatusLivro.EMPRESTADO);

        usuarioRepository.save(usuario);
        livroRepository.save(livro);

        var emprestimo = new Emprestimo(usuario, livro, bibliotecario);
        emprestimoRepository.save(emprestimo);
    }

    public void finalizarEmprestimo(UUID id) {
        Emprestimo emprestimo = findEmprestimo(id);
        Usuario usuario = emprestimo.getUsuario();
        Livro livro = emprestimo.getLivro();

        if (!usuario.getLivrosEmprestados().contains(livro)) {
            throw new IllegalStateException("O livro informado nao esta sendo emprestado para este usuario.");
        }

        var copyLivrosEmprestados = usuario.getLivrosEmprestados();
        copyLivrosEmprestados.remove(livro);
        usuario.setLivrosEmprestados(copyLivrosEmprestados);

        livro.setStatus(StatusLivro.DISPONIVEL);

        emprestimo.setFinalizado(true);

        usuarioRepository.save(usuario);
        livroRepository.save(livro);
        emprestimoRepository.save(emprestimo);
    }

    public void listarEmprestimos() {
        var emprestimos = emprestimoRepository.findAll();
        System.out.println("\n=== Lista de Empréstimos ===");
        if (emprestimos.isEmpty()) {
            System.out.println("Nenhum empréstimo registrado.");
            return;
        }
        for (var emprestimo : emprestimos) {
            System.out.println("- Id: " + emprestimo.getId());
            var usuario = emprestimo.getUsuario();
            System.out.println("  Usuário: " + usuario.getNome() + " | Email: " + usuario.getEmail());
            var livro = emprestimo.getLivro();
            System.out.println("  Livro: " + livro.getTitulo() + " | Autor: " + livro.getAutor() + " | Status: "
                + livro.getStatus());
            var bibliotecario = emprestimo.getBibliotecario();
            System.out.println("  Bibliotecario: " + bibliotecario.getNome() + " | Email: " + bibliotecario.getEmail());
            System.out.println("  Data criação: " + emprestimo.getDataCriacao() + " | Vencimento: "
                    + emprestimo.getDataVencimento() + " | Finalizado: " + emprestimo.getFinalizado());
        }
    }

}
