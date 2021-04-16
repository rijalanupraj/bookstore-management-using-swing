package frontend;

import backend.*;

// Import Swing Package
import javax.swing.*;
// Import Event Package
import java.awt.event.*;

public class SellBookWindow {

    Book book;

    SellBookWindow(Book book) {
        this.book = book;
        // Frame
        JFrame fSellBookFrame = new JFrame("Edit Book");
        // Label
        JLabel lBookId, lBookTitle, lQuantity;
        JTextField tfQuantity;
        JButton btnSell;

        // Label - ID
        lBookId = new JLabel("Book ISBN: " + book.isbn);
        lBookId.setBounds(130, 90, 300, 50);
        fSellBookFrame.add(lBookId);

        // Label - Book Title
        lBookTitle = new JLabel("Book Title: " + book.title);
        lBookTitle.setBounds(130, 140, 300, 50);
        fSellBookFrame.add(lBookTitle);

        // Label - Book Quantity
        lQuantity = new JLabel("Quantity");
        lQuantity.setBounds(130, 200, 300, 50);
        fSellBookFrame.add(lQuantity);

        // TextField - ISBN
        tfQuantity = new JTextField();
        tfQuantity.setBounds(220, 200, 100, 30);
        fSellBookFrame.add(tfQuantity);

        // Save Button
        btnSell = new JButton("Save");
        btnSell.setBounds(290, 350, 100, 30);
        fSellBookFrame.add(btnSell);

        fSellBookFrame.setSize(500, 500);
        fSellBookFrame.setLayout(null);
        fSellBookFrame.setVisible(true);
        fSellBookFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // On Click - Save Button
        btnSell.addActionListener(e -> {
            String quantity = tfQuantity.getText();
            if (quantity.isEmpty()) {
                // Quantity should not be empty
                JOptionPane.showMessageDialog(fSellBookFrame, "Fields cannot be empty", "Alert",
                        JOptionPane.WARNING_MESSAGE);
            } else if (!Utils.isNumeric(quantity)) {
                // Quantity Should be numeric
                JOptionPane.showMessageDialog(fSellBookFrame, "Quantity should be positive whole number", "Alert",
                        JOptionPane.WARNING_MESSAGE);
            } else if (Integer.parseInt(quantity) > this.book.numAvailable) {
                // Quantity should be lesser than the book available
                String message = "Only " + String.valueOf(this.book.numAvailable) + " Books Available";
                JOptionPane.showMessageDialog(fSellBookFrame, message, "Alert", JOptionPane.WARNING_MESSAGE);
            } else {
                // Sell the book and update in database
                boolean result = Book.sellBook(this.book, Integer.parseInt(quantity));
                if (result) {
                    JOptionPane.showMessageDialog(fSellBookFrame, "Book Sold");
                    fSellBookFrame.dispose();
                    new ViewBooksWindow();
                } else {
                    JOptionPane.showMessageDialog(fSellBookFrame, "Something went wrong", "Alert",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

    }

}
