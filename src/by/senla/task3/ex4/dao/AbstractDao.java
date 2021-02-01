package by.senla.task3.ex4.dao;

import by.senla.task3.ex4.api.dao.GenericDAO;
import by.senla.task3.ex4.model.AEntity;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<T extends AEntity> implements GenericDAO<T> {
    private final List<T> repository = new ArrayList<>();

    @Override
    public void save(T entity) {
        repository.add(entity);
    }

    @Override
    public void delete(T entity) {
        repository.remove(entity);
    }

    @Override
    public List<T> getList() {
        return repository;
    }

    @Override
    public T getByID(Long id) {
        for (T entity : repository) {
            if (id.equals(entity.getId())) {
                return entity;
            }
        }
        return null;
    }
}
