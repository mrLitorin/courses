package by.senla.task3.ex4.util;


import by.senla.task3.ex4.api.dao.IBookDao;
import by.senla.task3.ex4.dao.BookDao;
import by.senla.task3.ex4.model.Book;
import by.senla.task3.ex4.model.BookStatus;

public class InitBD {
    public void initBookDao() {
        IBookDao bookDao = BookDao.getInstance();
        bookDao.save(new Book("Title1", "Author1", BookStatus.ON_SALE));
        bookDao.save(new Book("Title2", "Author2", BookStatus.ON_SALE));
        bookDao.save(new Book("Title3", "Author3", BookStatus.MISSING));
        bookDao.save(new Book("Title4", "Author4", BookStatus.ON_SALE));
        bookDao.save(new Book("Title5", "Author5", BookStatus.ON_SALE));
        bookDao.save(new Book("Title6", "Author6", BookStatus.MISSING));
        bookDao.save(new Book("Title7", "Author7", BookStatus.ON_SALE));
        bookDao.save(new Book("Title8", "Author8", BookStatus.ON_SALE));
        bookDao.save(new Book("Title9", "Author9", BookStatus.MISSING));
    }
}
