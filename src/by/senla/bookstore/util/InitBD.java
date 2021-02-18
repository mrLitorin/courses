package by.senla.bookstore.util;


import by.senla.bookstore.api.dao.IBookDao;
import by.senla.bookstore.dao.BookDao;
import by.senla.bookstore.model.Book;
import by.senla.bookstore.model.BookStatus;

public class InitBD {
    public void initBookDao() {
        IBookDao bookDao = BookDao.getInstance();
        bookDao.save(new Book("Title1", "Author1", BookStatus.ON_SALE));
        bookDao.save(new Book("Title2", "Author2", BookStatus.ON_SALE));
        bookDao.save(new Book("Title3", "Author3", BookStatus.MISSING));
        bookDao.save(new Book("Title4", "Author4", BookStatus.ON_SALE));
        bookDao.save(new Book("Title0", "Author0", BookStatus.ON_SALE));
        bookDao.save(new Book("Title6", "Author6", BookStatus.MISSING));
        bookDao.save(new Book("Title7", "Author7", BookStatus.ON_SALE));
        bookDao.save(new Book("Title8", "Author8", BookStatus.ON_SALE));
        bookDao.save(new Book("Title9", "Author9", BookStatus.MISSING));
    }
}
