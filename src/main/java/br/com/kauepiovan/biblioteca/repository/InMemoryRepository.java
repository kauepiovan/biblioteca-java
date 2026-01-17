package br.com.kauepiovan.biblioteca.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class InMemoryRepository<T, ID> {

    protected final List<T> data = new ArrayList<>();

    protected abstract ID getId(T entity);

    public Optional<T> findById(ID id) {
        return data.stream()
                .filter(e -> getId(e).equals(id))
                .findFirst();
    }
    
    public List<T> findAll() {
        return new ArrayList<>(data);
    }

    public T save(T entity) {
        deleteById(getId(entity));
        data.add(entity);
        return entity;
    }

    public void deleteById(ID id) {
        findById(id).ifPresent(data::remove);
    }
}
