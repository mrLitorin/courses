package by.senla.bookstore.dao;

import by.senla.bookstore.api.dao.IRequestDao;
import by.senla.bookstore.model.Request;

import java.util.Objects;

public class RequestDao extends AbstractDao<Request> implements IRequestDao {
    private static final RequestDao instance = getInstance();

    private RequestDao() {
    }

    public static RequestDao getInstance() {
        return Objects.requireNonNullElse(instance, new RequestDao());
    }
}
