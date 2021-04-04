package com.senla.store.service;

import com.senla.exception.ServiceException;
import com.senla.store.api.dao.IRequestDao;
import com.senla.store.api.service.IRequestService;
import com.senla.store.dao.RequestDao;
import com.senla.store.model.Book;
import com.senla.store.model.BookStatus;
import com.senla.store.model.Request;
import com.senla.store.model.RequestStatus;
import com.senla.store.util.PropertiesHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class RequestService implements IRequestService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class.getSimpleName());
    private static IRequestService instance;
    private final IRequestDao requestDao = RequestDao.getInstance();
    private List<Request> requests;

    private RequestService() {
    }

    public static RequestService getInstance() {
        return (RequestService) Objects.requireNonNullElse(instance, new RequestService());
    }

    @Override
    public void deserialization() {
        try {
            var path = PropertiesHandler.getProperties("shop.serialization.path_requests");
            path.ifPresent(s -> requests = requestDao.deserialization(s));
        } catch (Exception e) {
            LOGGER.error("File not found or deserialization failed.");
            throw new ServiceException(e);
        }
    }

    @Override
    public void createRequest(Request request) {
        request.setStatus(RequestStatus.IN_PROCESSING);
        requestDao.save(request);
        LOGGER.info("New request #" + request.getId() + " is created.");
    }

    @Override
    public List<Request> getRequests() {
        return requestDao.getAll();
    }

    @Override
    public List<Request> sort(List<Request> requests, String sortBy) {
        if (requests == null) throw new ServiceException("Request list is null.");
        if ("quantity".equals(sortBy)) {
            LOGGER.info("Sorting requests by quantity.");
            return requests.stream()
                    .sorted(Comparator.comparingInt(Request::getQuantity))
                    .collect(Collectors.toList());
        }
        LOGGER.info("Sorting requests by title.");
        return requests.stream()
                .sorted(Comparator.comparing(o -> o.getMissingBook().getTitle()))
                .collect(Collectors.toList());
    }

    @Override
    public void addBookOnStock(Book book) {
        Optional<String> s = PropertiesHandler.getProperties("shop.request.status.enable");
        boolean isEnabled = Boolean.parseBoolean(s.get());
        List<Request> requestsInProcessing = requestDao.getAll().stream()
                .filter(request -> request.getStatus() == RequestStatus.IN_PROCESSING)
                .filter(request -> request.getMissingBook().equals(book))
                .collect(Collectors.toList());
        if (book != null) {
            requestsInProcessing.forEach(r -> {
                if (isEnabled) r.setStatus(RequestStatus.COMPLETED);
                book.setQuantity(r.getQuantity());
                book.setStatus(BookStatus.ON_SALE);
            });
        } else {
            LOGGER.warn("No requests for this book.");
        }
    }

    @Override
    public Request getRequestById(long id) {
        try {
            return requestDao.getById(id);
        } catch (Exception e) {
            LOGGER.error("The request #" + id + " does not exist.");
            throw new ServiceException(e);
        }
    }
}
