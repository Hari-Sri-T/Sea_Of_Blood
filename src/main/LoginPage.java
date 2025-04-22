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
        
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(loginButton);
        
        add(panel);
    }

    private void login(String username, String password) {
        // Call the database method for login validation
        if (MySQLConnection.validateUser(username, password)) {
            JOptionPane.showMessageDialog(this, "Login Successful");
            // Proceed to main game
            this.dispose(); // Close login window
            new GamePanel(); // Open game panel
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Credentials");
        }
    }
}

