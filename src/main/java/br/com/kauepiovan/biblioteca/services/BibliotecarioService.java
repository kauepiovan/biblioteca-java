package br.com.kauepiovan.biblioteca.services;

import java.util.List;

import br.com.kauepiovan.biblioteca.domain.model.Bibliotecario;
import br.com.kauepiovan.biblioteca.exceptions.EmailAlreadyExistException;
import br.com.kauepiovan.biblioteca.repository.interfaces.BibliotecarioRepository;

public class BibliotecarioService {

    private final BibliotecarioRepository repository;

    public BibliotecarioService(BibliotecarioRepository repository) {
        this.repository = repository;
    }

    private boolean emailExists(String email) {
        return repository.findByEmail(email).isPresent();
    }

    public void cadastrarBibliotecario(String nome, String email) throws EmailAlreadyExistException {
        if (emailExists(email)) {
            throw new EmailAlreadyExistException();
        }
        var b = new Bibliotecario(nome, email);
        repository.save(b);
    }

    public List<Bibliotecario> listarBibliotecarios() {
        return repository.findAll();
    }

}
