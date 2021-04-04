package com.senla.store.dao;

import com.senla.exception.DaoException;
import com.senla.store.api.dao.IRequestDao;
import com.senla.store.model.Book;
import com.senla.store.model.Request;
import com.senla.store.util.SerializationHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RequestDao extends AbstractDao<Request> implements IRequestDao, Serializable {
    private static final Logger LOGGER = LoggerFactory.getLogger(Request.class.getSimpleName());
    private static final IRequestDao instance = getInstance();
    private List<Request> requests = new ArrayList<>();

    private RequestDao() {
    }

    public static RequestDao getInstance() {
        return (RequestDao) Objects.requireNonNullElse(instance, new RequestDao());
    }

    @Override
    public List<Request> getAll() {
        return new ArrayList<>(requests);
    }

    @Override
    public void save(Request Request) {
        requests.add(Request);
    }

    @Override
    public Request getById(Long id) {
        for (Request request : getAll()) {
            if (id.equals(request.getId())) {
                return request;
            }
        }
        LOGGER.error("Request #" + id + " not found.");
        throw new DaoException("Request #" + id + " not found.");
    }

    @Override
    public void delete(Request Request) {
        try {
            requests.remove(Request);
        } catch (Exception e) {
            throw new DaoException("This request is not available.", e);
        }
    }

    @Override
    public List<Request> deserialization(String pathToFile) {
        try {
            requests = (List<Request>) SerializationHandler.readObject(pathToFile);
            return requests;
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.error(e.getLocalizedMessage());
            throw new DaoException("Deserialization requests failed.");
        }
    }
}
