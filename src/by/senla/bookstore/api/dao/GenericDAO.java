package by.senla.bookstore.api.dao;

import by.senla.bookstore.model.AEntity;

import java.util.List;

public interface GenericDAO<T extends AEntity> {
    void save(T entity);

    void delete(T entity);

    List<T> getAll();

    T getById(Long id);

    T update(T entity);
}
