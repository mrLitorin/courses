package by.senla.task3.ex4.api.service;

import by.senla.task3.ex4.model.Request;

public interface IRequestService {
    void sentRequest(Request request);

    void printAllRequest();

    void addAllBooksOnSale();
}
