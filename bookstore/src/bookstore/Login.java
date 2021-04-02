package bookstore;

// Import Swing Package
import javax.swing.*;
// Import Event Package
import java.awt.event.*;

public class Login {

    JFrame fLoginFrame = new JFrame("Login");
    JLabel lUsername, lPassword;
    JTextField tfUsername;
    JPasswordField pfPassword;
    JButton btnLogin, btnSignUp;

    Login() {
        // Label - Username
        lUsername = new JLabel("Username");
        lUsername.setBounds(130, 80, 300, 50);
        fLoginFrame.add(lUsername);

        // TextField - Username
        tfUsername = new JTextField();
        tfUsername.setBounds(220, 90, 300, 30);
        fLoginFrame.add(tfUsername);

        // Label - Password
        lPassword = new JLabel("Password");
        lPassword.setBounds(130, 130, 300, 50);
        fLoginFrame.add(lPassword);

        // PasswordField - Password
        pfPassword = new JPasswordField();
        pfPassword.setBounds(220, 140, 300, 30);
        fLoginFrame.add(pfPassword);

        // Button - Login
        btnLogin = new JButton("Login");
        btnLogin.setBounds(290, 180, 100, 30);
        fLoginFrame.add(btnLogin);

        // Button - SingUp
        btnSignUp = new JButton("SignUp");
        btnSignUp.setBounds(290, 220, 100, 30);
        fLoginFrame.add(btnSignUp);

        fLoginFrame.setLayout(null);
        fLoginFrame.setVisible(true);
        fLoginFrame.setSize(700, 500);

        // Stop the program once the cross button is clicked
        fLoginFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // On Login Button Clicked
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = tfUsername.getText();
                String psw = pfPassword.getText();
                User userObj = new User(username, psw);
                if (userObj.checkCredentials()) {
                    // Where you want to redirect
                    // new Home();
                    JOptionPane.showMessageDialog(fLoginFrame, "Welcome Back");

                    fLoginFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(fLoginFrame, "Username/Password  Invalid");
                }
            }
        });

        btnSignUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Register();
                fLoginFrame.dispose();
            }
        });

    }

    public static void main(String[] args) {
        Login loginObj = new Login();

    }

}
