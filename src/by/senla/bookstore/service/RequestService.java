package by.senla.bookstore.service;

import by.senla.bookstore.api.dao.IBookDao;
import by.senla.bookstore.api.service.IRequestService;
import by.senla.bookstore.dao.BookDao;
import by.senla.bookstore.dao.RequestDao;
import by.senla.bookstore.model.BookStatus;
import by.senla.bookstore.model.Request;
import by.senla.bookstore.model.RequestStatus;

import java.util.List;
import java.util.stream.Collectors;

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
    public void printRequest(List<Request> requests) {
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<ALL REQUEST<<<<<<<<<<<<<<>>>>>>>>>>>>");
        requests.forEach(System.out::println);
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

    @Override
    public List<Request> sortAll(String sortBy) {
        List<Request> requests = requestDao.getList();
        return this.sort(requests, sortBy);
    }

    @Override
    public List<Request> sort(List<Request> list, String sortBy) {
        List<Request> requests = list;
        switch (sortBy) {
            case "quantity":
                return requests.stream()
                        .sorted((o1, o2) -> o1.getQuantity() - o2.getQuantity())
                        .collect(Collectors.toList());

            case "abc":
                return requests.stream()
                        .sorted((o1, o2) -> o1.getMissingBook().getTitle().compareTo(o2.getMissingBook().getTitle()))
                        .collect(Collectors.toList());
        }
        return list;
    }
}
