package br.com.kauepiovan.biblioteca.services;

import br.com.kauepiovan.biblioteca.domain.model.Bibliotecario;
import br.com.kauepiovan.biblioteca.repository.BibliotecarioRepository;

public class BibliotecarioService {

    private final BibliotecarioRepository repository;

    public BibliotecarioService(BibliotecarioRepository repository) {
        this.repository = repository;
    }

    private boolean emailExists(String email) {
        return !repository.getOne(email).isEmpty();
    }

    public void cadastrarBibliotecario(String nome, String email) {
        if (emailExists(email)) {
            throw new IllegalStateException("Email informado ja existe cadastro: " + email);
        }
        var b = new Bibliotecario(nome, email);
        repository.addOne(b);
    }

    public void listarBibliotecarios() {
        var items = repository.getAll();
        System.out.println("\n=== Lista de Bibliotecários ===");
        if (items.isEmpty()) {
            System.out.println("Nenhum bibliotecario cadastrado.");
            return;
        }
        for (var b : items) {
            System.out.println("- Nome: " + b.getNome() + " | Email: " + b.getEmail() + " | Matricula: " + b.getMatricula());
        }
    }

}
