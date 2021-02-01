package by.senla.task3.ex4.service;

import by.senla.task3.ex4.api.dao.IBookDao;
import by.senla.task3.ex4.api.service.IBookService;
import by.senla.task3.ex4.dao.BookDao;
import by.senla.task3.ex4.model.Book;
import by.senla.task3.ex4.model.BookStatus;

import java.util.List;

public class BookService implements IBookService {
    private final IBookDao bookDao = BookDao.getInstance();
    private final List<Book> books = bookDao.getList();

    @Override
    public void writeOff(Book book) {
        if (book != null && books.contains(book)) {
            Book oldBook = books.get(books.indexOf(book));
            book.setStatus(BookStatus.MISSING);
            bookDao.update(book);
        }
    }

    @Override
    public void printAllBook() {
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<ALL BOOKS>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        bookDao.getList().forEach(System.out::println);
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<END>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n");
    }

}
