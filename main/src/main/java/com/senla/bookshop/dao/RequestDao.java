package com.senla.bookshop.dao;

import com.senla.bookshop.api.dao.IRequestDao;
import com.senla.bookshop.model.Request;
import com.senla.bookshop.util.PropertiesHandler;
import com.senla.bookshop.util.SerializationHandler;
import exception.DaoException;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RequestDao extends AbstractDao<Request> implements IRequestDao {
    private static final Logger LOGGER = Logger.getLogger(Request.class.getSimpleName());
    private static final RequestDao instance = getInstance();
    private List<Request> requests;

    private RequestDao() {
            deserialize();
    }

    public static RequestDao getInstance() {
        return Objects.requireNonNullElse(instance, new RequestDao());
    }

    private void deserialize() {
        try {
            var path = PropertiesHandler.getProperties("shop.serialization.path_requests");
            requests = (List<Request>) SerializationHandler.readObject(path.get());
        } catch (IOException | ClassNotFoundException e) {
            requests = new ArrayList<>();
            LOGGER.error(e);
            throw new DaoException("Deserialization filed.", e);
        }
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
}
