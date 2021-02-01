package by.senla.task3.ex4.model;

import by.senla.task3.ex4.dao.BookDao;
import by.senla.task3.ex4.util.GeneratorID;

public class Request extends AEntity {
    private Book missingBook;
    private RequestStatus status;

    {
        this.setId(GeneratorID.generateRequestId());
    }

    public Request() {
    }

    public Request(Book missingBook) {
        if (BookDao.getInstance().getList().contains(missingBook)) {
            this.missingBook = missingBook;
            this.status = RequestStatus.IN_PROCESSING;
        }
    }


    public Book getMissingBook() {
        return missingBook;
    }

    public void setMissingBooks(Book missingBook) {
        this.missingBook = missingBook;
    }

    @Override
    public String toString() {
        return "Request #" + this.getId() + " \n" + missingBook +
                ", \nProgress: " + status + "\n";
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }
}
