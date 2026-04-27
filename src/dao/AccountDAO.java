package dao;

import db.DBConnection;
import models.Account;
import java.sql.*;

public class AccountDAO {
    public boolean createAccount(int userId, String accountNumber) {
        String sql = "INSERT INTO accounts (user_id, account_number, balance) VALUES (?, ?, 0.00)";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setString(2, accountNumber);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public Account getAccountByUserId(int userId) {
        String sql = "SELECT * FROM accounts WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Account(rs.getInt("id"), rs.getInt("user_id"), rs.getString("account_number"), rs.getDouble("balance"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public Account getAccountByNumber(String accountNumber) {
        String sql = "SELECT * FROM accounts WHERE account_number = ?";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, accountNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Account(rs.getInt("id"), rs.getInt("user_id"), rs.getString("account_number"), rs.getDouble("balance"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public boolean updateBalance(int accountId, double newBalance, Connection conn) throws SQLException {
        String sql = "UPDATE accounts SET balance = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, newBalance);
            stmt.setInt(2, accountId);
            return stmt.executeUpdate() > 0;
        }
    }
}
