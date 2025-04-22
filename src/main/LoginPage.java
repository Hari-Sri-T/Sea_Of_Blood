package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginPage() {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login(usernameField.getText(), String.valueOf(passwordField.getPassword()));
            }
        });

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                register(usernameField.getText(), String.valueOf(passwordField.getPassword()));
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(registerButton);

        add(panel);
    }

    private void login(String username, String password) {
        if (MySQLConnection.validateUser (username, password)) {
            JOptionPane.showMessageDialog(this, "Login Successful");
            this.dispose(); // Close login window
           
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Credentials");
        }
    }

    private void register(String username, String password) {
        if (MySQLConnection.registerUser (username, password)) {
            JOptionPane.showMessageDialog(this, "Registration Successful");
        } else {
            JOptionPane.showMessageDialog(this, "Username already exists");
        }
    }
}
