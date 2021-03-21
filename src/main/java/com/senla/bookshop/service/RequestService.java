package com.senla.bookshop.service;

import com.senla.bookshop.api.dao.IRequestDao;
import com.senla.bookshop.api.service.IRequestService;
import com.senla.bookshop.dao.RequestDao;
import com.senla.bookshop.model.Book;
import com.senla.bookshop.model.BookStatus;
import com.senla.bookshop.model.Request;
import com.senla.bookshop.model.RequestStatus;
import exception.ServiceException;
import org.apache.log4j.Logger;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RequestService implements IRequestService {
    private static final Logger LOGGER = Logger.getLogger(OrderService.class.getSimpleName());
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
        LOGGER.info("New request #"+request.getId()+" is created.");
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
        List<Request> requestsInProcessing = requestDao.getAll().stream()
                .filter(request -> request.getStatus() == RequestStatus.IN_PROCESSING)
                .filter(request -> request.getMissingBook().equals(book))
                .collect(Collectors.toList());
        if (book != null) {
            requestsInProcessing.forEach(r -> {
                r.setStatus(RequestStatus.COMPLETED);
                book.setQuantity(r.getQuantity());
                book.setStatus(BookStatus.ON_SALE);
            });
        } else {
            LOGGER.warn("No requests for this book.");
        }
    }

    @Override
    public Request getRequestById(long id) {
       try{
           return requestDao.getById(id);
       }catch (Exception e){
           LOGGER.error("The request #"+id+" does not exist.");
           throw new ServiceException(e);
       }
    }
}
