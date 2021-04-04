package com.senla.store.util;


import com.senla.store.api.dao.IBookDao;
import com.senla.store.dao.BookDao;
import com.senla.store.model.Book;


public class InitBD {
    public void initBD() {
        IBookDao bookDao = BookDao.getInstance();

        bookDao.save(new Book("Title1", "Author1", 3));// 100000
        bookDao.save(new Book("Title2", "Author2", 4));// 100001
        bookDao.save(new Book("Title3", "Author3", 0));// 100002 MISSING
        bookDao.save(new Book("Title4", "Author4", 2));// 100003
        bookDao.save(new Book("Title0", "Author0", 5));// 100004
        bookDao.save(new Book("Title6", "Author6", 0));// 100005 MISSING
        bookDao.save(new Book("Title7", "Author7", 5));// 100006
        bookDao.save(new Book("Title8", "Author8", 7));// 100007
        bookDao.save(new Book("Title9", "Author9", 0));// 100008 MISSING


        //рандомное время для книг
        bookDao.getAll()
                .forEach(book -> book.setDateOfLastSale(MyRandom.getDateOfLastSale()));
        //о книге
        bookDao.getAll()
                .forEach(book -> book.setDescription("About the book.....bla bla"));
    }
}
