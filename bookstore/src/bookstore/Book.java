package bookstore;

public class Book {
    int isbn;
    String title;
    String author;
    String publisher;
    int publishedDate;
    Double price;
    int numAvailable;
    int numSold;

    public Book(int isbn, String title, String author, String publisher, int publishedDate, Double price,
            int numAvailable, int numSold) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.price = price;
        this.numAvailable = numAvailable;
        this.numSold = numSold;
    }
}
