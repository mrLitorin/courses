package by.senla.bookstore.service;

import by.senla.bookstore.api.dao.IBookDao;
import by.senla.bookstore.api.service.IBookService;
import by.senla.bookstore.dao.BookDao;
import by.senla.bookstore.model.Book;
import by.senla.bookstore.model.BookStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookService implements IBookService {
    private final IBookDao bookDao = BookDao.getInstance();
    private List<Book> books;

    @Override
    public List sort(List<Book> list, String sortBy) {
        books = list;
        switch (sortBy) {
            case ("title"):
                return books.stream()
                        .sorted((o1, o2) -> o1.getTitle().compareTo(o2.getTitle()))
                        .collect(Collectors.toList());
            case "price":
                return books.stream()
                        .sorted((o1, o2) -> o1.getPrice() - o2.getPrice())
                        .collect(Collectors.toList());
            case "year":
                return books.stream()
                        .sorted((o1, o2) -> o1.getPublicationYear() - o2.getPublicationYear())
                        .collect(Collectors.toList());
            case "status":
                return books.stream()
                        .sorted((o1, o2) -> o2.getStatus().ordinal() - o1.getStatus().ordinal())
                        .collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public List<Book> sortAll(String sortBy) {
        books = new ArrayList<Book>(books);
        return this.sort(books, sortBy);
    }

    @Override
    public void writeOff(Book book) {
        books = bookDao.getList();
        if (book != null && books.contains(book)) {
            Book oldBook = books.get(books.indexOf(book));
            book.setStatus(BookStatus.MISSING);
            bookDao.update(book);
        }
    }

    @Override
    public void printAllBook() {
        books = bookDao.getList();
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<ALL BOOKS>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        bookDao.getList().forEach(System.out::println);
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<END>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n");
    }

    @Override
    public void printBooks(List<Book> list) {
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<ALL BOOKS>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        list.forEach(System.out::println);
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<END>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n");

    }
}
