package frontend;

import backend.*;

// Import Swing Package
import javax.swing.*;
// Import Event Package
import java.awt.event.*;
import java.awt.*;

public class Register {

    JFrame fRegisterFrame = new JFrame("Register");
    JLabel lUsername, lPassword, lConfirmPassword;
    JTextField tfUsername;
    JPasswordField pfPassword, pfConfirmPassword;
    JButton btnLogin, btnSignUp;
    JPanel panel;

    Register() {

        // Using GridBag Layout
        panel = new JPanel();
        panel.setSize(500, 500);
        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();

        // Label - Username
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 20;
        lUsername = new JLabel("Username");
        panel.add(lUsername, gbc);

        // TextField - Username
        gbc.gridx = 1;
        gbc.gridy = 0;
        tfUsername = new JTextField();
        tfUsername.setPreferredSize(new Dimension(150, 30));
        panel.add(tfUsername, gbc);

        // -------------- Username Ends ----------------------

        // -------------- Password Starts --------------------
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Label - Password
        gbc.insets = new Insets(10, 0, 0, 0); // top padding
        gbc.gridx = 0;
        gbc.gridy = 1;
        lPassword = new JLabel("Password");
        panel.add(lPassword, gbc);

        // TextField - Password
        gbc.gridx = 1;
        gbc.gridy = 1;
        pfPassword = new JPasswordField();
        pfPassword.setPreferredSize(new Dimension(150, 30));
        panel.add(pfPassword, gbc);

        // -------------- Password Ends ----------------------

        // -------------- Confirm Password Starts --------------------
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Label - Confirm Password
        gbc.insets = new Insets(10, 0, 0, 0); // top padding
        gbc.gridx = 0;
        gbc.gridy = 2;
        lConfirmPassword = new JLabel("Confirm Password");
        panel.add(lConfirmPassword, gbc);

        // TextField - Confirm Password
        gbc.gridx = 1;
        gbc.gridy = 2;
        pfConfirmPassword = new JPasswordField();
        pfConfirmPassword.setPreferredSize(new Dimension(150, 30));
        panel.add(pfConfirmPassword, gbc);

        // -------------- Confirm Password Ends ----------------------

        // -------------- Button Starts --------------------
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Button - Register
        gbc.insets = new Insets(10, 0, 0, 0); // top padding
        btnSignUp = new JButton("Create A Account");
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(btnSignUp, gbc);

        // Button - Login
        gbc.insets = new Insets(10, 0, 0, 0); // top padding
        btnLogin = new JButton("Have A Account");
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(btnLogin, gbc);

        // -------------- Button Ends ----------------------

        fRegisterFrame.add(panel);
        // fRegisterFrame.setLayout(null);
        fRegisterFrame.setVisible(true);
        fRegisterFrame.setSize(500, 500);

        // Stop the program once the cross button is clicked
        fRegisterFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // On SignUp Button Clicked
        btnSignUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                boolean connectionWorking = DbConnect.isConnectionWorking();
                // Checking if the DataBase Connection is Working
                if (!connectionWorking) {
                    JOptionPane.showMessageDialog(fRegisterFrame, "Database Connection Not Working", "Alert",
                            JOptionPane.WARNING_MESSAGE);
                } else {

                    String username = tfUsername.getText();
                    String password = pfPassword.getText();
                    String confirmPassword = pfPassword.getText();
                    // Checking if the field is empty
                    if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                        JOptionPane.showMessageDialog(fRegisterFrame, "Field cannot be empty");
                    } else if (password.length() < 4) {
                        // Password cannot be lesser than 4 characters
                        JOptionPane.showMessageDialog(fRegisterFrame, "Password should be at least 4 characters");
                    } else if (!password.equals(confirmPassword)) {
                        // Password and confirm Password should be same
                        JOptionPane.showMessageDialog(fRegisterFrame, "Passwords didn't match");
                    } else {

                        User userObj = new User(username, password);
                        if (userObj.doesUsernameExists()) {
                            // Username should be unique
                            JOptionPane.showMessageDialog(fRegisterFrame, "Username Already Exists", "Alert",
                                    JOptionPane.WARNING_MESSAGE);
                        } else {
                            // Save new user to the database
                            boolean result = userObj.registerUser();
                            if (result) {
                                JOptionPane.showMessageDialog(fRegisterFrame, "User Registered");
                                new Login();
                                fRegisterFrame.dispose();
                            } else {
                                JOptionPane.showMessageDialog(fRegisterFrame, "Something went wrong", "Alert",
                                        JOptionPane.WARNING_MESSAGE);
                            }
                        }
                    }
                }
            }
        });

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Login();
                fRegisterFrame.dispose();
            }
        });

    }

}
