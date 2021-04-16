package backend;

//Import Sql Package
import java.sql.*;

// Import ArrayList Class
import java.util.ArrayList;

public class Book {
    public int id, numAvailable, numSold;
    public String isbn, title, author, publisher, publishedDate;
    public Double price;

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

    public static ArrayList<Book> getAllBooks() {
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

    public static ArrayList<Book> getOnlyAvailableBooks() {
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

    public static ArrayList<Book> getOnlySoldBooks() {
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

    public static boolean checkISBNExists(String isbn) {
        ArrayList<Book> allBooks = getAllBooks();
        for (int i = 0; i < allBooks.size(); i++) {
            Book book = allBooks.get(i);
            if (book.isbn.equals(isbn)) {
                return true;
            }
        }
        return false;
    }

    public static boolean addNewBook(String isbn, String title, String author, String publisher, String publishedDate,
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

    public static boolean deleteBook(int id) {
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

    public static boolean sellBook(Book book, Integer quantity) {
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

    public static boolean updateBook(int id, String isbn, String title, String author, String publisher, String publishedDate,
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

    public static ArrayList<Book> convertObjectToArrayList(Object[][] booksObjects) {
        ArrayList<Book> booksList = new ArrayList<Book>();
        for (int i = 0; i < booksObjects.length; i++) {

            Integer id = (int) booksObjects[i][0];
            String isbn = booksObjects[i][1].toString();
            String title = booksObjects[i][2].toString();
            String author = booksObjects[i][3].toString();
            String publisher = booksObjects[i][4].toString();
            String publishedDate = booksObjects[i][5].toString();
            Double price = (double) booksObjects[i][6];
            int numAvailable = (int) booksObjects[i][7];
            int numSold = (int) booksObjects[i][8];
            Book newObj = new Book(id, isbn, title, author, publisher, publishedDate, price, numAvailable, numSold);
            booksList.add(newObj);
        }
        return booksList;
    }

    public static Object[][] getBooksInObjects(ArrayList<Book> booksList) {
        Object[][] books = new Object[booksList.size()][9];
        for (int i = 0; i < booksList.size(); i++) {

            Book current = booksList.get(i);
            books[i][0] = current.id;
            books[i][1] = current.isbn;
            books[i][2] = current.title;
            books[i][3] = current.author;
            books[i][4] = current.publisher;
            books[i][5] = current.publishedDate;
            books[i][6] = current.price;
            books[i][7] = current.numAvailable;
            books[i][8] = current.numSold;

        }
        return books;
    }

    public static Object[][] performSortOperation(ArrayList<Book> booksList, int index) {

        Object[][] books = getBooksInObjects(booksList);
        sort(books, 0, books.length - 1, index);
        return books;

    }

    public static void merge(Object arr[][], int left, int mid, int right, int index) {
        // Find sizes of two subarrays to be merged
        int n1 = mid - left + 1;
        int n2 = right - mid;
        // Create temp arrays
        Object[][] left_arr = new Object[n1][9];
        Object[][] right_arr = new Object[n2][9];
        // Copy data to temp arrays
        for (int i = 0; i < n1; ++i)
            left_arr[i] = arr[left + i];
        for (int j = 0; j < n2; ++j)
            right_arr[j] = arr[mid + 1 + j];
        // Merge the temp arrays
        // Initial indexes of first and second subarrays
        int i = 0, j = 0;
        // Initial index of merged subarry array
        int k = left;
        while (i < n1 && j < n2) {
            boolean compare = false;
            if (index == 0 || index == 7 || index == 8) {
                Integer integerField = (int) left_arr[i][index];
                compare = Integer.compare(integerField, (int) right_arr[j][index]) < 0;

            } else if (index == 6) {
                Double doubleField = (Double) left_arr[i][index];
                compare = Double.compare(doubleField, (Double) right_arr[j][index]) < 0;
            }

            else {
                String stringField = (String) left_arr[i][index];
                compare = stringField.compareToIgnoreCase((String) right_arr[j][index]) < 0;
            }
            if (compare) {
                arr[k] = left_arr[i];
                i++;
            } else {
                arr[k] = right_arr[j];
                j++;
            }

            k++;
        }
        // Copy remaining elements of left_arr[] if any
        while (i < n1) {
            arr[k] = left_arr[i];
            i++;
            k++;
        }
        // Copy remaining elements of right_arr[] if any
        while (j < n2) {
            arr[k] = right_arr[j];
            j++;
            k++;
        }
    }

    // sort two halves and merge
    public static void sort(Object arr[][], int left, int right, int index) {
        if (left < right) {
            // Find the middle point
            int mid = left + (right - left) / 2;
            // Sort first and second halves
            sort(arr, left, mid, index);
            sort(arr, mid + 1, right, index);
            // Merge the sorted halves
            merge(arr, left, mid, right, index);
        }
    }

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
