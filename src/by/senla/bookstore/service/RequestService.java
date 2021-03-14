package by.senla.bookstore.service;

import by.senla.bookstore.api.dao.IRequestDao;
import by.senla.bookstore.api.service.IRequestService;
import by.senla.bookstore.dao.RequestDao;
import by.senla.bookstore.model.Book;
import by.senla.bookstore.model.BookStatus;
import by.senla.bookstore.model.Request;
import by.senla.bookstore.model.RequestStatus;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RequestService implements IRequestService {
    private static RequestService instance;
    private final IRequestDao requestDao = RequestDao.getInstance();

    private RequestService() {
    }

    public static RequestService getInstance() {
        return Objects.requireNonNullElse(instance, new RequestService());
    }

    @Override
    public void createRequest(Request request) {
        request.setStatus(RequestStatus.IN_PROCESSING);
        requestDao.save(request);
    }

    @Override
    public List<Request> getRequests() {
        return requestDao.getAll();
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

    @Override
    public void addBookOnStock(Book book) {
        List<Request> requestsInProcessing = requestDao.getAll().stream()
                .filter(request -> request.getStatus() == RequestStatus.IN_PROCESSING)
                .filter(request -> request.getMissingBook().equals(book))
                .collect(Collectors.toList());
        if (book != null && requestsInProcessing != null) {
            requestsInProcessing.forEach(r -> {
                r.setStatus(RequestStatus.COMPLETED);
                book.setQuantity(r.getQuantity());
                book.setStatus(BookStatus.ON_SALE);
            });
        } else {
            System.out.print("No requests for this book.");
        }
    }

    @Override
    public Request getRequestById(long id) {
        return requestDao.getById(id);
    }
}
