package by.senla.bookstore.api.service;

import by.senla.bookstore.model.Book;
import by.senla.bookstore.model.Request;

import java.util.List;

public interface IRequestService {

    List<Request> getRequests();

    List<Request> sort(List<Request> list, String sortBy);

    Request getRequestById(long id);

    void addBookOnStock(Book book);

    void createRequest(Request request);
}
