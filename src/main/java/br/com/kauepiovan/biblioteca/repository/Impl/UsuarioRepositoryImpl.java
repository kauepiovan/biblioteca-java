package br.com.kauepiovan.biblioteca.repository.impl;

import java.util.Optional;
import java.util.UUID;

import br.com.kauepiovan.biblioteca.domain.model.Usuario;
import br.com.kauepiovan.biblioteca.repository.InMemoryRepository;
import br.com.kauepiovan.biblioteca.repository.interfaces.UsuarioRepository;

public class UsuarioRepositoryImpl
        extends InMemoryRepository<Usuario, UUID>
        implements UsuarioRepository {

    @Override
    protected UUID getId(Usuario usuario) {
        return usuario.getId();
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return data.stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst();
    }
}