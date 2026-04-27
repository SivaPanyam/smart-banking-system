package ui;

import models.Transaction;
import services.TransactionService;
import services.AnalyticsService;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class AnalyticsUI extends JFrame {
    public AnalyticsUI(int userId) {
        setTitle("Spending Analytics");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        List<Transaction> txns = new TransactionService().getHistory(userId);
        AnalyticsService as = new AnalyticsService();
        Map<String, Double> map = as.getSpendingByCategory(txns);

        JPanel panel = new JPanel(new GridLayout(map.size() + 3, 1, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel title = new JLabel("Your Category-Wise Spending");
        title.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(title);
        
        for (Map.Entry<String, Double> e : map.entrySet()) {
            panel.add(new JLabel("• " + e.getKey() + ": ₹" + String.format("%.2f", e.getValue())));
        }
        
        JLabel topCat = new JLabel("<html><br><b>Top Spending Category: </b>" + as.getTopCategory(map) + "</html>");
        topCat.setForeground(new Color(180, 0, 0));
        panel.add(topCat);

        add(new JScrollPane(panel), BorderLayout.CENTER);
    }
}
