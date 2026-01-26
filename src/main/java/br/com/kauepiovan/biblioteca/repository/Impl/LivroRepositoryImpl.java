package br.com.kauepiovan.biblioteca.repository.impl;

import java.util.Optional;
import java.util.UUID;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import br.com.kauepiovan.biblioteca.domain.model.Livro;
import br.com.kauepiovan.biblioteca.repository.BaseRepository;
import br.com.kauepiovan.biblioteca.repository.interfaces.LivroRepository;

public class LivroRepositoryImpl
        extends BaseRepository<Livro, UUID>
        implements LivroRepository {

    public LivroRepositoryImpl(EntityManager entityManager) {
        super(entityManager, Livro.class);
    }

    @Override
    public Optional<Livro> findByTitulo(String titulo) {
        try {
            return Optional.of(entityManager.createQuery("SELECT l FROM Livro l WHERE l.titulo = :titulo", Livro.class)
                    .setParameter("titulo", titulo)
                    .getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}