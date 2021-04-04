package com.senla.store.api.dao;

import com.senla.store.model.AEntity;

import java.util.List;

public interface GenericDAO<T extends AEntity> {
    void save(T entity);

    void delete(T entity);

    List<T> getAll();

    T getById(Long id);

    T update(T entity);

    List<T> deserialization(String pathToFile);
}
