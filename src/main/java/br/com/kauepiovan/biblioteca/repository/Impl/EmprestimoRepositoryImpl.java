package br.com.kauepiovan.biblioteca.repository.Impl;

import java.util.UUID;

import br.com.kauepiovan.biblioteca.domain.model.Emprestimo;
import br.com.kauepiovan.biblioteca.repository.EmprestimoRepository;
import br.com.kauepiovan.biblioteca.repository.InMemoryRepository;

public class EmprestimoRepositoryImpl 
        extends InMemoryRepository<Emprestimo, UUID>
        implements EmprestimoRepository {
    
    @Override
    protected UUID getId(Emprestimo emprestimo) {
        return emprestimo.getId();
    }
    
}
