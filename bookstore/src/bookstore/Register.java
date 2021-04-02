package bookstore;

// Import Swing Package
import javax.swing.*;
// Import Event Package
import java.awt.event.*;

public class Register {

    JFrame fRegisterFrame = new JFrame("Register");
    JLabel lUsername, lPassword, lConfirmPassword;
    JTextField tfUsername;
    JPasswordField pfPassword, pfConfirmPassword;
    JButton btnLogin, btnSignUp;

    Register() {
        // Label - Username
        lUsername = new JLabel("Username");
        lUsername.setBounds(130, 90, 300, 50);
        fRegisterFrame.add(lUsername);

        // TextField - Username
        tfUsername = new JTextField();
        tfUsername.setBounds(240, 100, 300, 30);
        fRegisterFrame.add(tfUsername);

        // Label - Password
        lPassword = new JLabel("Password");
        lPassword.setBounds(130, 130, 300, 50);
        fRegisterFrame.add(lPassword);

        // PasswordField - Password
        pfPassword = new JPasswordField();
        pfPassword.setBounds(240, 140, 300, 30);
        fRegisterFrame.add(pfPassword);

        // Label - Confirm Password
        lConfirmPassword = new JLabel("Confirm Password");
        lConfirmPassword.setBounds(130, 170, 300, 50);
        fRegisterFrame.add(lConfirmPassword);

        // PasswordField - Confirm Password
        pfConfirmPassword = new JPasswordField();
        pfConfirmPassword.setBounds(240, 180, 300, 30);
        fRegisterFrame.add(pfConfirmPassword);

        // Button - SingUp
        btnSignUp = new JButton("Register");
        btnSignUp.setBounds(300, 230, 100, 30);
        fRegisterFrame.add(btnSignUp);

        // Button - Login
        btnLogin = new JButton("Login");
        btnLogin.setBounds(300, 270, 100, 30);
        fRegisterFrame.add(btnLogin);

        fRegisterFrame.setLayout(null);
        fRegisterFrame.setVisible(true);
        fRegisterFrame.setSize(700, 500);

        // Stop the program once the cross button is clicked
        fRegisterFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // On SignUp Button Clicked
        btnSignUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = tfUsername.getText();
                String password = pfPassword.getText();
                String confirmPassword = pfPassword.getText();

                if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(fRegisterFrame, "Field cannot be empty");
                } else if (password.length() < 4) {
                    JOptionPane.showMessageDialog(fRegisterFrame, "Password should be at least 4 characters");
                } else if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(fRegisterFrame, "Passwords didn't match");
                } else {

                    User userObj = new User(username, password);
                    if (userObj.doesUsernameExists()) {
                        JOptionPane.showMessageDialog(fRegisterFrame, "Username Already Exists");
                    } else {
                        boolean result = userObj.registerUser();
                        if (result) {
                            JOptionPane.showMessageDialog(fRegisterFrame, "User Registered");
                            new Login();
                            fRegisterFrame.dispose();
                        } else {
                            JOptionPane.showMessageDialog(fRegisterFrame, "Something went wrong");
                        }
                    }
                }
            }
        });

    }

}
