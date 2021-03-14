package by.senla.bookstore.model;

import by.senla.bookstore.dao.BookDao;
import by.senla.bookstore.util.GeneratorID;

import java.util.Objects;

public class Request extends AEntity {
    private long id;
    private Book missingBook;
    private int quantity;
    private RequestStatus status;

    {
        this.setId(GeneratorID.generateRequestId());
    }

    public Request() {
    }

    public Request(Book missingBook, int quantity) {
        if (BookDao.getInstance().getAll().contains(missingBook)) {
            this.missingBook = missingBook;
            this.status = RequestStatus.IN_PROCESSING;
            this.quantity = quantity;
        }
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
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

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return quantity == request.quantity && missingBook.equals(request.missingBook);
    }

    @Override
    public int hashCode() {
        return Objects.hash(missingBook, quantity, status);
    }

    @Override
    public String toString() {
        return "Request #" + this.getId() + " \n" + missingBook +
                ", \nquantity: " + quantity +
                ", \nProgress: " + status + "\n";
    }


}
