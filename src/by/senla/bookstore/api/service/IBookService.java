package by.senla.bookstore.api.service;

import by.senla.bookstore.model.Book;

public interface IBookService {
    void writeOff(Book book);

    void printAllBook();
}
