package bookstore;

//Import Sql Package
import java.sql.*;

// Import ArrayList Class
import java.util.ArrayList;

public class Book {
    int id;
    String isbn;
    String title;
    String author;
    String publisher;
    String publishedDate;
    Double price;
    int numAvailable;
    int numSold;

    public Book(int id, String isbn, String title, String author, String publisher, String publishedDate, Double price,
            int numAvailable, int numSold) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.price = price;
        this.numAvailable = numAvailable;
        this.numSold = numSold;
    }

    static ArrayList<Book> getAllBooks() {
        ArrayList<Book> bookArray = new ArrayList<Book>();

        String query = "SELECT * FROM book";

        try {
            Connection con = DbConnect.connection();
            PreparedStatement ins = con.prepareStatement(query);
            ResultSet rs = ins.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String isbn = rs.getString("isbn");
                String title = rs.getString("title");
                String author = rs.getString("author");
                String publisher = rs.getString("publisher");
                String publishedDate = rs.getString("published_date");
                Double price = rs.getDouble("price");
                int numAvailable = rs.getInt("num_available");
                int numSold = rs.getInt("num_sold");

                Book book = new Book(id, isbn, title, author, publisher, publishedDate, price, numAvailable, numSold);
                bookArray.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookArray;
    }

    static boolean checkISBNExists(String isbn) {
        ArrayList<Book> allBooks = getAllBooks();
        for (int i = 0; i < allBooks.size(); i++) {
            Book book = allBooks.get(i);
            if (book.isbn.equals(isbn)) {
                return true;
            }
        }
        return false;
    }

    static boolean addNewBook(String isbn, String title, String author, String publisher, String publishedDate,
            Double price, int quantity) {
        try {
            // DbConnect Class
            Connection con = DbConnect.connection();

            String query = "INSERT INTO book(isbn, title, author, publisher, published_date, price, num_available) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement ins = con.prepareStatement(query);
            ins.setString(1, isbn);
            ins.setString(2, title);
            ins.setString(3, author);
            ins.setString(4, publisher);
            ins.setString(5, publishedDate);
            ins.setDouble(6, price);
            ins.setInt(7, quantity);
            boolean result = ins.execute();
            return !result;

        } catch (SQLException error) {
            System.out.println(error);
            return false;
        }
    }

    static boolean deleteBook(int id) {
        try {
            // DbConnect Class
            Connection con = DbConnect.connection();

            // Delete Query
            String query = "DELETE FROM book WHERE id=?";
            PreparedStatement ins = con.prepareStatement(query);
            ins.setInt(1, id);
            boolean result = ins.execute();
            return !result;

        } catch (SQLException error) {
            System.out.println(error);
            return false;
        }
    }

    static boolean updateBook(int id, String isbn, String title, String author, String publisher, String publishedDate,
            Double price, int numAvailable, int numSold) {
        try {
            // DbConnect Class
            Connection con = DbConnect.connection();

            String query = "Update book SET isbn=?, title=?, author=?, publisher=?, published_date=?, price=?, num_available=?, num_sold=? WHERE id=?";
            PreparedStatement ins = con.prepareStatement(query);
            ins.setString(1, isbn);
            ins.setString(2, title);
            ins.setString(3, author);
            ins.setString(4, publisher);
            ins.setString(5, publishedDate);
            ins.setDouble(6, price);
            ins.setInt(7, numAvailable);
            ins.setInt(8, numSold);
            ins.setInt(9, id);
            boolean result = ins.execute();
            return !result;

        } catch (SQLException error) {
            System.out.println(error);
            return false;
        }
    }

}
