package com.senla.store.model;

import com.senla.store.util.GeneratorID;

import java.io.Serializable;

public class Client extends AEntity implements Serializable {
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
