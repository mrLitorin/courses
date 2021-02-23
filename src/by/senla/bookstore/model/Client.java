package by.senla.bookstore.model;

import by.senla.bookstore.util.GeneratorID;

import java.util.ArrayList;
import java.util.List;

public class Client extends AEntity {
    private final List<Order> orderList = new ArrayList<>();
    private String fullName;
    private int age;

    {
        this.setId(GeneratorID.generateClientId());
    }

    public Client(String fullName, int age) {
        this.fullName = fullName;
        this.age = age;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Client: " + fullName;
    }
}
