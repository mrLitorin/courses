package by.senla.task3.ex4.api.dao;

import by.senla.task3.ex4.model.AEntity;

import java.util.List;

public interface GenericDAO<T extends AEntity> {
    void save(T entity);

    void delete(T entity);

    List<T> getList();

    T getByID(Long id);

    T update(T entity);
}
