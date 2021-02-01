package by.senla.task3.ex4.dao;

import by.senla.task3.ex4.api.dao.IRequestDao;
import by.senla.task3.ex4.model.Request;

import java.util.List;

public class RequestDao extends AbstractDao<Request> implements IRequestDao {
    private static final IRequestDao requestDao = new RequestDao();
    private final List<Request> requests = this.getList();

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
}
