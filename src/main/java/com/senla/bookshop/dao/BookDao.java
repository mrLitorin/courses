package com.senla.bookshop.dao;

import com.senla.bookshop.api.dao.IBookDao;
import com.senla.bookshop.model.Book;
import com.senla.bookshop.model.BookStatus;

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
