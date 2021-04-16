package frontend;

import backend.*;

// Import Swing Package
import javax.swing.*;
// Import Event Package
import java.awt.event.*;

public class AddBookWindow {

    AddBookWindow() {
        // Frame
        JFrame jAddBookFrame = new JFrame("New Book");
        // Label
        JLabel lISBN, lTitle, lAuthor, lPublisher, lPublishedDate, lPrice, lQuantity;
        // TextField
        JTextField tfISBN, tfTitle, tfAuthor, tfPublisher, tfPublishedDate, tfPrice, tfQuantity;
        JButton btnSave, btnBack;

        // Label - ISBN
        lISBN = new JLabel("ISBN");
        lISBN.setBounds(130, 90, 300, 50);
        jAddBookFrame.add(lISBN);

        // TextField - ISBN
        tfISBN = new JTextField();
        tfISBN.setBounds(220, 100, 300, 30);
        jAddBookFrame.add(tfISBN);

        // Label - Title
        lTitle = new JLabel("Title");
        lTitle.setBounds(130, 130, 300, 50);
        jAddBookFrame.add(lTitle);

        // TextField - Title
        tfTitle = new JTextField();
        tfTitle.setBounds(220, 140, 300, 30);
        jAddBookFrame.add(tfTitle);

        // Label - Author
        lAuthor = new JLabel("Author");
        lAuthor.setBounds(130, 170, 300, 50);
        jAddBookFrame.add(lAuthor);

        // TextField - Author
        tfAuthor = new JTextField();
        tfAuthor.setBounds(220, 180, 300, 30);
        jAddBookFrame.add(tfAuthor);

        // Label - Publisher
        lPublisher = new JLabel("Publisher");
        lPublisher.setBounds(130, 210, 300, 50);
        jAddBookFrame.add(lPublisher);

        // TextField - Publisher
        tfPublisher = new JTextField();
        tfPublisher.setBounds(220, 220, 300, 30);
        jAddBookFrame.add(tfPublisher);

        // Label - PublishedDate
        lPublishedDate = new JLabel("Published Date");
        lPublishedDate.setBounds(130, 250, 300, 50);
        jAddBookFrame.add(lPublishedDate);

        // TextField - Date PublishedDate
        tfPublishedDate = new JTextField();
        tfPublishedDate.setBounds(220, 260, 300, 30);
        jAddBookFrame.add(tfPublishedDate);

        // Label - Quantity
        lQuantity = new JLabel("Quantity");
        lQuantity.setBounds(130, 290, 300, 50);
        jAddBookFrame.add(lQuantity);

        // TextField - Quantity
        tfQuantity = new JTextField();
        tfQuantity.setBounds(220, 300, 300, 30);
        jAddBookFrame.add(tfQuantity);

        // Label - Price
        lPrice = new JLabel("Price per pice");
        lPrice.setBounds(130, 330, 300, 50);
        jAddBookFrame.add(lPrice);

        // TextField - Price
        tfPrice = new JTextField();
        tfPrice.setBounds(220, 340, 300, 30);
        jAddBookFrame.add(tfPrice);

        // Save Button
        btnSave = new JButton("Save");
        btnSave.setBounds(290, 400, 100, 30);
        jAddBookFrame.add(btnSave);

        // Back Button
        btnBack = new JButton("Back");
        btnBack.setBounds(10, 10, 100, 30);
        jAddBookFrame.add(btnBack);

        // On Click - Back Button
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Dashboard();
                jAddBookFrame.dispose();
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
            String quantity = tfQuantity.getText();

            // Checking if it is empty
            if (isbn.isEmpty() || title.isEmpty() || author.isEmpty() || publishedDate.isEmpty() || publisher.isEmpty()
                    || price.isEmpty() || quantity.isEmpty()) {
                JOptionPane.showMessageDialog(jAddBookFrame, "Fields cannot be empty", "Alert",
                        JOptionPane.WARNING_MESSAGE);
            } else if (!Utils.isNumeric(quantity)) {
                // Checking if the quantity is positive whole number
                JOptionPane.showMessageDialog(jAddBookFrame, "Quantity should be whole number", "Alert",
                        JOptionPane.WARNING_MESSAGE);
            } else if (!Utils.isNumberOrDouble(price) || !(Double.parseDouble(price) > 0)) {
                // Checking if the price is numeric or double
                JOptionPane.showMessageDialog(jAddBookFrame, "The price should be greater than zero", "Alert",
                        JOptionPane.WARNING_MESSAGE);
            } else if (!Utils.isValidDate(publishedDate)) {
                // CHecking if the date has correct format. Using method from utils class
                JOptionPane.showMessageDialog(jAddBookFrame, "Invalid Date Format. Format:yyyy-MM-dd", "Alert",
                        JOptionPane.WARNING_MESSAGE);
            } else if (Book.checkISBNExists(isbn)) {
                // Checking if the ISBN number already exists or Not
                JOptionPane.showMessageDialog(jAddBookFrame, "The ISBN number already exists", "Alert",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                // Adding the book to the database
                boolean result = Book.addNewBook(isbn, title, author, publisher, publishedDate,
                        Double.parseDouble(price), Integer.parseInt(quantity));
                if (result) {
                    JOptionPane.showMessageDialog(jAddBookFrame, "Book Added Successfully");
                    tfISBN.setText("");
                    tfTitle.setText("");
                    tfAuthor.setText("");
                    tfPublishedDate.setText("");
                    tfPublisher.setText("");
                    tfPrice.setText("");
                    tfQuantity.setText("");
                } else {
                    JOptionPane.showMessageDialog(jAddBookFrame, "Something went wrong", "Alert",
                            JOptionPane.WARNING_MESSAGE);
                }
            }

        });

        jAddBookFrame.setLayout(null);
        jAddBookFrame.setVisible(true);
        jAddBookFrame.setSize(600, 600);
        jAddBookFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

}
