package br.com.kauepiovan.biblioteca.application;

import java.util.Scanner;

import br.com.kauepiovan.biblioteca.application.ui.Menu;
import br.com.kauepiovan.biblioteca.controller.BibliotecarioController;
import br.com.kauepiovan.biblioteca.controller.EmprestimoController;
import br.com.kauepiovan.biblioteca.controller.LivroController;
import br.com.kauepiovan.biblioteca.controller.UsuarioController;
import br.com.kauepiovan.biblioteca.domain.enums.GeneroLiterario;
import br.com.kauepiovan.biblioteca.domain.enums.TipoUsuario;
import br.com.kauepiovan.biblioteca.domain.model.Usuario;
import br.com.kauepiovan.biblioteca.repository.impl.BibliotecarioRepositoryImpl;
import br.com.kauepiovan.biblioteca.repository.impl.EmprestimoRepositoryImpl;
import br.com.kauepiovan.biblioteca.repository.impl.LivroRepositoryImpl;
import br.com.kauepiovan.biblioteca.repository.impl.UsuarioRepositoryImpl;
import br.com.kauepiovan.biblioteca.domain.model.Livro;
import br.com.kauepiovan.biblioteca.services.EmprestimoService;
import br.com.kauepiovan.biblioteca.services.LivroService;
import br.com.kauepiovan.biblioteca.services.UsuarioService;
import br.com.kauepiovan.biblioteca.services.BibliotecarioService;
import br.com.kauepiovan.biblioteca.domain.model.Bibliotecario;

public class App {
    public static void main(String[] args) {
        var input = new Scanner(System.in);
        var menu = new Menu();

        var usuarioRepository = new UsuarioRepositoryImpl();
        var usuarioService = new UsuarioService(usuarioRepository);

        var livroRepository = new LivroRepositoryImpl();
        var livroService = new LivroService(livroRepository);
        
        var bibliotecarioRepository = new BibliotecarioRepositoryImpl();
        var bibliotecarioService = new BibliotecarioService(bibliotecarioRepository);
        
        var emprestimoRepository = new EmprestimoRepositoryImpl();
        var emprestimoService = new EmprestimoService(usuarioRepository, livroRepository, emprestimoRepository, bibliotecarioRepository);

        var usuarioController = new UsuarioController(usuarioService, input);
        var bibliotecarioController = new BibliotecarioController(bibliotecarioService, input);
        var livroController = new LivroController(livroService, input);
        var emprestimoController = new EmprestimoController(emprestimoService, input);

        // --- Dados mockados iniciais ---
        usuarioRepository.save(new Usuario("Alice Silva", "alice@example.com", TipoUsuario.COMUM));
        usuarioRepository.save(new Usuario("Bruno Costa", "bruno@example.com", TipoUsuario.PREMIUM));

        livroRepository.save(new Livro("O Misterio do Lago", "João Pereira", GeneroLiterario.SUSPENSE));
        livroRepository.save(new Livro("A Jornada do Heroi", "Mariana Souza", GeneroLiterario.ACAO_E_AVENTURA));
        livroRepository.save(new Livro("Reinos Fantasticos", "Carlos Lima", GeneroLiterario.FANTASIA));
        livroRepository.save(new Livro("Futuro Imaginado", "Ana Martins", GeneroLiterario.FICCAO_CIENTIFICA));

        bibliotecarioRepository.save(new Bibliotecario("Maria Admin", "maria@biblioteca.com"));
        bibliotecarioRepository.save(new Bibliotecario("Joao Admin", "joao@biblioteca.com"));
        // -------------------------------

        int opcao;
        do {
            menu.showStart();
            opcao = input.nextInt();
            input.nextLine();

            switch (opcao) {
                case 1 -> usuarioController.cadastrar();
                case 2 -> bibliotecarioController.cadastrar();
                case 3 -> livroController.cadastrar();
                case 4 -> usuarioController.listar();
                case 5 -> bibliotecarioController.listar();
                case 6 -> livroController.listar(); 
                case 7 -> emprestimoController.listar();
                case 8 -> emprestimoController.cadastrar();
                case 9 -> emprestimoController.concluir();
            }
        } while (opcao != 0);
        input.close();
    }
}
