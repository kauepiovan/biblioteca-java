package br.com.kauepiovan.biblioteca.services;

import java.util.List;

import br.com.kauepiovan.biblioteca.domain.enums.TipoUsuario;
import br.com.kauepiovan.biblioteca.domain.model.Usuario;
import br.com.kauepiovan.biblioteca.exceptions.EmailAlreadyExistException;
import br.com.kauepiovan.biblioteca.repository.impl.UsuarioRepositoryImpl;

public class UsuarioService {

    private final UsuarioRepositoryImpl repository;

    public UsuarioService(UsuarioRepositoryImpl repository) {
        this.repository = repository;
    }

    private boolean emailExists(String email) {
        return repository.findByEmail(email).isPresent();
    }

    public void cadastrarUsuario(String nome, String email, TipoUsuario tipo) throws EmailAlreadyExistException {
        if (emailExists(email)) {
            throw new EmailAlreadyExistException();
        }
        var usuario = new Usuario(nome, email, tipo);
        repository.save(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return repository.findAll();
    }

}
