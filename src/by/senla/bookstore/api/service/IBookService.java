package by.senla.bookstore.api.service;

import by.senla.bookstore.model.Book;

import java.util.List;

public interface IBookService {

    List<Book> getBooks();

    List<Book> sort(List<Book> list, String sortBy);

    List<Book> unpopularBooks();

    Book getBookById(long Id);

    void writeOff(Book book);
}