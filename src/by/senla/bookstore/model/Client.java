package by.senla.bookstore.model;

import by.senla.bookstore.util.GeneratorID;

public class Client extends AEntity {
    private long id;
    private String fullName;
    private int age;

    {
        this.setId(GeneratorID.generateClientId());
    }

    public Client(String fullName, int age) {
        this.fullName = fullName;
        this.age = age;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
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
