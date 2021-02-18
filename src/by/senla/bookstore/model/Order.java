package by.senla.bookstore.model;

import by.senla.bookstore.util.GeneratorID;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Order extends AEntity {
    private LocalDateTime date;
    private List<Book> bookList = new ArrayList<>();
    private Client client;
    private OrderState orderStatus;
    private int price;

    {
        this.setId(GeneratorID.generateOrderId());
    }

    public Order() {
        this.date = LocalDateTime.now();
        this.setId(GeneratorID.generateOrderId());
    }

    public Order(Book... books) {
        this.date = LocalDateTime.now();
        this.setId(GeneratorID.generateOrderId());
        bookList.addAll(0, Arrays.asList(books));
    }

    public int getPrice() {
        price = 0;
        bookList.stream()
                .forEach(book -> price += book.getPrice());
        return price;
    }

    public String getDate() {
        return date.format(DateTimeFormatter.ofPattern("d MMM uuuu HH:mm:ss"));
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
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

    @Override
    public String toString() {
        return "Order #" + this.getId() + " >>> " + this.getDate() +
                ", \nbooks: " + bookList +
                ", \nclient: " + client +
                "\nOrder progress: " + orderStatus + '\n' +
                "price: " + this.getPrice() + '\n';
    }
}
