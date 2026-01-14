package br.com.kauepiovan.biblioteca.repository;

import java.util.Optional;
import java.util.UUID;

import br.com.kauepiovan.biblioteca.domain.model.Usuario;


public interface UsuarioRepository extends Repository<Usuario, UUID>{
    Optional<Usuario> findByEmail(String email);
}