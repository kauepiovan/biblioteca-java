package br.com.kauepiovan.biblioteca.repository.interfaces;

import java.util.Optional;
import java.util.UUID;

import br.com.kauepiovan.biblioteca.domain.model.Bibliotecario;

public interface BibliotecarioRepository extends Repository<Bibliotecario, UUID> {
    Optional<Bibliotecario> findByEmail(String email);
}
