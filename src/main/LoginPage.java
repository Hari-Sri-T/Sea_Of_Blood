package main;

import javax.swing.*;
import java.awt.*;

public class LoginPage {
    public static void showLoginAndLaunchGame() {
        while (true) {
            JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
            JTextField usernameField = new JTextField(20);
            JPasswordField passwordField = new JPasswordField(20);

            panel.add(new JLabel("Username:"));
            panel.add(usernameField);
            panel.add(new JLabel("Password:"));
            panel.add(passwordField);

            Object[] options = {"Login", "Register", "Exit"};
            int result = JOptionPane.showOptionDialog(
                    null, panel, "Login to Sea of Bones",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, options, options[0]
            );

            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            if (result == 0) { // Login
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Username or password cannot be empty.");
                    continue;
                }

                if (MySQLConnection.validateUser(username, password)) {
                    JOptionPane.showMessageDialog(null, "Login Successful!");
                    launchGameWindow();
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Credentials. Try again.");
                }

            } else if (result == 1) { // Register
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Username or password cannot be empty.");
                    continue;
                }

                if (MySQLConnection.userExists(username)) {
                    JOptionPane.showMessageDialog(null, "User already exists. Try logging in.");
                } else {
                    if (MySQLConnection.registerUser(username, password)) {
                        JOptionPane.showMessageDialog(null, "Registration Successful! You can now log in.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Registration Failed. Try again.");
                    }
                }

            } else { // Exit
                int confirm = JOptionPane.showConfirmDialog(null, "Exit game?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        }
    }

    private static void launchGameWindow() {
        JFrame window = new JFrame("Sea of Bones");
        GamePanel gamepanel = new GamePanel();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.add(gamepanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamepanel.setupGame();
        gamepanel.startGameThread();
    }
}
