package by.senla.bookstore.service;

import by.senla.bookstore.api.dao.IBookDao;
import by.senla.bookstore.api.service.IRequestService;
import by.senla.bookstore.dao.BookDao;
import by.senla.bookstore.dao.RequestDao;
import by.senla.bookstore.model.Request;
import by.senla.bookstore.model.RequestStatus;

import java.util.Comparator;
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
        System.out.println("####################### ALL REQUESTS #####################");
        requestDao.getAll().forEach(System.out::println);
        System.out.println("###########################################################\n");
    }

    @Override
    public void printRequest(List<Request> requests) {
        System.out.println("####################### REQUESTS ##########################");
        requests.forEach(System.out::println);
        System.out.print("###########################################################\n");
    }

    @Override
    public List<Request> sortAll(String sortBy) {
        List<Request> requests = requestDao.getAll();
        return this.sort(requests, sortBy);
    }

    @Override
    public List<Request> sort(List<Request> requests, String sortBy) {
        switch (sortBy) {
            case "quantity":
                return requests.stream()
                        .sorted(Comparator.comparingInt(Request::getQuantity))
                        .collect(Collectors.toList());

            case "title":
                return requests.stream()
                        .sorted(Comparator.comparing(o -> o.getMissingBook().getTitle()))
                        .collect(Collectors.toList());
            default:
                System.out.println("Invalid input >> " + sortBy);
        }
        return requests;
    }
}
