package by.senla.bookstore.model;

import by.senla.bookstore.util.GeneratorID;

import java.util.Objects;
import java.util.Random;

public class Book extends AEntity {
    private String title;
    private String author;
    private int price = new Random().nextInt(100);
    private int publicationYear = new Random().nextInt(2021);
    private BookStatus status;

    {
        this.setId(GeneratorID.generateBookId());
    }

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public Book(String title, String author, BookStatus status) {
        this.title = title;
        this.author = author;
        this.status = status;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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
        return "Book #" + this.getId() + " >>> \t" + title + '\'' +
                "\t'" + author + '\'' +
                "\t " + publicationYear + " year" +
                '\t' + price + "$" +
                '\t' + status;
    }
}
