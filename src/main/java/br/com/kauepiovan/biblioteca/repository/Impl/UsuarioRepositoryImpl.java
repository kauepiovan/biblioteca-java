package br.com.kauepiovan.biblioteca.repository.impl;

import java.util.Optional;
import java.util.UUID;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import br.com.kauepiovan.biblioteca.domain.model.Usuario;
import br.com.kauepiovan.biblioteca.repository.BaseRepository;
import br.com.kauepiovan.biblioteca.repository.interfaces.UsuarioRepository;

public class UsuarioRepositoryImpl
        extends BaseRepository<Usuario, UUID>
        implements UsuarioRepository {

    public UsuarioRepositoryImpl(EntityManager entityManager) {
        super(entityManager, Usuario.class);
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        try {
            return Optional
                    .of(entityManager.createQuery("SELECT u FROM Usuario u WHERE u.email = :email", Usuario.class)
                            .setParameter("email", email)
                            .getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}