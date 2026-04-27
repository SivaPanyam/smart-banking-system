package ui;

import models.User;
import models.Account;
import services.AccountService;
import javax.swing.*;
import java.awt.*;

public class DashboardUI extends JFrame {
    private User user;
    private Account account;
    private AccountService accountService = new AccountService();
    private JLabel lblBalance;

    public DashboardUI(User user) {
        this.user = user;
        this.account = accountService.getAccount(user.getId());

        setTitle("Dashboard - " + user.getName());
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        topPanel.setBackground(new Color(40, 53, 147));
        
        JLabel lblAcc = new JLabel("Account No: " + account.getAccountNumber(), SwingConstants.CENTER);
        lblAcc.setForeground(Color.WHITE);
        topPanel.add(lblAcc);
        
        lblBalance = new JLabel("Balance: ₹" + account.getBalance(), SwingConstants.CENTER);
        lblBalance.setFont(new Font("Arial", Font.BOLD, 24));
        lblBalance.setForeground(Color.WHITE);
        topPanel.add(lblBalance);
        
        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(3, 2, 15, 15));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnDeposit = new JButton("Deposit");
        JButton btnWithdraw = new JButton("Withdraw");
        JButton btnTransfer = new JButton("Transfer");
        JButton btnHistory = new JButton("Transaction History");
        JButton btnAnalytics = new JButton("Monthly Analytics");
        JButton btnLogout = new JButton("Logout");

        btnDeposit.addActionListener(e -> new TransactionUI(user.getId(), "DEPOSIT", this).setVisible(true));
        btnWithdraw.addActionListener(e -> new TransactionUI(user.getId(), "WITHDRAWAL", this).setVisible(true));
        btnTransfer.addActionListener(e -> new TransactionUI(user.getId(), "TRANSFER", this).setVisible(true));
        btnHistory.addActionListener(e -> new HistoryUI(user.getId()).setVisible(true));
        btnAnalytics.addActionListener(e -> new AnalyticsUI(user.getId()).setVisible(true));
        btnLogout.addActionListener(e -> { new LoginUI().setVisible(true); dispose(); });

        centerPanel.add(btnDeposit); centerPanel.add(btnWithdraw);
        centerPanel.add(btnTransfer); centerPanel.add(btnHistory);
        centerPanel.add(btnAnalytics); centerPanel.add(btnLogout);

        add(centerPanel, BorderLayout.CENTER);
    }

    public void refreshBalance() {
        this.account = accountService.getAccount(user.getId());
        lblBalance.setText("Balance: ₹" + account.getBalance());
    }
}
