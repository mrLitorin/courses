package by.senla.bookstore.api.service;

import by.senla.bookstore.model.Book;

import java.util.List;

public interface IBookService {
    void writeOff(Book book);

    void printAllBook();

    void printBooks(List<Book> list);

    List sort(List<Book> list, String sortBy);

    List sortAll(String sortBy);
}
