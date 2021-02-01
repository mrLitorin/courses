package by.senla.task3.ex4;

import by.senla.task3.ex4.api.service.IBookService;
import by.senla.task3.ex4.api.service.IOrderService;
import by.senla.task3.ex4.api.service.IRequestService;
import by.senla.task3.ex4.dao.BookDao;
import by.senla.task3.ex4.dao.OrderDao;
import by.senla.task3.ex4.model.*;
import by.senla.task3.ex4.service.BookService;
import by.senla.task3.ex4.service.OrderService;
import by.senla.task3.ex4.service.RequestService;
import by.senla.task3.ex4.util.InitBD;

public class Runner {
    public static void main(String[] args) {
        InitBD initBD = new InitBD();
        IBookService bookService = new BookService();
        IOrderService orderService = new OrderService();
        IRequestService requestService = new RequestService();

        //Инициализация БД и ее вывод
        initBD.initBookDao();
        bookService.printAllBook();

        //Списать книгу со склада
        bookService.writeOff(BookDao.getInstance().getByID(100000L));
        bookService.printAllBook();

        //Создать заказ на книгу (MISSING)
        orderService.create(new Order(BookDao.getInstance().getByID(100002L)));
        //Создать заказ на книгу (ON_SALE)
        orderService.create(new Order(BookDao.getInstance().getByID(100007L)));
        //еще один
        orderService.printAllOrders();

        //Отменить заказ
        orderService.cancel(OrderDao.getInstance().getByID(500001L));
        //Изменить статус заказа
        orderService.changeStatus(OrderDao.getInstance().getByID(500003L), OrderState.COMPLETED);
        orderService.printAllOrders();

        //Добавить книги на склад
        requestService.printAllRequest();
        requestService.addAllBooksOnSale();
        requestService.printAllRequest();
        bookService.printAllBook();


        //Оставить запрос на книгу
        requestService.sentRequest(new Request(BookDao.getInstance().getByID(100004L)));
        requestService.printAllRequest();
    }
}
