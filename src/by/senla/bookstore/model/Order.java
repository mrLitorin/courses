package by.senla.bookstore.model;

import by.senla.bookstore.util.GeneratorID;

import java.util.*;

public class Order extends AEntity {
    private final Date date;
    private List<Book> bookList = new ArrayList<>();
    private Client client;
    private OrderState orderStatus;

    {
        this.setId(GeneratorID.generateOrderId());
    }

    public Order() {
        this.date = new Date();
    }

    public Order(Book... books) {
        this.date = new Date();
        this.setId(GeneratorID.generateOrderId());
        bookList.addAll(0, Arrays.asList(books));
    }

    @Override
    public String toString() {
        return "Order #" + this.getId() + " >>> " + date +
                ", \nbooks: " + bookList +
                ", \nclient: " + client +
                "\nOrder progress: " + orderStatus + '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(this.getId(), order.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, bookList, client, orderStatus);
    }

    public Date getDate() {
        return date;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public OrderState getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderState orderStatus) {
        this.orderStatus = orderStatus;
    }
}
