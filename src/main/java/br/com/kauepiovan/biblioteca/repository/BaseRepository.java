package br.com.kauepiovan.biblioteca.repository;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;

public abstract class BaseRepository<T, ID> {

    protected final EntityManager entityManager;
    protected final Class<T> entityClass;

    public BaseRepository(EntityManager entityManager, Class<T> entityClass) {
        this.entityManager = entityManager;
        this.entityClass = entityClass;
    }

    public Optional<T> findById(ID id) {
        return Optional.ofNullable(entityManager.find(entityClass, id));
    }

    public List<T> findAll() {
        return entityManager.createQuery("FROM " + entityClass.getName(), entityClass).getResultList();
    }

    public T save(T entity) {
        try {
            entityManager.getTransaction().begin();
            T merged = entityManager.merge(entity);
            entityManager.getTransaction().commit();
            return merged;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        }
    }

    public void deleteById(ID id) {
        try {
            entityManager.getTransaction().begin();
            T entity = entityManager.find(entityClass, id);
            if (entity != null) {
                entityManager.remove(entity);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        }
    }
}
