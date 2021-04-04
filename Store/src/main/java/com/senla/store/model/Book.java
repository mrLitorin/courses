package com.senla.store.model;

import com.senla.store.util.GeneratorID;
import com.senla.store.util.MyRandom;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Book extends AEntity implements Serializable {
    private long id;
    private final String title;
    private final String author;
    private final int publicationYear = MyRandom.getPublicationYear();
    private int price = MyRandom.getPriceOfBook();
    private int quantity;
    private BookStatus status;
    private LocalDateTime dateOfLastSale;
    private String description;

    {
        this.setId(GeneratorID.generateBookId());
    }

    public Book(String title, String author, int quantity) {
        this.title = title;
        this.author = author;
        this.quantity = quantity;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateOfLastSale() {
        return dateOfLastSale;
    }

    public void setDateOfLastSale(LocalDateTime dateOfLastSale) {
        this.dateOfLastSale = dateOfLastSale;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity >= 0) {
            this.quantity = quantity;
        } else {
            System.out.println("Invalid input");
        }
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return title.equals(book.title) && author.equals(book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, price, status);
    }

    @Override
    public String toString() {
        String df = dateOfLastSale.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss"));

        return "Book #" + this.getId() + " " + title + '\'' +
                "\t'" + author + '\'' +
                "\t " + publicationYear + "year" +
                '\t' + price + "$" +
                '\t' + quantity + "pcs" +
                '\t' + status
                + "\t Last sale >> " + df;
    }
}
