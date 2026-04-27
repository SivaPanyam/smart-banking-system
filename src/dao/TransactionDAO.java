package dao;

import db.DBConnection;
import models.Transaction;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {
    public boolean logTransaction(int accountId, String type, double amount, String category, String description, boolean isFraud, Connection conn) throws SQLException {
        String sql = "INSERT INTO transactions (account_id, type, amount, category, description, is_fraud_flagged) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, accountId);
            stmt.setString(2, type);
            stmt.setDouble(3, amount);
            stmt.setString(4, category);
            stmt.setString(5, description);
            stmt.setBoolean(6, isFraud);
            return stmt.executeUpdate() > 0;
        }
    }

    public List<Transaction> getTransactionsByAccountId(int accountId) {
        List<Transaction> list = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE account_id = ? ORDER BY transaction_date DESC";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, accountId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Transaction(rs.getInt("id"), rs.getInt("account_id"), rs.getString("type"),
                    rs.getDouble("amount"), rs.getString("category"), rs.getString("description"),
                    rs.getTimestamp("transaction_date"), rs.getBoolean("is_fraud_flagged")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
}
