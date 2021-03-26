package com.senla.bookshop.dao;

import com.senla.bookshop.api.dao.GenericDAO;
import com.senla.bookshop.model.AEntity;
import exception.DaoException;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<T extends AEntity> implements GenericDAO<T> {
    private static final Logger LOGGER = Logger.getLogger(AbstractDao.class.getSimpleName());
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
        LOGGER.error("ID #" + id + " not found.");
        throw new DaoException("ID #" + id + " not found.");
    }

    @Override
    public void delete(T entity) {
        try {
            repository.remove(entity);
        } catch (Exception e) {
            throw new DaoException("This entity is not available.", e);
        }
    }

    @Override
    public T update(T entity) {
        return null;
    }
}
