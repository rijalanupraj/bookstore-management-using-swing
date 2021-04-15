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

    static ArrayList<Book> getOnlyAvailableBooks() {
        ArrayList<Book> allBooks = getAllBooks();
        ArrayList<Book> availableBooks = new ArrayList<Book>();

        for (int i = 0; i < allBooks.size(); i++) {
            Book book = allBooks.get(i);
            if (book.numAvailable > 0) {
                availableBooks.add(book);
            }
        }
        return availableBooks;

    }

    static ArrayList<Book> getOnlySoldBooks() {
        ArrayList<Book> allBooks = getAllBooks();
        ArrayList<Book> soldBooks = new ArrayList<Book>();

        for (int i = 0; i < allBooks.size(); i++) {
            Book book = allBooks.get(i);
            if (book.numSold > 0) {
                soldBooks.add(book);
            }
        }
        return soldBooks;

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

    static boolean sellBook(Book book, Integer quantity) {
        try {
            // DbConnect Class
            Connection con = DbConnect.connection();
            Integer numSold = book.numSold + quantity;
            Integer numAvailable = book.numAvailable - quantity;
            String query = "Update book SET num_available=?, num_sold=? WHERE id=?";
            PreparedStatement ins = con.prepareStatement(query);
            ins.setInt(1, numAvailable);
            ins.setInt(2, numSold);
            ins.setInt(3, book.id);
            boolean result = ins.execute();
            return !result;
        } catch (

        SQLException error) {
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

    public static ArrayList<Book> searchBooks(String query, String databaseField) {
        ArrayList<Book> allBooks = getAllBooks();
        ArrayList<Book> querySet = new ArrayList<Book>();
        String searchQuery = query.toLowerCase();
        for (int i = 0; i < allBooks.size(); i++) {
            Book book = allBooks.get(i);
            String databaseFieldData = getValueFromDatabaseFieldsForSearching(book, databaseField);

            boolean isEqual = databaseFieldData.equals(searchQuery);
            if (isEqual) {
                querySet.add(book);
            }
        }
        return querySet;

    }

    public static ArrayList<Book> sortBooks(String query, String databaseField) {
        ArrayList<Book> allBooks = getAllBooks();
        ArrayList<Book> querySet = new ArrayList<Book>();
        String searchQuery = query.toLowerCase();
        for (int i = 0; i < allBooks.size(); i++) {
            Book book = allBooks.get(i);
            String databaseFieldData = getValueFromDatabaseFieldsForSearching(book, databaseField);

            boolean isEqual = databaseFieldData.equals(searchQuery);
            if (isEqual) {
                querySet.add(book);
            }
        }
        return querySet;

    }

    public Object[][] getBooksInObjects() {
        ArrayList<Book> allBooks = getAllBooks();
        Object[][] books = new Object[allBooks.size()][7];
        for (int i = 0; i < allBooks.size(); i++) {

            Book current = allBooks.get(i);
            books[i][0] = current.id;
            books[i][1] = current.isbn;
            books[i][2] = current.title;
            books[i][3] = current.author;
            books[i][4] = current.publisher;
            books[i][5] = current.publishedDate;
            books[i][6] = current.numAvailable;
            books[i][7] = current.numSold;

        }
        return books;
    }

    // public void mergeSort(int arr[], int l, int r) {

    // if (l < r) {
    // int m = l + (r - l) / 2;
    // mergeSort(arr, l, m);
    // mergeSort(arr, m + 1, r);
    // merge(arr, l, m, r);
    // }
    // for (int i : arr) {
    // System.out.println(i);
    // }
    // }

    // public void merge(int arr[], int l, int m, int r) {
    // int n1 = m - l + 1;
    // int n2 = r - m;
    // int[] L = new int[n1];
    // int[] R = new int[n2];
    // for (int i = 0; i < n1; i++) {
    // L[i] = arr[l + i];
    // }
    // for (int i = 0; i < n2; i++) {
    // R[i] = arr[m + 1 + i];
    // }
    // int i = 0, j = 0, k = l;
    // while (i < n1 && j < n2) {
    // if (L[i] <= R[j]) {
    // arr[k++] = L[i++];
    // } else {
    // arr[k++] = R[j++];
    // }
    // }
    // while (i < n1) {
    // arr[k++] = L[i++];
    // }
    // while (j < n2) {
    // arr[k++] = R[j++];
    // }
    // }

    private static String getValueFromDatabaseFieldsForSearching(Book book, String databaseField) {
        String databaseFieldData = "";
        switch (databaseField) {
        case "title":
            databaseFieldData = book.title.toLowerCase();
            break;

        case "author":
            databaseFieldData = book.author.toLowerCase();
            break;

        case "publisher":
            databaseFieldData = book.publisher.toLowerCase();
            break;

        case "published_date":
            databaseFieldData = book.publishedDate.toLowerCase();
            break;
        }
        return databaseFieldData;
    }

}
