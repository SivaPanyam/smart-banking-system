package ui;

import services.AuthService;
import utils.AlertUtils;
import utils.Validator;
import javax.swing.*;
import java.awt.*;

public class RegisterUI extends JFrame {
    private AuthService authService = new AuthService();

    public RegisterUI() {
        setTitle("Smart Banking - Register");
        setSize(350, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1, 10, 10));

        JPanel p1 = new JPanel(); p1.add(new JLabel("Name:")); JTextField txtName = new JTextField(15); p1.add(txtName);
        JPanel p2 = new JPanel(); p2.add(new JLabel("Email:")); JTextField txtEmail = new JTextField(15); p2.add(txtEmail);
        JPanel p3 = new JPanel(); p3.add(new JLabel("Password:")); JPasswordField txtPass = new JPasswordField(15); p3.add(txtPass);
        
        JButton btnReg = new JButton("Register");
        JButton btnBack = new JButton("Back to Login");

        btnReg.addActionListener(e -> {
            if (!Validator.isValidEmail(txtEmail.getText())) {
                AlertUtils.showError("Validation Error", "Invalid email format!"); return;
            }
            if (authService.register(txtName.getText(), txtEmail.getText(), new String(txtPass.getPassword()))) {
                AlertUtils.showInfo("Success", "Registration successful! Please login.");
                new LoginUI().setVisible(true); dispose();
            } else {
                AlertUtils.showError("Registration Failed", "Email may already exist.");
            }
        });

        btnBack.addActionListener(e -> { new LoginUI().setVisible(true); dispose(); });

        add(new JLabel("Create an Account", SwingConstants.CENTER));
        add(p1); add(p2); add(p3);
        JPanel p4 = new JPanel(); p4.add(btnReg); p4.add(btnBack);
        add(p4);
    }
}
