package by.senla.bookstore.model;

import by.senla.bookstore.util.GeneratorID;
import by.senla.bookstore.util.MyRandom;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Book extends AEntity {
    private String title;
    private String author;
    private int publicationYear = MyRandom.getPublicationYear();
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


    public String getDescription() {
        return description;
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
