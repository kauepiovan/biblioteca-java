package br.com.kauepiovan.biblioteca.repository.impl;

import java.util.UUID;

import jakarta.persistence.EntityManager;

import br.com.kauepiovan.biblioteca.domain.model.Emprestimo;
import br.com.kauepiovan.biblioteca.repository.BaseRepository;
import br.com.kauepiovan.biblioteca.repository.interfaces.EmprestimoRepository;

public class EmprestimoRepositoryImpl
        extends BaseRepository<Emprestimo, UUID>
        implements EmprestimoRepository {

    public EmprestimoRepositoryImpl(EntityManager entityManager) {
        super(entityManager, Emprestimo.class);
    }

}
