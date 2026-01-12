package br.com.kauepiovan.biblioteca.repository;

import java.util.List;

import java.util.Optional;

import java.util.UUID;


public interface Repository<T> {
    public List<T> getAll();
    public Optional<T> getOne(UUID id);
    public Optional<T> getOne(String value);
    public Optional<T> removeOne(UUID id);
    public Optional<T> removeOne(String value);
    public T addOne(T entity);
    public T update(T entity);
    
}
