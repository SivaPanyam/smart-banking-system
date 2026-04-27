package ui;

import models.Transaction;
import services.TransactionService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class HistoryUI extends JFrame {
    public HistoryUI(int userId) {
        setTitle("Transaction History");
        setSize(650, 400);
        setLocationRelativeTo(null);

        String[] columns = {"Date", "Type", "Amount (₹)", "Category", "Fraud Flag"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);

        List<Transaction> list = new TransactionService().getHistory(userId);
        for (Transaction t : list) {
            model.addRow(new Object[]{
                t.getTransactionDate(), 
                t.getType(), 
                t.getAmount(), 
                t.getCategory(), 
                t.isFraudFlagged() ? "⚠️ YES" : "NO"
            });
        }

        add(new JScrollPane(table), BorderLayout.CENTER);
    }
}
