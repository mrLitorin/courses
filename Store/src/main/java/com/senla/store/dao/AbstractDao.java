package com.senla.store.dao;

import com.senla.exception.DaoException;
import com.senla.store.api.dao.GenericDAO;
import com.senla.store.model.AEntity;
import com.senla.store.util.SerializationHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<T extends AEntity> implements GenericDAO<T>, Serializable {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractDao.class.getSimpleName());
    private List<T> repository = new ArrayList<>();

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

    @Override
    public List<T> deserialization(String pathToFile) {
        try {
            repository = (List<T>) SerializationHandler.readObject(pathToFile);
            return repository;
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.error(e.getLocalizedMessage());
            throw new DaoException("Deserialization failed.");
        }
    }
}
