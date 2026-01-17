package br.com.kauepiovan.biblioteca.controller;

import java.util.Scanner;

import br.com.kauepiovan.biblioteca.services.BibliotecarioService;

public class BibliotecarioController {
    private final BibliotecarioService bibliotecarioService;
    private final Scanner input;

    public BibliotecarioController(BibliotecarioService bibliotecarioService, Scanner input) {
        this.bibliotecarioService = bibliotecarioService;
        this.input = input;
    }

    public void cadastrar() {
        try {
            System.out.println("Digite o nome do bibliotecario: ");
            var nomeBibliotecario = input.nextLine();
            System.out.println("Digite o email do bibliotecario: ");
            var emailBibliotecario = input.nextLine();
            bibliotecarioService.cadastrarBibliotecario(nomeBibliotecario, emailBibliotecario);
            System.out.println(
                    "Bibliotecario: | " + nomeBibliotecario + " | " + emailBibliotecario + " | Criado com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void listar() {
        try {
            var biblio = bibliotecarioService.listarBibliotecarios();
            if (biblio.isEmpty()) {
                System.out.println("Nenhum bibliotecario cadastrado.");
                return;
            }
            System.out.println("--- Lista de Bibliotecários ---");
            int i = 1;
            for (var b : biblio) {
                System.out.println(String.format("%d) %s - %s | id=%s", i++, b.getNome(), b.getEmail(), b.getId()));
            }
            System.out.println("-------------------------------");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // TODO - remover

    // TODO - Atualizar
}
