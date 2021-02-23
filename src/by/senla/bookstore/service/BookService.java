package by.senla.bookstore.service;

import by.senla.bookstore.api.dao.IBookDao;
import by.senla.bookstore.api.dao.IRequestDao;
import by.senla.bookstore.api.service.IBookService;
import by.senla.bookstore.dao.BookDao;
import by.senla.bookstore.dao.RequestDao;
import by.senla.bookstore.model.Book;
import by.senla.bookstore.model.BookStatus;
import by.senla.bookstore.model.Request;
import by.senla.bookstore.model.RequestStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BookService implements IBookService {
    private final IBookDao bookDao = BookDao.getInstance();
    private final IRequestDao requestDao = RequestDao.getInstance();
    private List<Book> books;

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
    public List<Book> unpopularBooks() {
        books = bookDao.getAll();
        LocalDateTime halfYears = LocalDateTime.now().minusMonths(6);
        return books.stream()
                .filter(book -> book.getDateOfLastSale().isBefore(halfYears))
                .collect(Collectors.toList());
    }

    @Override
    public void showDescription(Book book) {
        books = bookDao.getAll();
        if (books.contains(book)) {
            System.out.println(book);
            System.out.println(book.getDescription());
        }

    }

    @Override
    public Book getBookById(long Id) {
        return bookDao.getById(Id);
    }

    @Override
    public void addBookOnStock(Book book) {
        List<Request> requests = requestDao.getAll();
        List<Request> requestsFiltered;
        int count = 0;

        requestsFiltered = requests.stream().filter(request -> request.getMissingBook().equals(book)
                && request.getStatus().equals(RequestStatus.IN_PROCESSING))
                .collect(Collectors.toList());
        for (Request r : requestsFiltered) {
            r.setStatus(RequestStatus.COMPLETED);
            count += r.getQuantity();
        }
        book.setQuantity(count);
    }

    @Override
    public List<Book> sortAll(String sortBy) {
        books = new ArrayList<>(books);
        return this.sort(books, sortBy);
    }

    @Override
    public void writeOff(Book book) {
        books = bookDao.getAll();
        if (book != null && books.contains(book)) {
            book.setQuantity(0);
            book.setStatus(BookStatus.MISSING);
        }
    }

    @Override
    public void printAllBook() {
        books = bookDao.getAll();
        System.out.println("####################### ALL BOOKS ##########################");
        bookDao.getAll().forEach(System.out::println);
        System.out.print("###########################################################\n");
    }

    @Override
    public void printBooks(List<Book> list) {
        System.out.println("######################## BOOKS ############################");
        list.forEach(System.out::println);
        System.out.print("############################################################\n");

    }
}
