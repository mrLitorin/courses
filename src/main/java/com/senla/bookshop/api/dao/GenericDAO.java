package com.senla.bookshop.api.dao;

import com.senla.bookshop.model.AEntity;

import java.util.List;

public interface GenericDAO<T extends AEntity> {
    void save(T entity);

    void delete(T entity);

    List<T> getAll();

    T getById(Long id);

    T update(T entity);
}
