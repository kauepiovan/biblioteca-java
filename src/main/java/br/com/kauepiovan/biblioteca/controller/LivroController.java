package br.com.kauepiovan.biblioteca.controller;

import java.util.Scanner;

import br.com.kauepiovan.biblioteca.domain.enums.GeneroLiterario;
import br.com.kauepiovan.biblioteca.services.LivroService;

public class LivroController {
    private final LivroService livroService;
    private final Scanner input;

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
                var categoriaLine = input.nextLine().trim();
                int inputCategoria;
                try {
                inputCategoria = Integer.parseInt(categoriaLine);
                } catch (NumberFormatException e) {
                inputCategoria = 4; // default
                }

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
            var livros = livroService.listarLivros();
            if (livros.isEmpty()) {
                System.out.println("Nenhum livro cadastrado.");
                return;
            }
            System.out.println("--- Lista de Livros ---");
            int i = 1;
            for (var l : livros) {
                System.out.println(String.format("%d) %s - %s | Categoria: %s | Status: %s", i++, l.getTitulo(), l.getAutor(), l.getCategoria(), l.getStatus()));
            }
            System.out.println("----------------------");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
