package by.senla.bookstore.model;

import by.senla.bookstore.util.GeneratorID;
import by.senla.bookstore.util.MyRandom;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Order extends AEntity {
    private LocalDateTime date;
    private Map<Book, Integer> bookMap = new HashMap<>();
    private Client client;
    private OrderState orderStatus;
    private int price;

    {
        this.date = MyRandom.getDateOrder();
        this.setId(GeneratorID.generateOrderId());
        this.setOrderStatus(OrderState.HOT);
    }

    public Order() {
    }

    public Order(Book book, Integer quantity) {
        bookMap.put(book, quantity);
    }

    public int getPrice() {
        price = 0;
        bookMap.forEach((book, quantity) -> price += book.getPrice() * quantity);
        return price;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Map<Book, Integer> getBookMap() {
        return bookMap;
    }

    public void setBookMap(Map<Book, Integer> bookMap) {
        this.bookMap = bookMap;
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
        return Objects.hash(date, bookMap, client, orderStatus);
    }

    @Override
    public String toString() {
        String df = getDate().format(DateTimeFormatter.ofPattern("d MMM yyyy HH:mm:ss"));
        return "Order #" + this.getId() + ": " + df +
                ", \nbooks: " + bookMap +
                ", \nclient: " + client +
                "\nOrder progress: " + orderStatus + '\n' +
                "price: " + this.getPrice() + "$\n";
    }
}
