package frontend;

import backend.*;

// Swing
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

// Array List
import java.util.ArrayList;
import java.util.Collections;
// Import Event Package
import java.awt.event.*;

public class ViewBooksWindow {

    ViewBooksWindow() {
        JFrame fViewBook = new JFrame("View Book");
        JLabel lSearchByLabel, lSortByLabel, lSortByOrderLabel;
        JTextField tfSearchTextInput;
        JButton btnBack, btnSearch, btnOnlySort, btnDeleteBook, btnEditBook, btnViewAvailable, btnViewSold, btnSell,
                btnRefresh;
        JComboBox<String> cbSearchByBox, cbSortByBox;
        JCheckBox chbSortOrderBox;

        // Header For table
        String header[] = { "ISBN", "Title", "Author", "Publisher", "Published Date", "Price", "Available", "Sold" };

        // Getting all books from Database using book class
        ArrayList<Book> booksArray = Book.getAllBooks();

        // Data to show in the table
        Object data[][] = assignDataToTable(booksArray, header);

        // Table it will take two parameter: data && header
        JTable jBookViewTable = new JTable(data, header);
        JScrollPane bookViewScrollPane = new JScrollPane(jBookViewTable);
        fViewBook.add(bookViewScrollPane);
        bookViewScrollPane.setBounds(100, 200, 700, 400);

        // Label - Search By
        lSearchByLabel = new JLabel("Search By");
        lSearchByLabel.setBounds(100, 60, 100, 30);
        fViewBook.add(lSearchByLabel);

        // Label - Sort By
        lSortByLabel = new JLabel("Sort By");
        lSortByLabel.setBounds(220, 60, 100, 30);
        fViewBook.add(lSortByLabel);

        // Label - Sort By Order
        lSortByOrderLabel = new JLabel("Order By");
        lSortByOrderLabel.setBounds(340, 60, 100, 30);
        fViewBook.add(lSortByOrderLabel);

        // Search By Fields
        String searchByDatabaseFields[] = { "title", "author", "publisher", "published_date" };
        String searchByFields[] = { "Title", "Author", "Publisher", "Date" };
        // Combo Box - Search By
        cbSearchByBox = new JComboBox<String>(searchByFields);
        cbSearchByBox.setBounds(100, 100, 100, 30);
        fViewBook.add(cbSearchByBox);

        // Sort By Fields
        String sortByFields[] = { "Default", "ISBN", "Title", "Author", "Publisher", "Published Date", "Price",
                "Available", "Sold" };
        // Combo Box - Sort By
        cbSortByBox = new JComboBox<String>(sortByFields);
        cbSortByBox.setBounds(220, 100, 100, 30);
        fViewBook.add(cbSortByBox);

        // CheckBox - Sort Order Ascending||Descending
        chbSortOrderBox = new JCheckBox("Descending");
        chbSortOrderBox.setBounds(340, 100, 100, 30);
        fViewBook.add(chbSortOrderBox);

        // Main Search Input Box
        tfSearchTextInput = new JTextField();
        tfSearchTextInput.setBounds(450, 100, 300, 30);
        fViewBook.add(tfSearchTextInput);

        // SearchButton
        btnSearch = new JButton("Search and Sort");
        btnSearch.setBounds(400, 140, 200, 30);
        fViewBook.add(btnSearch);

        // Only Sort Button
        btnOnlySort = new JButton("Sort Only");
        btnOnlySort.setBounds(670, 140, 100, 30);
        fViewBook.add(btnOnlySort);

        // Back Button
        btnBack = new JButton("Back");
        btnBack.setBounds(10, 10, 100, 30);
        fViewBook.add(btnBack);

        // Delete Button
        btnDeleteBook = new JButton("Delete");
        btnDeleteBook.setBounds(100, 650, 150, 30);
        fViewBook.add(btnDeleteBook);

        // Edit Button
        btnEditBook = new JButton("Edit");
        btnEditBook.setBounds(280, 650, 150, 30);
        fViewBook.add(btnEditBook);

        // View Available Button
        btnViewAvailable = new JButton("View Available");
        btnViewAvailable.setBounds(470, 650, 150, 30);
        fViewBook.add(btnViewAvailable);

        // View Sold Button
        btnViewSold = new JButton("View Sold");
        btnViewSold.setBounds(650, 650, 150, 30);
        fViewBook.add(btnViewSold);

        // Sell Button
        btnSell = new JButton("Sell This Book");
        btnSell.setBounds(390, 700, 150, 30);
        fViewBook.add(btnSell);

        // Refresh Page Button
        btnRefresh = new JButton("Refresh");
        btnRefresh.setBounds(100, 140, 150, 30);
        fViewBook.add(btnRefresh);

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

        // On Click - Sell book
        btnSell.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = jBookViewTable.getSelectedRow();
                if (row >= 0) {
                    Book bookInstance = booksArray.get(row);
                    fViewBook.dispose();
                    new SellBookWindow(bookInstance);

                } else {
                    JOptionPane.showMessageDialog(fViewBook, "Select the Row first");
                }

            }
        });

        // On Click - Sort
        btnOnlySort.addActionListener(e -> {
            // Get the index from the sort by Combo Box
            int sortByTextComboBoxIndex = cbSortByBox.getSelectedIndex();
            // Get the boolean value from checkbox
            boolean isDescending = chbSortOrderBox.isSelected();
            // Perform Sort Operation
            Object[][] sortedArray = Book.performSortOperation(booksArray, sortByTextComboBoxIndex);
            ArrayList<Book> querySet = Book.convertObjectToArrayList(sortedArray);
            if (isDescending) {
                Collections.reverse(querySet);
            }
            if (querySet.isEmpty()) {
                JOptionPane.showMessageDialog(fViewBook, "Something went wrong", "Alert", JOptionPane.WARNING_MESSAGE);
            } else {
            booksArray.clear();
            booksArray.addAll(querySet);
            // Assigning the sorted data to JTable
            Object queryData[][] = assignDataToTable(querySet, header);
            DefaultTableModel a = new DefaultTableModel(queryData, header);
            jBookViewTable.setModel(a);
            }

        });

        // On Click - Search
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get query String
                String searchBoxText = tfSearchTextInput.getText();
                // Get The combo box index for search
                int searchByTextComboBoxIndex = cbSearchByBox.getSelectedIndex();
                int sortByTextComboBoxIndex = cbSortByBox.getSelectedIndex();
                String searchByText = searchByDatabaseFields[searchByTextComboBoxIndex];
                boolean descending = chbSortOrderBox.isSelected();
                if (searchBoxText.isEmpty()) {
                    JOptionPane.showMessageDialog(fViewBook, "First Enter text to search");
                } else {
                    // Search
                    ArrayList<Book> afterSearch = Book.performBookSearch(searchBoxText, searchByText);
                    // Sort
                    Object[][] sortedArray = Book.performSortOperation(afterSearch, sortByTextComboBoxIndex);
                    ArrayList<Book> querySet = Book.convertObjectToArrayList(sortedArray);
                    if (descending) {
                        Collections.reverse(querySet);
                    }
                    
                    if (querySet.isEmpty()) {
                        JOptionPane.showMessageDialog(fViewBook, "Nothing Found", "Alert", JOptionPane.WARNING_MESSAGE);
                    } else {
                        // Assigning the searched and sorted data to JTable
                    	booksArray.clear();
                        booksArray.addAll(querySet);
                        Object queryData[][] = assignDataToTable(querySet, header);
                        DefaultTableModel a = new DefaultTableModel(queryData, header);
                        jBookViewTable.setModel(a);
                    }
                }

            }
        });

        // On Click - Btn Available
        btnViewAvailable.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArrayList<Book> querySet = Book.getOnlyAvailableBooks();
                booksArray.clear();
                booksArray.addAll(querySet);
                if (querySet.isEmpty()) {
                    JOptionPane.showMessageDialog(fViewBook, "Nothing Found", "Alert", JOptionPane.WARNING_MESSAGE);
                } else {
                    Object queryData[][] = assignDataToTable(querySet, header);
                    DefaultTableModel a = new DefaultTableModel(queryData, header);
                    jBookViewTable.setModel(a);
                }
            }
        });

        // On Click - Btn Available
        btnViewSold.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArrayList<Book> querySet = Book.getOnlySoldBooks();
                booksArray.clear();
                booksArray.addAll(querySet);
                if (querySet.isEmpty()) {
                    JOptionPane.showMessageDialog(fViewBook, "Nothing Found", "Alert", JOptionPane.WARNING_MESSAGE);
                } else {
                    Object queryData[][] = assignDataToTable(querySet, header);
                    DefaultTableModel a = new DefaultTableModel(queryData, header);
                    jBookViewTable.setModel(a);
                }
            }
        });

        // Button - Refresh
        btnRefresh.addActionListener(e -> {
            ArrayList<Book> querySet = Book.getAllBooks();
            booksArray.clear();
            booksArray.addAll(querySet);
            Object queryData[][] = assignDataToTable(querySet, header);
            DefaultTableModel a = new DefaultTableModel(queryData, header);
            jBookViewTable.setModel(a);
        });

        fViewBook.setSize(900, 800);
        fViewBook.setLayout(null);
        fViewBook.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fViewBook.setVisible(true);

    }

    // Changing to 2d Array to Display in JTable
    private Object[][] assignDataToTable(ArrayList<Book> booksArray, String[] header) {
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
        return data;
    }

    public static void main(String[] args) {
        new ViewBooksWindow();
    }

}
