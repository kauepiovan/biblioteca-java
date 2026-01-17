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
            var nomeUsuario = input.next();
            System.out.println("Digite o email do usuario: ");
            var emailUsuario = input.next();
            System.out.println("especifique o tipo { 1 - Usuario comum | 2 - Usuario premium }");
            var tipoUsuario = input.nextInt() == 1 ? TipoUsuario.COMUM : TipoUsuario.PREMIUM;
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
            usuarioService.listarUsuarios();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // TODO - remover

    // TODO - atualizar
}
