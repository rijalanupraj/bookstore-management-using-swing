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
        JButton btnBack;

        // Header For table
        String header[] = { "ISBN", "Title", "Author", "Publisher", "Published Date", "Price", "Available", "Sold" };

        ArrayList<Book> booksArray = Book.getAllBooks();

        Object data[][] = new Object[booksArray.size()][header.length];

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
        bookViewScrollPane.setBounds(100, 200, 700, 300);

        // Back Button
        btnBack = new JButton("Back");
        btnBack.setBounds(10, 10, 100, 30);
        fViewBook.add(btnBack);

        // On Click - Back Button
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Dashboard();
                fViewBook.dispose();
            }
        });

        fViewBook.setSize(1000, 1000);
        fViewBook.setLayout(null);
        fViewBook.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fViewBook.setVisible(true);

    }

}
