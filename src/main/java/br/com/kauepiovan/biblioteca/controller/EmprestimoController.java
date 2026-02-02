package br.com.kauepiovan.biblioteca.controller;

import java.util.Scanner;
import java.util.UUID;

import br.com.kauepiovan.biblioteca.services.EmprestimoService;

public class EmprestimoController {
    private final EmprestimoService emprestimoService;
    private final Scanner input;

    public EmprestimoController(EmprestimoService emprestimoService, Scanner input) {
        this.emprestimoService = emprestimoService;
        this.input = input;
    }

    public void cadastrar() {
        try {
            System.out.println("Digite o email do usuario: ");
            var emailUsuarioEmprestimo = input.nextLine();
            System.out.println("Digite o titulo do livro: ");
            var tituloLivroEmprestimo = input.nextLine();
            System.out.println("Digite o email do bibliotecario responsavel: ");
            var emailBibliotecario = input.nextLine();
            emprestimoService.realizarEmprestimo(emailUsuarioEmprestimo, tituloLivroEmprestimo, emailBibliotecario);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void listar() {
        try {
            var emprestimos = emprestimoService.listarEmprestimos();
            if (emprestimos.isEmpty()) {
                System.out.println("Nenhum empréstimo registrado.");
                return;
            }
            System.out.println("--- Lista de Empréstimos ---");
            int i = 1;
            for (var e : emprestimos) {
                var usuario = e.getUsuario();
                var livro = e.getLivro();
                var bibliotecario = e.getBibliotecario();
                System.out.println(String.format("%d) id=%s | usuário=%s (%s) | livro=%s | bibliotecario=%s (%s)",
                        i++, e.getId(), usuario.getNome(), usuario.getEmail(), livro.getTitulo(), bibliotecario.getNome(), bibliotecario.getEmail()));
                System.out.println(String.format("    criado=%s | vence=%s | finalizado=%s", e.getDataCriacao(), e.getDataVencimento(), e.getFinalizado()));
            }
            System.out.println("-----------------------------");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void concluir() {
        try {
            
            System.out.println("Digite o id do emprestimo: ");
            var id = input.nextLine();
            emprestimoService.finalizarEmprestimo(UUID.fromString(id));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
