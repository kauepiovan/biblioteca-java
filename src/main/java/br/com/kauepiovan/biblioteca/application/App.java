package br.com.kauepiovan.biblioteca.application;

import java.util.Scanner;
import java.util.UUID;

import br.com.kauepiovan.biblioteca.domain.enums.GeneroLiterario;
import br.com.kauepiovan.biblioteca.domain.enums.TipoUsuario;
import br.com.kauepiovan.biblioteca.domain.model.Menu;
import br.com.kauepiovan.biblioteca.domain.model.Usuario;
import br.com.kauepiovan.biblioteca.domain.model.Livro;
import br.com.kauepiovan.biblioteca.repository.EmprestimoRepository;
import br.com.kauepiovan.biblioteca.repository.LivroRepository;
import br.com.kauepiovan.biblioteca.repository.UsuarioRepository;
import br.com.kauepiovan.biblioteca.repository.BibliotecarioRepository;
import br.com.kauepiovan.biblioteca.services.EmprestimoService;
import br.com.kauepiovan.biblioteca.services.LivroService;
import br.com.kauepiovan.biblioteca.services.UsuarioService;
import br.com.kauepiovan.biblioteca.services.BibliotecarioService;
import br.com.kauepiovan.biblioteca.domain.model.Bibliotecario;

public class App {
    public static void main(String[] args) {

        var usuarioRepository = new UsuarioRepository();
        var usuarioService = new UsuarioService(usuarioRepository);

        var livroRepository = new LivroRepository();
        var livroService = new LivroService(livroRepository);
        
        var bibliotecarioRepository = new BibliotecarioRepository();
        var bibliotecarioService = new BibliotecarioService(bibliotecarioRepository);
        
        var emprestimoRepository = new EmprestimoRepository();
        var emprestimoService = new EmprestimoService(usuarioRepository, livroRepository, emprestimoRepository, bibliotecarioRepository);

        // --- Dados mockados iniciais ---
        usuarioRepository.addOne(new Usuario("Alice Silva", "alice@example.com", TipoUsuario.COMUM));
        usuarioRepository.addOne(new Usuario("Bruno Costa", "bruno@example.com", TipoUsuario.PREMIUM));

        livroRepository.addOne(new Livro("O Misterio do Lago", "João Pereira", GeneroLiterario.SUSPENSE));
        livroRepository.addOne(new Livro("A Jornada do Heroi", "Mariana Souza", GeneroLiterario.ACAO_E_AVENTURA));
        livroRepository.addOne(new Livro("Reinos Fantasticos", "Carlos Lima", GeneroLiterario.FANTASIA));
        livroRepository.addOne(new Livro("Futuro Imaginado", "Ana Martins", GeneroLiterario.FICCAO_CIENTIFICA));

        bibliotecarioRepository.addOne(new Bibliotecario("Maria Admin", "maria@biblioteca.com"));
        bibliotecarioRepository.addOne(new Bibliotecario("Joao Admin", "joao@biblioteca.com"));
        // -------------------------------

        var menu = new Menu();
        var input = new Scanner(System.in);

        int userInput = 1;
        while (userInput != 0) {
            menu.showStart();
            userInput = input.nextInt();

            switch (userInput) {
                case 1:
                    // Cadastrar usuario
                    try {
                        input.nextLine();

                        System.out.println("Digite o nome do usuario: ");
                        var nomeUsuario = input.next();

                        System.out.println("Digite o email do usuario: ");
                        var emailUsuario = input.next();

                        System.out.println("especifique o tipo { 1 - Usuario comum | 2 - Usuario premium }");
                        var tipoUsuario = input.nextInt() == 1 ? TipoUsuario.COMUM : TipoUsuario.PREMIUM;

                        usuarioService.cadastrarUsuario(nomeUsuario, emailUsuario, tipoUsuario);
                        System.out.println("Usuario: | " + nomeUsuario + " | " + emailUsuario + " | " + tipoUsuario
                                + " | " + "Criado com sucesso!");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 2:
                    // Cadastrar bibliotecario
                    try {
                        input.nextLine();
                        System.out.println("Digite o nome do bibliotecario: ");
                        var nomeBiblio = input.nextLine();
                        System.out.println("Digite o email do bibliotecario: ");
                        var emailBiblio = input.nextLine();
                        bibliotecarioService.cadastrarBibliotecario(nomeBiblio, emailBiblio);
                        System.out.println("Bibliotecario: | " + nomeBiblio + " | " + emailBiblio + " | Criado com sucesso!");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 3:
                    // Cadastrar livros
                    try {
                        input.nextLine();

                        System.out.println("Digite o titulo do livro: ");
                        var tituloLivro = input.nextLine();

                        System.out.println("Digite o autor do livro: ");
                        var autorLivro = input.nextLine();

                        System.out.println("Selecione o categoria do livro: ");
                        System.out.println(
                                "[ 1 - Suspense | 2 - Acao e Aventura | 3 - Fantasia | 4 - ficção científica ]");
                        var inputCategoria = input.nextInt();
                        var categoriaLivro = inputCategoria == 1 ? GeneroLiterario.SUSPENSE
                                : inputCategoria == 2 ? GeneroLiterario.ACAO_E_AVENTURA
                                        : inputCategoria == 3 ? GeneroLiterario.FANTASIA
                                                : GeneroLiterario.FICCAO_CIENTIFICA;

                        livroService.cadastrarLivro(tituloLivro, autorLivro, categoriaLivro);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 4:
                    // Listar usuarios
                    try {
                        usuarioService.listarUsuarios();
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 5:
                    // Listar bibliotecarios
                    try {
                        bibliotecarioService.listarBibliotecarios();
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 6:
                    // Listar livros
                    try {
                        livroService.listarLivros();
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 7:
                    // Listar emprestimos
                    try {
                        emprestimoService.listarEmprestimos();    
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;
                case 8:
                    // Emprestar livros
                    try {
                        input.nextLine();

                        System.out.println("Digite o email do usuario: ");
                        var emailUsuarioEmprestimo = input.nextLine();

                        System.out.println("Digite o titulo do livro: ");
                        var tituloLivroEmprestimo = input.nextLine();

                        System.out.println("Digite o email do bibliotecario responsavel: ");
                        var emailBibliotecario = input.nextLine();

                        emprestimoService.realizarEmprestimo(emailUsuarioEmprestimo, tituloLivroEmprestimo, emailBibliotecario);
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;
                case 9:
                    // Devolver livros
                    try {
                        input.nextLine();
                        System.out.println("Digite o id do emprestimo: ");
                        var id = input.nextLine();
                        emprestimoService.finalizarEmprestimo(UUID.fromString(id));
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;
                default:
                    break;
            }
        }
        input.close();

    }
}
