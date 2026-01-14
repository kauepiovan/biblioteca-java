package br.com.kauepiovan.biblioteca.repository.Impl;

import java.util.Optional;
import java.util.UUID;

import br.com.kauepiovan.biblioteca.domain.model.Livro;
import br.com.kauepiovan.biblioteca.repository.InMemoryRepository;
import br.com.kauepiovan.biblioteca.repository.LivroRepository;

public class LivroRepositoryImpl
        extends InMemoryRepository<Livro, UUID>
        implements LivroRepository{

    @Override
    protected UUID getId(Livro livro) {
        return livro.getId();
    }

    @Override
    public Optional<Livro> findByTitulo(String titulo) {
        return data.stream()
                .filter(u -> u.getTitulo().equals(titulo))
                .findFirst();
    }
}