package br.com.kauepiovan.biblioteca.repository;

import java.util.Optional;
import java.util.UUID;

import br.com.kauepiovan.biblioteca.domain.model.Livro;

public interface LivroRepository extends Repository<Livro, UUID> {
    Optional<Livro> findByTitulo(String titulo);
}
