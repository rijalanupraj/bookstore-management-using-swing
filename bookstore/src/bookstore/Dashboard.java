package bookstore;

// Import Swing Package
import javax.swing.*;
// Import Event Package
import java.awt.event.*;

public class Dashboard {

    Dashboard() {
        // Frame
        JFrame fDashboardFrame = new JFrame("Dashboard");

        // jButton
        JButton btnAddBookWindow;

        btnAddBookWindow = new JButton("Add Book");
        btnAddBookWindow.setBounds(100, 100, 200, 60);
        fDashboardFrame.add(btnAddBookWindow);

        // On Click - Add Book Button
        btnAddBookWindow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AddBookWindow();
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
