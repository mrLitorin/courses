package by.senla.bookstore.dao;

import by.senla.bookstore.api.dao.GenericDAO;
import by.senla.bookstore.model.AEntity;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<T extends AEntity> implements GenericDAO<T> {
    private final List<T> repository = new ArrayList<>();

    @Override
    public List<T> getAll() {
        return new ArrayList<>(repository);
    }

    @Override
    public void save(T entity) {
        repository.add(entity);
    }

    @Override
    public T getById(Long id) {
        for (T entity : getAll()) {
            if (id.equals(entity.getId())) {
                return entity;
            }
        }
        System.out.println("ERROR: ID #" + id + " not found. ");
        return null;
    }

    @Override
    public void delete(T entity) {
        repository.remove(entity);
    }

    @Override
    public T update(T entity) {
        return null;
    }
}
