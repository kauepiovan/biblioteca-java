package br.com.kauepiovan.biblioteca.domain.model;

public class Menu {

    public Menu() {

    }

    public final void showStart() { 
        System.out.println("\n\n1 - Cadastrar Usuário.");
        System.out.println("2 - Cadastrar Livros.");
        System.out.println("3 - Listar Usuarios.");
        System.out.println("4 - Listar Livros.");
        System.out.println("5 - Emprestar livro.");
        System.out.println("6 - Devolver livro.");
        System.out.println("7 - Listar Emprestimos");
        System.out.println("0 - Sair.");
    }



}
