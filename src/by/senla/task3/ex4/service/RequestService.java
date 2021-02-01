package by.senla.task3.ex4.service;

import by.senla.task3.ex4.api.dao.IBookDao;
import by.senla.task3.ex4.api.service.IRequestService;
import by.senla.task3.ex4.dao.BookDao;
import by.senla.task3.ex4.dao.RequestDao;
import by.senla.task3.ex4.model.BookStatus;
import by.senla.task3.ex4.model.Request;
import by.senla.task3.ex4.model.RequestStatus;

public class RequestService implements IRequestService {
    private final RequestDao requestDao = (RequestDao) RequestDao.getInstance();
    private final IBookDao bookDao = BookDao.getInstance();

    @Override
    public void sentRequest(Request request) {
        request.setStatus(RequestStatus.IN_PROCESSING);
        requestDao.save(request);
    }

    @Override
    public void printAllRequest() {
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<ALL REQUEST<<<<<<<<<<<<<<>>>>>>>>>>>>");
        requestDao.getList().forEach(System.out::println);
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<END<<<<<<<<<<<<<<>>>>>>>>>>>>>>>\n");
    }

    @Override
    public void addAllBooksOnSale() {
        for (Request request : requestDao.getList()) {
            request.getMissingBook().setStatus(BookStatus.ON_SALE);
            bookDao.update(request.getMissingBook());
            request.setStatus(RequestStatus.COMPLETED);
        }
    }


}
