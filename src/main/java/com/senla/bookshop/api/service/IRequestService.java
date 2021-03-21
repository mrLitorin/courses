package com.senla.bookshop.api.service;

import com.senla.bookshop.model.Book;
import com.senla.bookshop.model.Request;

import java.util.List;

public interface IRequestService {

    List<Request> getRequests();

    List<Request> sort(List<Request> list, String sortBy);

    Request getRequestById(long id);

    void addBookOnStock(Book book);

    void createRequest(Request request);
}
