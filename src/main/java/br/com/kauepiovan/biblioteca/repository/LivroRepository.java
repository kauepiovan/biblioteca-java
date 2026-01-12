package br.com.kauepiovan.biblioteca.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.com.kauepiovan.biblioteca.domain.model.Livro;

public class LivroRepository implements Repository<Livro> {

    public List<Livro> data = new ArrayList<>();

    @Override
    public List<Livro> getAll() {
        return data;
    }

    @Override
    public Optional<Livro> getOne(UUID id) {
        for (Livro livro : data) {
            if (livro.getId().equals(id)) {
                return Optional.of(livro);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Livro> getOne(String titulo) {
        for (Livro livro : data) {
            if (livro.getTitulo().equals(titulo)) {
                return Optional.of(livro);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Livro> removeOne(UUID id) {
        for (Livro livro : data) {
            if (livro.getId().equals(id)) {
                data.remove(livro);
                return Optional.of(livro);
            }
        }
        return Optional.empty();
    }

    
    @Override
    public Optional<Livro> removeOne(String titulo) {
        for (Livro livro : data) {
            if (livro.getTitulo().equals(titulo)) {
                data.remove(livro);
                return Optional.of(livro);
            }
        }
        return Optional.empty();
    }

    @Override
    public Livro addOne(Livro livro) {
        data.add(livro);
        return livro;
    }

    @Override
    public Livro update(Livro entity) {
        return data.set(data.indexOf(entity), entity);
    }

}
