package br.com.kauepiovan.biblioteca.repository.impl;

import java.util.Optional;
import java.util.UUID;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import br.com.kauepiovan.biblioteca.domain.model.Bibliotecario;
import br.com.kauepiovan.biblioteca.repository.BaseRepository;
import br.com.kauepiovan.biblioteca.repository.interfaces.BibliotecarioRepository;

public class BibliotecarioRepositoryImpl
        extends BaseRepository<Bibliotecario, UUID>
        implements BibliotecarioRepository {

    public BibliotecarioRepositoryImpl(EntityManager entityManager) {
        super(entityManager, Bibliotecario.class);
    }

    @Override
    public Optional<Bibliotecario> findByEmail(String email) {
        try {
            return Optional.of(entityManager
                    .createQuery("SELECT b FROM Bibliotecario b WHERE b.email = :email", Bibliotecario.class)
                    .setParameter("email", email)
                    .getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}