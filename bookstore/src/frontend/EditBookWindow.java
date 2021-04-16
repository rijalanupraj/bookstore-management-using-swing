package frontend;
import backend.*;

// Import Swing Package
import javax.swing.*;
// Import Event Package
import java.awt.event.*;

public class EditBookWindow {
    Book book;

    EditBookWindow(Book book) {
        this.book = book;

        // Frame
        JFrame jEditBookFrame = new JFrame("Edit Book");
        // Label
        JLabel lISBN, lTitle, lAuthor, lPublisher, lPublishedDate, lPrice, lNumAvailable, lSoldBooks;
        // TextField
        JTextField tfISBN, tfTitle, tfAuthor, tfPublisher, tfPublishedDate, tfPrice, tfNumAvailable, tfSoldBooks;
        JButton btnSave, btnBack;

        // Label - ISBN
        lISBN = new JLabel("ISBN");
        lISBN.setBounds(130, 90, 300, 50);
        jEditBookFrame.add(lISBN);

        // TextField - ISBN
        tfISBN = new JTextField();
        tfISBN.setBounds(220, 100, 300, 30);
        jEditBookFrame.add(tfISBN);

        // Label - Title
        lTitle = new JLabel("Title");
        lTitle.setBounds(130, 130, 300, 50);
        jEditBookFrame.add(lTitle);

        // TextField - Title
        tfTitle = new JTextField();
        tfTitle.setBounds(220, 140, 300, 30);
        jEditBookFrame.add(tfTitle);

        // Label - Author
        lAuthor = new JLabel("Author");
        lAuthor.setBounds(130, 170, 300, 50);
        jEditBookFrame.add(lAuthor);

        // TextField - Author
        tfAuthor = new JTextField();
        tfAuthor.setBounds(220, 180, 300, 30);
        jEditBookFrame.add(tfAuthor);

        // Label - Publisher
        lPublisher = new JLabel("Publisher");
        lPublisher.setBounds(130, 210, 300, 50);
        jEditBookFrame.add(lPublisher);

        // TextField - Publisher
        tfPublisher = new JTextField();
        tfPublisher.setBounds(220, 220, 300, 30);
        jEditBookFrame.add(tfPublisher);

        // Label - PublishedDate
        lPublishedDate = new JLabel("Published Date");
        lPublishedDate.setBounds(130, 250, 300, 50);
        jEditBookFrame.add(lPublishedDate);

        // TextField - Date PublishedDate
        tfPublishedDate = new JTextField();
        tfPublishedDate.setBounds(220, 260, 300, 30);
        jEditBookFrame.add(tfPublishedDate);

        // Label - Num Available
        lNumAvailable = new JLabel("Available Books");
        lNumAvailable.setBounds(130, 290, 300, 50);
        jEditBookFrame.add(lNumAvailable);

        // TextField - Num Available
        tfNumAvailable = new JTextField();
        tfNumAvailable.setBounds(220, 300, 300, 30);
        jEditBookFrame.add(tfNumAvailable);

        // Label - Sold Books
        lSoldBooks = new JLabel("Sold Books");
        lSoldBooks.setBounds(130, 330, 300, 50);
        jEditBookFrame.add(lSoldBooks);

        // TextField - Sold Books
        tfSoldBooks = new JTextField();
        tfSoldBooks.setBounds(220, 340, 300, 30);
        jEditBookFrame.add(tfSoldBooks);

        // Label - Price
        lPrice = new JLabel("Price per pice");
        lPrice.setBounds(130, 370, 300, 50);
        jEditBookFrame.add(lPrice);

        // TextField - Price
        tfPrice = new JTextField();
        tfPrice.setBounds(220, 380, 300, 30);
        jEditBookFrame.add(tfPrice);

        // Save Button
        btnSave = new JButton("Save");
        btnSave.setBounds(290, 420, 100, 30);
        jEditBookFrame.add(btnSave);

        // Back Button
        btnBack = new JButton("Exit");
        btnBack.setBounds(10, 10, 100, 30);
        jEditBookFrame.add(btnBack);

        // Setting Initial Value to TextField
        tfISBN.setText(this.book.isbn);
        tfTitle.setText(this.book.title);
        tfAuthor.setText(this.book.author);
        tfPublisher.setText(this.book.publisher);
        tfPublishedDate.setText(this.book.publishedDate);
        String numOfBookSold = String.valueOf(this.book.numSold);
        String numOfAvailableBook = String.valueOf(this.book.numAvailable);
        tfSoldBooks.setText(numOfBookSold);
        tfNumAvailable.setText(numOfAvailableBook);
        tfPrice.setText(this.book.price.toString());

        // On Click - Back Button
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jEditBookFrame.dispose();
                new ViewBooksWindow();
            }
        });

        // On Click - Save Button
        btnSave.addActionListener(e -> {
            String isbn = tfISBN.getText();
            String title = tfTitle.getText();
            String author = tfAuthor.getText();
            String publisher = tfPublisher.getText();
            String publishedDate = tfPublishedDate.getText();
            String price = tfPrice.getText();
            String numAvailable = tfNumAvailable.getText();
            String numSold = tfSoldBooks.getText();

            // Validating the fields
            if (isbn.isEmpty() || title.isEmpty() || author.isEmpty() || publishedDate.isEmpty() || publisher.isEmpty()
                    || price.isEmpty() || numAvailable.isEmpty() || numSold.isEmpty()) {
                JOptionPane.showMessageDialog(jEditBookFrame, "Fields cannot be empty", "Alert",
                        JOptionPane.WARNING_MESSAGE);
            } else if (!Utils.isNumeric(numAvailable) || !Utils.isNumeric(numSold)) {
                JOptionPane.showMessageDialog(jEditBookFrame,
                        "Available Books & Sold Books should be a positive whole number", "Alert",
                        JOptionPane.WARNING_MESSAGE);
            } else if (!Utils.isNumberOrDouble(price) || !(Double.parseDouble(price) > 0)) {
                JOptionPane.showMessageDialog(jEditBookFrame, "The price should be greater than zero", "Alert",
                        JOptionPane.WARNING_MESSAGE);
            } else if (!Utils.isValidDate(publishedDate)) {
                JOptionPane.showMessageDialog(jEditBookFrame, "Invalid Date Format. Format:yyyy-MM-dd", "Alert",
                        JOptionPane.WARNING_MESSAGE);
            } else if (Book.checkISBNExists(isbn) && !isbn.equals(this.book.isbn)) {
                JOptionPane.showMessageDialog(jEditBookFrame, "The ISBN number already exists", "Alert",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                boolean result = Book.updateBook(this.book.id, isbn, title, author, publisher, publishedDate,
                        Double.parseDouble(price), Integer.parseInt(numAvailable), Integer.parseInt(numSold));
                if (result) {
                    JOptionPane.showMessageDialog(jEditBookFrame, "Updated Successfully");
                    jEditBookFrame.dispose();
                    new ViewBooksWindow();
                } else {
                    JOptionPane.showMessageDialog(jEditBookFrame, "Something went wrong", "Alert",
                            JOptionPane.WARNING_MESSAGE);
                }
            }

        });

        jEditBookFrame.setLayout(null);
        jEditBookFrame.setVisible(true);
        jEditBookFrame.setSize(600, 600);
        jEditBookFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

}
