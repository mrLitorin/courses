package com.senla.store.service;

import com.senla.exception.DaoException;
import com.senla.exception.ServiceException;
import com.senla.store.api.dao.IBookDao;
import com.senla.store.api.dao.IRequestDao;
import com.senla.store.api.service.IBookService;
import com.senla.store.dao.BookDao;
import com.senla.store.dao.RequestDao;
import com.senla.store.model.Book;
import com.senla.store.model.BookStatus;
import com.senla.store.util.PropertiesHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookService implements IBookService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookService.class.getSimpleName());
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
    public void deserialization() {
        try {
            var path = PropertiesHandler.getProperties("shop.serialization.path_books");
            books = bookDao.deserialization(path.get());
        }catch (Exception e){
            LOGGER.error("File not found or deserialization failed.");
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Book> sort(List<Book> list, String sortBy) {
        switch (sortBy) {
            case "price" -> {
                LOGGER.info("Sorting books by price");
                return list.stream()
                        .sorted(Comparator.comparingInt(Book::getPrice))
                        .collect(Collectors.toList());
            }
            case "year" -> {
                LOGGER.info("Sorting books by year");
                return list.stream()
                        .sorted(Comparator.comparingInt(Book::getPublicationYear))
                        .collect(Collectors.toList());
            }
            case "status" -> {
                LOGGER.info("Sorting books by status");
                return list.stream()
                        .sorted((o1, o2) -> o2.getStatus().ordinal() - o1.getStatus().ordinal())
                        .collect(Collectors.toList());
            }
            default -> {
                LOGGER.info("Sorting books by title");
                return list.stream()
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
        Optional<String> s = PropertiesHandler.getProperties("shop.book.amount_of_month");
        System.out.println(s);
        int amountOfMonth = Integer.parseInt(s.get());
        LOGGER.info("Selection of books not in demand.");
        books = bookDao.getAll();
        LocalDateTime halfYears = LocalDateTime.now().minusMonths(amountOfMonth);
        return books.stream()
                .filter(book -> book.getDateOfLastSale().isBefore(halfYears))
                .collect(Collectors.toList());
    }

    @Override
    public Book getBookById(long Id) {
        try {
            return bookDao.getById(Id);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
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
