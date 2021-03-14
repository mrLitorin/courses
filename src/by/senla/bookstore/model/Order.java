package by.senla.bookstore.model;

import by.senla.bookstore.util.GeneratorID;
import by.senla.bookstore.util.MyRandom;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Order extends AEntity {
    private long id;
    private LocalDateTime date;
    private Book orderedBook;
    private Client client;
    private OrderState orderStatus;
    private int quantity;
    private int price;

    {
        this.date = MyRandom.getDateOrder();
        this.setId(GeneratorID.generateOrderId());
        this.setOrderStatus(OrderState.HOT);
    }

    public Order() {
    }

    public Order(Book orderedBook, int quantity) {
        this.orderedBook = orderedBook;
        this.quantity = quantity;
    }

    public Order(Book orderedBook, int quantity, Client client) {
        this.orderedBook = orderedBook;
        this.quantity = quantity;
        this.client = client;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public int getPrice() {
        price = orderedBook.getPrice() * quantity;
        return price;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Book getOrderedBook() {
        return orderedBook;
    }

    public void setOrderedBook(Book book) {
        this.orderedBook = book;
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
        return Objects.hash(date, orderedBook, client, orderStatus);
    }

    @Override
    public String toString() {
        String df = getDate().format(DateTimeFormatter.ofPattern("d MMM yyyy HH:mm:ss"));
        return "Order #" + this.getId() + ": " + df +
                ", \nbooks: " + orderedBook +
                ", \nclient: " + client +
                "\nOrder progress: " + orderStatus + '\n' +
                "price: " + this.getPrice() + "$\n";
    }

    public int getQuantity() {
        return quantity;
    }
}
