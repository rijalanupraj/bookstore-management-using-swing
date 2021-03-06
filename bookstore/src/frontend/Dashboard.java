package frontend;
import backend.*;

// Import Swing Package
import javax.swing.*;
// Import Event Package
import java.awt.event.*;

public class Dashboard {

    Dashboard() {
        // Frame
        JFrame fDashboardFrame = new JFrame("Dashboard");

        // jButton
        JButton btnAddBookWindow, btnViewBookWindow;

        // Add Book Window Button
        btnAddBookWindow = new JButton("Add Book");
        btnAddBookWindow.setBounds(100, 100, 200, 60);
        fDashboardFrame.add(btnAddBookWindow);

        // View Book Window Button
        btnViewBookWindow = new JButton("View Books");
        btnViewBookWindow.setBounds(320, 100, 200, 60);
        fDashboardFrame.add(btnViewBookWindow);

        // On Click - Add Book Button
        btnAddBookWindow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AddBookWindow();
                fDashboardFrame.dispose();
            }
        });

        // On Click - View Book Button
        btnViewBookWindow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ViewBooksWindow();
                fDashboardFrame.dispose();
            }
        });

        fDashboardFrame.setLayout(null);
        fDashboardFrame.setVisible(true);
        fDashboardFrame.setSize(700, 500);

        // Stop the program once the cross button is clicked
        fDashboardFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}
