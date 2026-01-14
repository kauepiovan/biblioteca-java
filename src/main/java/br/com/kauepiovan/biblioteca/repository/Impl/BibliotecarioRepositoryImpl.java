package br.com.kauepiovan.biblioteca.repository.impl;

import java.util.Optional;
import java.util.UUID;

import br.com.kauepiovan.biblioteca.domain.model.Bibliotecario;
import br.com.kauepiovan.biblioteca.repository.InMemoryRepository;
import br.com.kauepiovan.biblioteca.repository.interfaces.BibliotecarioRepository;

public class BibliotecarioRepositoryImpl
        extends InMemoryRepository<Bibliotecario, UUID>
        implements BibliotecarioRepository {

    @Override
    protected UUID getId(Bibliotecario bibliotecario) {
        return bibliotecario.getId();
    }

    @Override
    public Optional<Bibliotecario> findByEmail(String email) {
        return data.stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst();
    }
}