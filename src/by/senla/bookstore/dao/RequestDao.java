package by.senla.bookstore.dao;

import by.senla.bookstore.api.dao.IRequestDao;
import by.senla.bookstore.model.Request;

import java.util.ArrayList;
import java.util.List;

public class RequestDao extends AbstractDao<Request> implements IRequestDao {
    private static final IRequestDao requestDao = new RequestDao();
    private final List<Request> requests = super.getList();

    private RequestDao() {
    }

    public static IRequestDao getInstance() {
        return requestDao;
    }

    @Override
    public Request update(Request request) {
        Request temp = null;
        if (request != null && requests.contains(request)) {
            temp = requests.get(requests.indexOf(request));
            temp.setId(request.getId());
            temp.setStatus(request.getStatus());
            temp.setMissingBooks(request.getMissingBook());
        }
        return temp;
    }

    @Override
    public List<Request> getList() {
        List<Request> list = new ArrayList<Request>(requests);
        return list;
    }
}
