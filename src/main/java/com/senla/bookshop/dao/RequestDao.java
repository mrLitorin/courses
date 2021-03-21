package com.senla.bookshop.dao;

import com.senla.bookshop.api.dao.IRequestDao;
import com.senla.bookshop.model.Request;

import java.util.Objects;

public class RequestDao extends AbstractDao<Request> implements IRequestDao {
    private static final RequestDao instance = getInstance();

    private RequestDao() {
    }

    public static RequestDao getInstance() {
        return Objects.requireNonNullElse(instance, new RequestDao());
    }
}
