package by.senla.bookstore.api.service;

import by.senla.bookstore.model.Book;

import java.util.List;

public interface IBookService {

    void writeOff(Book book);

    void printAllBook();

    void printBooks(List<Book> list);

    List<Book> sort(List<Book> list, String sortBy);

    List<Book> sortAll(String sortBy);

    Book getBookById(long Id);

    void addBookOnStock(Book book);

    List<Book> unpopularBooks();

    void showDescription(Book bookById);

}