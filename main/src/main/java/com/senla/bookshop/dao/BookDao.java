package com.senla.bookshop.dao;

import com.senla.bookshop.api.dao.IBookDao;
import com.senla.bookshop.model.Book;
import com.senla.bookshop.model.BookStatus;
import com.senla.bookshop.util.PropertiesHandler;
import com.senla.bookshop.util.SerializationHandler;
import exception.DaoException;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookDao extends AbstractDao<Book> implements IBookDao, Serializable {
    private static final Logger LOGGER = Logger.getLogger(Book.class.getSimpleName());
    private static final BookDao instance = getInstance();
    private List<Book> books;

    private BookDao() {
        deserialize();
        LOGGER.info("Deserialization success.");
    }

    public static BookDao getInstance() {
        return Objects.requireNonNullElse(instance, new BookDao());
    }

    private void deserialize() {
        try {
            var path = PropertiesHandler.getProperties("shop.serialization.path_books");
            books = (List<Book>) SerializationHandler.readObject(path.get());

        } catch (IOException | ClassNotFoundException e ) {
            books = new ArrayList<>();
            LOGGER.error(e);
            throw new DaoException("Deserialization filed.", e);
        }
    }

    @Override
    public Book getById(Long id) {
        for (Book book : getAll()) {
            if (id.equals(book.getId())) {
                return book;
            }
        }
        LOGGER.error("Book by ID #" + id + " not found.");
        throw new DaoException("Book #" + id + " not found.");
    }

    @Override
    public List<Book> getAll() {
        return new ArrayList<>(books);
    }

    @Override
    public void delete(Book book) {
        try {
            books.remove(book);
        } catch (Exception e) {
            throw new DaoException("This book is not available.", e);
        }
    }

    @Override
    public void save(Book book) {
        books.add(book);
        if (book.getQuantity() == 0) {
            book.setStatus(BookStatus.MISSING);
        } else {
            book.setStatus(BookStatus.ON_SALE);
        }
    }
}
