package br.com.kauepiovan.biblioteca.controller;

import java.util.Scanner;

import br.com.kauepiovan.biblioteca.services.BibliotecarioService;

public class BibliotecarioController {
    private BibliotecarioService bibliotecarioService;
    private Scanner input;

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
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void listar() {
        try {
            bibliotecarioService.listarBibliotecarios();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // TODO - remover

    // TODO - Atualizar
}
