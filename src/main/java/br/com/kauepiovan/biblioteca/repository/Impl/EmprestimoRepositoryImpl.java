package br.com.kauepiovan.biblioteca.repository.impl;

import java.util.UUID;

import br.com.kauepiovan.biblioteca.domain.model.Emprestimo;
import br.com.kauepiovan.biblioteca.repository.InMemoryRepository;
import br.com.kauepiovan.biblioteca.repository.interfaces.EmprestimoRepository;

public class EmprestimoRepositoryImpl 
        extends InMemoryRepository<Emprestimo, UUID>
        implements EmprestimoRepository {
    
    @Override
    protected UUID getId(Emprestimo emprestimo) {
        return emprestimo.getId();
    }
    
}
