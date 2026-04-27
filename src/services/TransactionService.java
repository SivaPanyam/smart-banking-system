package services;

import dao.AccountDAO;
import dao.TransactionDAO;
import db.DBConnection;
import models.Account;
import models.Transaction;
import utils.AlertUtils;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TransactionService {
    private AccountDAO accountDAO = new AccountDAO();
    private TransactionDAO transactionDAO = new TransactionDAO();

    public boolean processTransaction(int userId, String type, double amount, String category, String desc, String targetAccNum) {
        boolean isFraud = amount > 50000;
        if (isFraud) {
            AlertUtils.showWarning("Fraud Alert", "Transaction of ₹" + amount + " flagged for review!");
        }

        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            Account acc = accountDAO.getAccountByUserId(userId);
            if (acc == null) throw new SQLException("Account not found");

            if (type.equals("WITHDRAWAL") || type.equals("TRANSFER")) {
                if (acc.getBalance() < amount) throw new SQLException("Insufficient funds");
                accountDAO.updateBalance(acc.getId(), acc.getBalance() - amount, conn);
                
                if ((acc.getBalance() - amount) < 1000) {
                    AlertUtils.showWarning("Low Balance Alert", "Your account balance has dropped below ₹1000.");
                }
            } else if (type.equals("DEPOSIT")) {
                accountDAO.updateBalance(acc.getId(), acc.getBalance() + amount, conn);
            }

            transactionDAO.logTransaction(acc.getId(), type, amount, category, desc, isFraud, conn);

            if (type.equals("TRANSFER") && targetAccNum != null) {
                Account target = accountDAO.getAccountByNumber(targetAccNum);
                if (target == null) throw new SQLException("Target account not found");
                
                accountDAO.updateBalance(target.getId(), target.getBalance() + amount, conn);
                transactionDAO.logTransaction(target.getId(), "DEPOSIT", amount, "Transfer In", "From " + acc.getAccountNumber(), false, conn);
            }

            conn.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            return false;
        } finally {
            if (conn != null) try { conn.setAutoCommit(true); conn.close(); } catch (SQLException ex) { ex.printStackTrace(); }
        }
    }

    public List<Transaction> getHistory(int userId) {
        Account acc = accountDAO.getAccountByUserId(userId);
        return transactionDAO.getTransactionsByAccountId(acc.getId());
    }
}
