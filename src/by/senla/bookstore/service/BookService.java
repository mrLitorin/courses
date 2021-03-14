package by.senla.bookstore.service;

import by.senla.bookstore.api.dao.IBookDao;
import by.senla.bookstore.api.dao.IRequestDao;
import by.senla.bookstore.api.service.IBookService;
import by.senla.bookstore.dao.BookDao;
import by.senla.bookstore.dao.RequestDao;
import by.senla.bookstore.model.Book;
import by.senla.bookstore.model.BookStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BookService implements IBookService {
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
            case ("title"):
                return books.stream()
                        .sorted(Comparator.comparing(Book::getTitle))
                        .collect(Collectors.toList());
            case "price":
                return books.stream()
                        .sorted(Comparator.comparingInt(Book::getPrice))
                        .collect(Collectors.toList());
            case "year":
                return books.stream()
                        .sorted(Comparator.comparingInt(Book::getPublicationYear))
                        .collect(Collectors.toList());
            case "status":
                return books.stream()
                        .sorted((o1, o2) -> o2.getStatus().ordinal() - o1.getStatus().ordinal())
                        .collect(Collectors.toList());
            default:
                System.out.println("Invalid input \"Sort By\".");
        }
        return new ArrayList<>();
    }

    @Override
    public List<Book> getBooks() {
        return bookDao.getAll();
    }

    @Override
    public List<Book> unpopularBooks() {
        books = bookDao.getAll();
        LocalDateTime halfYears = LocalDateTime.now().minusMonths(6);
        return books.stream()
                .filter(book -> book.getDateOfLastSale().isBefore(halfYears))
                .collect(Collectors.toList());
    }

    @Override
    public Book getBookById(long Id) {
        return bookDao.getById(Id);
    }

    @Override
    public void writeOff(Book book) {
        books = bookDao.getAll().stream()
                .filter(book1 -> book.getStatus() != BookStatus.MISSING)
                .collect(Collectors.toList());
        if (book != null && books.contains(book)) {
            book.setQuantity(0);
            book.setStatus(BookStatus.MISSING);
        } else {
            System.out.println("The book is missing or does not exist.");
        }
    }
}
