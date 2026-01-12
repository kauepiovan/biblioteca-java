package br.com.kauepiovan.biblioteca.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.com.kauepiovan.biblioteca.domain.model.Usuario;

public class UsuarioRepository implements Repository<Usuario>{

    public List<Usuario> data = new ArrayList<>();

    @Override
    public List<Usuario> getAll() {
        return data;
    }

    @Override
    public Optional<Usuario> getOne(UUID id) {
        for (Usuario usuario : data) {
            if (usuario.getId().equals(id)) {
                return Optional.of(usuario);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Usuario> getOne(String email) {
        for (Usuario usuario : data) {
            if (usuario.getEmail().equals(email)) {
                return Optional.of(usuario);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Usuario> removeOne(UUID id) {
        for (Usuario usuario : data) {
            if (usuario.getId().equals(id)) {
                data.remove(usuario);
                return Optional.of(usuario);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Usuario> removeOne(String email) {
        for (Usuario usuario : data) {
            if (usuario.getEmail().equals(email)) {
                data.remove(usuario);
                return Optional.of(usuario);
            }
        }
        return Optional.empty();
    }

    @Override
    public Usuario addOne(Usuario usuario) {
        data.add(usuario);
        return usuario;
    }

    @Override
    public Usuario update(Usuario entity) {
        return data.set(data.indexOf(entity), entity);
    }
    
}
