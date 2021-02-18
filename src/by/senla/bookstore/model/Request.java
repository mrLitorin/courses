package by.senla.bookstore.model;

import by.senla.bookstore.dao.BookDao;
import by.senla.bookstore.util.GeneratorID;

public class Request extends AEntity {
    private Book missingBook;
    private int quantity;
    private RequestStatus status;

    {
        this.setId(GeneratorID.generateRequestId());
    }

    public Request() {
    }

    public Request(Book missingBook, int quantity) {
        if (BookDao.getInstance().getList().contains(missingBook)) {
            this.missingBook = missingBook;
            this.status = RequestStatus.IN_PROCESSING;
            this.quantity = quantity;
        }
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Book getMissingBook() {
        return missingBook;
    }

    public void setMissingBook(Book missingBook) {
        this.missingBook = missingBook;
    }

    public void setMissingBooks(Book missingBook) {
        this.missingBook = missingBook;
    }

    @Override
    public String toString() {
        return "Request #" + this.getId() + " \n" + missingBook +
                ", \nquantity: " + quantity +
                ", \nProgress: " + status + "\n";
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }
}
