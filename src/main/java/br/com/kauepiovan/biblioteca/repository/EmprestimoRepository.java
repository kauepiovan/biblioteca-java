package br.com.kauepiovan.biblioteca.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.com.kauepiovan.biblioteca.domain.model.Emprestimo;

public class EmprestimoRepository implements Repository<Emprestimo> {

    public List<Emprestimo> data = new ArrayList<>();

    @Override
    public List<Emprestimo> getAll() {
        return data;
    }

    @Override
    public Optional<Emprestimo> getOne(UUID id) {
        for (Emprestimo emprestimo : data) {
            if (emprestimo.getId().equals(id)) {
                return Optional.of(emprestimo);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Emprestimo> getOne(String value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOne'");
    }

    @Override
    public Optional<Emprestimo> removeOne(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeOne'");
    }

    @Override
    public Optional<Emprestimo> removeOne(String value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeOne'");
    }

    @Override
    public Emprestimo addOne(Emprestimo entity) {
        data.add(entity);
        return entity;
    }

    @Override
    public Emprestimo update(Emprestimo entity) {
        return data.set(data.indexOf(entity), entity);
    }

    

}
