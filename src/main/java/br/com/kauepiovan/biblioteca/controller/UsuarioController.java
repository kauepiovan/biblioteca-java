package br.com.kauepiovan.biblioteca.controller;

import java.util.Scanner;

import br.com.kauepiovan.biblioteca.domain.enums.TipoUsuario;
import br.com.kauepiovan.biblioteca.services.UsuarioService;

public class UsuarioController {
    private final UsuarioService usuarioService;
    private final Scanner input;

    public UsuarioController(UsuarioService usuarioService, Scanner input) {
        this.usuarioService = usuarioService;
        this.input = input;
    }

    // cadastrar
    public void cadastrar() {
        try {
            System.out.println("Digite o nome do usuario: ");
            var nomeUsuario = input.nextLine();
            System.out.println("Digite o email do usuario: ");
            var emailUsuario = input.nextLine();
            System.out.println("especifique o tipo { 1 - Usuario comum | 2 - Usuario premium }");
            var tipoLine = input.nextLine().trim();
            TipoUsuario tipoUsuario;
            try {
                tipoUsuario = Integer.parseInt(tipoLine) == 1 ? TipoUsuario.COMUM : TipoUsuario.PREMIUM;
            } catch (NumberFormatException e) {
                tipoUsuario = TipoUsuario.COMUM;
            }
            usuarioService.cadastrarUsuario(nomeUsuario, emailUsuario, tipoUsuario);
            System.out.println("Usuario: | " + nomeUsuario + " | " + emailUsuario + " | " + tipoUsuario
                    + " | " + "Criado com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // listar
    public void listar() {
        try {
            var usuarios = usuarioService.listarUsuarios();
            if (usuarios.isEmpty()) {
                System.out.println("Nenhum usuário cadastrado.");
                return;
            }
            System.out.println("--- Lista de Usuários ---");
            int i = 1;
            for (var u : usuarios) {
                System.out.println(String.format("%d) %s - %s | Tipo: %s | Limite: %d | Emprestados: %d",
                        i++, u.getNome(), u.getEmail(), u.getTipo(), u.getLimiteLivros(), u.getLivrosEmprestados().size()));
                if (!u.getLivrosEmprestados().isEmpty()) {
                    System.out.println("   Livros emprestados:");
                    for (var l : u.getLivrosEmprestados()) {
                        System.out.println("    - " + l.getTitulo() + " (" + l.getAutor() + ")");
                    }
                }
            }
            System.out.println("-------------------------");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // TODO - remover

    // TODO - atualizar
}
