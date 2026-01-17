package br.com.kauepiovan.biblioteca.services;

import br.com.kauepiovan.biblioteca.domain.model.Bibliotecario;
import br.com.kauepiovan.biblioteca.exceptions.EmailAlreadyExistException;
import br.com.kauepiovan.biblioteca.repository.interfaces.BibliotecarioRepository;

public class BibliotecarioService {

    private final BibliotecarioRepository repository;

    public BibliotecarioService(BibliotecarioRepository repository) {
        this.repository = repository;
    }

    private boolean emailExists(String email) {
        return repository.findByEmail(email).isPresent() ? true : false;
    }

    public void cadastrarBibliotecario(String nome, String email) throws EmailAlreadyExistException {
        if (emailExists(email)) {
            throw new EmailAlreadyExistException();
        }
        var b = new Bibliotecario(nome, email);
        repository.save(b);
    }

    public void listarBibliotecarios() {
        var items = repository.findAll();
        System.out.println("\n=== Lista de Bibliotecários ===");
        if (items.isEmpty()) {
            System.out.println("Nenhum bibliotecario cadastrado.");
            return;
        }
        for (var b : items) {
            System.out.println("- Nome: " + b.getNome() + " | Email: " + b.getEmail() + " | Matricula: " + b.getId());
        }
    }

}
