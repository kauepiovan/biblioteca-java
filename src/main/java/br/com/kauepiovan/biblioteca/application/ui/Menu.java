package br.com.kauepiovan.biblioteca.application.ui;

public class Menu {

    public Menu() {

    }

    public final void showStart() { 
        System.out.println("\n");
        System.out.println("--- CADASTROS ---");
        System.out.println("1 - Cadastrar Usuário.");
        System.out.println("2 - Cadastrar Bibliotecario.");
        System.out.println("3 - Cadastrar Livros.");
        System.out.println("");
        System.out.println("--- LISTAGENS ---");
        System.out.println("4 - Listar Usuarios.");
        System.out.println("5 - Listar Bibliotecarios.");
        System.out.println("6 - Listar Livros.");
        System.out.println("7 - Listar Emprestimos.");
        System.out.println("");
        System.out.println("--- FUNCIONALIDAES --");
        System.out.println("8 - Emprestar Livro.");
        System.out.println("9 - Devolver Livro.");
        System.out.println("");
        System.out.println("0 - Sair do Sistema.");
        System.out.println("");
        System.out.print("\nSelecione uma opcao: ");
    }



}
