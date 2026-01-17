package br.com.kauepiovan.biblioteca.controller;

import java.util.Scanner;

import br.com.kauepiovan.biblioteca.domain.enums.GeneroLiterario;
import br.com.kauepiovan.biblioteca.services.LivroService;

public class LivroController {
    private LivroService livroService;
    private Scanner input;

    public LivroController(LivroService livroService, Scanner input) {
        this.livroService = livroService;
        this.input = input;
    }

    public void cadastrar() {
        try {
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
            System.out.println(e.getMessage());
        }
    }

    public void listar() {
        try {
            livroService.listarLivros();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
