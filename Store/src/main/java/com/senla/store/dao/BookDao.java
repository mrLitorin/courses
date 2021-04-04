package com.senla.store.dao;

import com.senla.exception.DaoException;
import com.senla.store.api.dao.IBookDao;
import com.senla.store.model.Book;
import com.senla.store.model.BookStatus;
import com.senla.store.util.SerializationHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookDao extends AbstractDao<Book> implements IBookDao, Serializable {
    private static final Logger LOGGER = LoggerFactory.getLogger(Book.class.getSimpleName());
    private static final IBookDao instance = getInstance();
    private List<Book> books = new ArrayList<>();

    private BookDao() {
    }

    public static IBookDao getInstance() {
        return Objects.requireNonNullElse(instance, new BookDao());
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

    @Override
    public List<Book> deserialization(String pathToFile) {
         try {
            books = (List<Book>) SerializationHandler.readObject(pathToFile);
            return books;
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.error(e.getLocalizedMessage());
            throw new DaoException("Deserialization books failed.");
        }
    }
}
