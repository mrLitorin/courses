package by.senla.task3.ex4.dao;

import by.senla.task3.ex4.api.dao.IBookDao;
import by.senla.task3.ex4.model.Book;

import java.util.List;

public class BookDao extends AbstractDao<Book> implements IBookDao {
    private static final IBookDao bookDao = new BookDao();
    private final List<Book> books = this.getList();

    private BookDao() {
    }

    public static IBookDao getInstance() {
        return bookDao;
    }

    public List<Book> getBooks() {
        return books;
    }

    @Override
    public Book update(Book book) {
        Book temp = null;
        if (books.contains(book)) {
            temp = books.get(books.indexOf(book));
//            temp.setId(book.getId());
//            temp.setTitle(book.getTitle());
//            temp.setAuthor(book.getAuthor());
            temp.setStatus(book.getStatus());
            temp.setPrice(book.getPrice());
        }
        return temp;
    }
}
