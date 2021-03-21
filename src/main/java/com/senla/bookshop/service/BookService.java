package com.senla.bookshop.service;

import com.senla.bookshop.api.dao.IBookDao;
import com.senla.bookshop.api.dao.IRequestDao;
import com.senla.bookshop.api.service.IBookService;
import com.senla.bookshop.dao.BookDao;
import com.senla.bookshop.dao.RequestDao;
import com.senla.bookshop.model.Book;
import com.senla.bookshop.model.BookStatus;
import exception.DaoException;
import exception.ServiceException;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BookService implements IBookService {
    private static final Logger LOGGER = Logger.getLogger(BookService.class.getSimpleName());
    private static BookService instance;
    private final IBookDao bookDao = BookDao.getInstance();
    private final IRequestDao requestDao = RequestDao.getInstance();
    private List<Book> books;

    private BookService() {
    }

    public static BookService getInstance() {
        return Objects.requireNonNullElse(instance, new BookService());
    }

    @Override
    public List<Book> sort(List<Book> list, String sortBy) {
        books = list;
        switch (sortBy) {
            case "price" -> {
                LOGGER.info("Sorting books by price");
                return books.stream()
                        .sorted(Comparator.comparingInt(Book::getPrice))
                        .collect(Collectors.toList());
            }
            case "year" -> {
                LOGGER.info("Sorting books by year");
                return books.stream()
                        .sorted(Comparator.comparingInt(Book::getPublicationYear))
                        .collect(Collectors.toList());
            }
            case "status" -> {
                LOGGER.info("Sorting books by status");
                return books.stream()
                        .sorted((o1, o2) -> o2.getStatus().ordinal() - o1.getStatus().ordinal())
                        .collect(Collectors.toList());
            }
            default -> {
                LOGGER.info("Sorting books by title");
                return books.stream()
                        .sorted(Comparator.comparing(Book::getTitle))
                        .collect(Collectors.toList());
            }
        }
    }

    @Override
    public List<Book> getBooks() {
        return bookDao.getAll();
    }

    @Override
    public List<Book> unpopularBooks() {
        LOGGER.info("Selection of books not in demand.");
        books = bookDao.getAll();
        LocalDateTime halfYears = LocalDateTime.now().minusMonths(6);
        return books.stream()
                .filter(book -> book.getDateOfLastSale().isBefore(halfYears))
                .collect(Collectors.toList());
    }

    @Override
    public Book getBookById(long Id) {
        try {
            return bookDao.getById(Id);
        } catch (DaoException e) {
            LOGGER.error(e);
            throw new ServiceException("Book not exist.", e);
        }
    }

    @Override
    public void writeOff(Book book) {
        LOGGER.info("Write off the book.");
        books = bookDao.getAll().stream()
                .filter(book1 -> book.getStatus() != BookStatus.MISSING)
                .collect(Collectors.toList());
        if (book != null && books.contains(book)) {
            book.setQuantity(0);
            book.setStatus(BookStatus.MISSING);
        } else {
            LOGGER.warn("The book is missing or does not exist.");
        }
    }
}
