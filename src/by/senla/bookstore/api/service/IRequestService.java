package by.senla.bookstore.api.service;

import by.senla.bookstore.model.Request;

import java.util.List;

public interface IRequestService {

    void sentRequest(Request request);

    void printAllRequest();

    void printRequest(List<Request> requests);

    List<Request> sortAll(String sortBy);

    List<Request> sort(List<Request> list, String sortBy);
}
