package br.com.kauepiovan.biblioteca.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.com.kauepiovan.biblioteca.domain.model.Bibliotecario;

public class BibliotecarioRepository implements Repository<Bibliotecario> {

    public List<Bibliotecario> data = new ArrayList<>();

    @Override
    public List<Bibliotecario> getAll() {
        return data;
    }

    @Override
    public Optional<Bibliotecario> getOne(UUID id) {
        for (Bibliotecario bibliotecario : data) {
            if (bibliotecario.getId().equals(id)) {
                return Optional.of(bibliotecario);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Bibliotecario> getOne(String value) {
        for (Bibliotecario bibliotecario : data) {
            if (bibliotecario.getEmail().equals(value)) {
                return Optional.of(bibliotecario);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Bibliotecario> removeOne(UUID id) {
        for (Bibliotecario bibliotecario : data) {
            if (bibliotecario.getId().equals(id)) {
                data.remove(bibliotecario);
                return Optional.of(bibliotecario);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Bibliotecario> removeOne(String value) {
        for (Bibliotecario bibliotecario : data) {
            if (bibliotecario.getEmail().equals(value)) {
                data.remove(bibliotecario);
                return Optional.of(bibliotecario);
            }
        }
        return Optional.empty();
    }

    @Override
    public Bibliotecario addOne(Bibliotecario entity) {
        data.add(entity);
        return entity;
    }

    @Override
    public Bibliotecario update(Bibliotecario entity) {
        return data.set(data.indexOf(entity), entity);
    }

}
