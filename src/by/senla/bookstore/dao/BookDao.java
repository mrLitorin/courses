package by.senla.bookstore.dao;

import by.senla.bookstore.api.dao.IBookDao;
import by.senla.bookstore.model.Book;
import by.senla.bookstore.model.BookStatus;

import java.util.Objects;

public class BookDao extends AbstractDao<Book> implements IBookDao {
    private static final BookDao instance = getInstance();

    private BookDao() {
    }

    public static BookDao getInstance() {
        return Objects.requireNonNullElse(instance, new BookDao());
    }

    @Override
    public void save(Book book) {
        super.save(book);
        if (book.getQuantity() == 0) {
            book.setStatus(BookStatus.MISSING);
        } else {
            book.setStatus(BookStatus.ON_SALE);
        }
    }
}
