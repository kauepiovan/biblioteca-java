package br.com.kauepiovan.biblioteca.controller;

import java.util.Scanner;
import java.util.UUID;

import br.com.kauepiovan.biblioteca.services.EmprestimoService;

public class EmprestimoController {
    private EmprestimoService emprestimoService;
    private Scanner input;

    public EmprestimoController(EmprestimoService emprestimoService, Scanner input) {
        this.emprestimoService = emprestimoService;
        this.input = input;
    }

    public void cadastrar() {
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
    }

    public void listar() {
        try {
            emprestimoService.listarEmprestimos();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void concluir() {
        try {
            input.nextLine();
            System.out.println("Digite o id do emprestimo: ");
            var id = input.nextLine();
            emprestimoService.finalizarEmprestimo(UUID.fromString(id));
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

}
