package models;

import java.sql.Timestamp;

public class Transaction {
    private int id;
    private int accountId;
    private String type;
    private double amount;
    private String category;
    private String description;
    private Timestamp transactionDate;
    private boolean isFraudFlagged;

    public Transaction(int id, int accountId, String type, double amount, String category, String description, Timestamp transactionDate, boolean isFraudFlagged) {
        this.id = id; this.accountId = accountId; this.type = type; this.amount = amount;
        this.category = category; this.description = description; this.transactionDate = transactionDate; this.isFraudFlagged = isFraudFlagged;
    }

    public int getId() { return id; }
    public String getType() { return type; }
    public double getAmount() { return amount; }
    public String getCategory() { return category; }
    public String getDescription() { return description; }
    public Timestamp getTransactionDate() { return transactionDate; }
    public boolean isFraudFlagged() { return isFraudFlagged; }
}
