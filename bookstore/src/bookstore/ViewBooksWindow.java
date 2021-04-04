package bookstore;

// Swing
import javax.swing.*;
// Table Model
import javax.swing.table.TableModel;
// Array List
import java.util.ArrayList;
// Import Event Package
import java.awt.event.*;

public class ViewBooksWindow {

    ViewBooksWindow() {
        JFrame fViewBook = new JFrame("View Book");
        JButton btnBack, btnDeleteBook, btnEditBook;

        // Header For table
        String header[] = { "ISBN", "Title", "Author", "Publisher", "Published Date", "Price", "Available", "Sold" };

        // Getting all books from Database using book class
        ArrayList<Book> booksArray = Book.getAllBooks();

        // Data to show in the table
        Object data[][] = new Object[booksArray.size()][header.length];

        // For loop to assign value to the data
        for (int i = 0; i < booksArray.size(); i++) {
            Book row = booksArray.get(i);
            data[i][0] = row.isbn;
            data[i][1] = row.title;
            data[i][2] = row.author;
            data[i][3] = row.publisher;
            data[i][4] = row.publishedDate;
            data[i][5] = row.price;
            data[i][6] = row.numAvailable;
            data[i][7] = row.numSold;
        }

        // Table it will take two parameter: data && header
        JTable jBookViewTable = new JTable(data, header);
        JScrollPane bookViewScrollPane = new JScrollPane(jBookViewTable);
        fViewBook.add(bookViewScrollPane);
        bookViewScrollPane.setBounds(100, 200, 700, 400);

        // Back Button
        btnBack = new JButton("Back");
        btnBack.setBounds(10, 10, 100, 30);
        fViewBook.add(btnBack);

        // Delete Button
        btnDeleteBook = new JButton("Delete");
        btnDeleteBook.setBounds(100, 650, 200, 30);
        fViewBook.add(btnDeleteBook);

        // Edit Button
        btnEditBook = new JButton("Edit");
        btnEditBook.setBounds(350, 650, 200, 30);
        fViewBook.add(btnEditBook);

        // On Click - Back Button
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Dashboard();
                fViewBook.dispose();
            }
        });

        // On Click - Delete Book
        btnDeleteBook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = jBookViewTable.getSelectedRow();
                if (row >= 0) {
                    int bookId = booksArray.get(row).id;
                    boolean result = Book.deleteBook(bookId);
                    if (result) {
                        JOptionPane.showMessageDialog(fViewBook, "Book Deleted");
                        new ViewBooksWindow();
                        fViewBook.dispose();

                    } else {
                        JOptionPane.showMessageDialog(fViewBook, "Something went wrong", "Alert",
                                JOptionPane.WARNING_MESSAGE);
                    }

                } else {
                    JOptionPane.showMessageDialog(fViewBook, "Select the Row first");
                }

            }
        });

        // On Click - Edit Book
        btnEditBook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = jBookViewTable.getSelectedRow();
                if (row >= 0) {
                    Book bookInstance = booksArray.get(row);
                    fViewBook.dispose();
                    new EditBookWindow(bookInstance);

                } else {
                    JOptionPane.showMessageDialog(fViewBook, "Select the Row first");
                }

            }
        });

        fViewBook.setSize(1000, 1000);
        fViewBook.setLayout(null);
        fViewBook.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fViewBook.setVisible(true);

    }

}
