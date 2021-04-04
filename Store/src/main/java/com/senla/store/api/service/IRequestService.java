package com.senla.store.api.service;

import com.senla.exception.ServiceException;
import com.senla.store.model.Book;
import com.senla.store.model.Request;
import com.senla.store.util.PropertiesHandler;

import java.util.List;

public interface IRequestService {

    List<Request> getRequests();

    List<Request> sort(List<Request> list, String sortBy);

    Request getRequestById(long id);

    void deserialization();

    void addBookOnStock(Book book);

    void createRequest(Request request);
}
