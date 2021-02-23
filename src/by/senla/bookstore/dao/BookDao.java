package by.senla.bookstore.dao;

import by.senla.bookstore.api.dao.IBookDao;
import by.senla.bookstore.model.Book;
import by.senla.bookstore.model.BookStatus;

public class BookDao extends AbstractDao<Book> implements IBookDao {
    private static final IBookDao bookDao = new BookDao();

    private BookDao() {
    }

    public static IBookDao getInstance() {
        return bookDao;
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

    @Override
    public Book update(Book book) {
//        Book temp = null;
//        if (books.contains(book)) {
//            temp = books.get(books.indexOf(book));
//            temp.setStatus(book.getStatus());
//            temp.setPrice(book.getPrice());
//        }
        return null;//temp;
    }
}
