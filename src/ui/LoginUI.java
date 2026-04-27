package ui;

import services.AuthService;
import models.User;
import utils.AlertUtils;
import javax.swing.*;
import java.awt.*;

public class LoginUI extends JFrame {
    private AuthService authService = new AuthService();

    public LoginUI() {
        setTitle("Smart Banking - Login");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1, 10, 10));

        JPanel p1 = new JPanel(); p1.add(new JLabel("Email:")); 
        JTextField txtEmail = new JTextField(15); p1.add(txtEmail);
        
        JPanel p2 = new JPanel(); p2.add(new JLabel("Password:")); 
        JPasswordField txtPass = new JPasswordField(15); p2.add(txtPass);
        
        JButton btnLogin = new JButton("Login");
        JButton btnReg = new JButton("Register");

        btnLogin.addActionListener(e -> {
            User u = authService.login(txtEmail.getText(), new String(txtPass.getPassword()));
            if (u != null) {
                new DashboardUI(u).setVisible(true);
                dispose();
            } else {
                AlertUtils.showError("Login Error", "Invalid email or password!");
            }
        });

        btnReg.addActionListener(e -> {
            new RegisterUI().setVisible(true);
            dispose();
        });

        add(new JLabel("Welcome to Smart Banking", SwingConstants.CENTER));
        add(p1); add(p2);
        JPanel p3 = new JPanel(); p3.add(btnLogin); p3.add(btnReg);
        add(p3);
    }
}
