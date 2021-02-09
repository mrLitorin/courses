package by.senla.bookstore.api.service;

import by.senla.bookstore.model.Request;

public interface IRequestService {
    void sentRequest(Request request);

    void printAllRequest();

    void addAllBooksOnSale();
}
