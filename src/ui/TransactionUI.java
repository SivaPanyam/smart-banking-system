package ui;

import services.TransactionService;
import utils.AlertUtils;
import utils.Validator;
import javax.swing.*;
import java.awt.*;

public class TransactionUI extends JFrame {
    private TransactionService ts = new TransactionService();

    public TransactionUI(int userId, String type, DashboardUI parent) {
        setTitle(type + " Money");
        setSize(400, 300);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(5, 1, 5, 5));

        JPanel p1 = new JPanel(); p1.add(new JLabel("Amount (₹):")); 
        JTextField txtAmt = new JTextField(15); p1.add(txtAmt);
        
        JPanel p2 = new JPanel(); p2.add(new JLabel("Category:")); 
        JComboBox<String> cbCat = new JComboBox<>(new String[]{"Salary", "Food", "Travel", "Bills", "Shopping", "Other"}); 
        p2.add(cbCat);
        
        JPanel p3 = new JPanel(); p3.add(new JLabel("Target A/c (Transfers):")); 
        JTextField txtTarget = new JTextField(15); p3.add(txtTarget);
        
        if (!type.equals("TRANSFER")) { txtTarget.setEnabled(false); }

        JButton btnSubmit = new JButton("Submit Transaction");
        btnSubmit.addActionListener(e -> {
            if (!Validator.isValidAmount(txtAmt.getText())) {
                AlertUtils.showError("Validation Error", "Invalid amount!"); return;
            }
            double amt = Double.parseDouble(txtAmt.getText());
            boolean success = ts.processTransaction(userId, type, amt, cbCat.getSelectedItem().toString(), type + " Transaction", txtTarget.getText());
            if (success) {
                AlertUtils.showInfo("Success", "Transaction Completed!");
                parent.refreshBalance();
                dispose();
            } else {
                AlertUtils.showError("Error", "Transaction Failed. Please check balance or account info.");
            }
        });

        add(p1); add(p2); add(p3);
        JPanel p4 = new JPanel(); p4.add(btnSubmit); add(p4);
    }
}
