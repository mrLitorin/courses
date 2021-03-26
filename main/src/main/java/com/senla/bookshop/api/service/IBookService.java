package com.senla.bookshop.api.service;

import com.senla.bookshop.model.Book;

import java.util.List;

public interface IBookService {

    List<Book> getBooks();

    List<Book> sort(List<Book> list, String sortBy);

    List<Book> unpopularBooks();

    Book getBookById(long Id);

    void writeOff(Book book);
}