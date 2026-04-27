package services;

import dao.UserDAO;
import dao.AccountDAO;
import models.User;
import utils.PasswordUtils;

public class AuthService {
    private UserDAO userDAO = new UserDAO();
    private AccountDAO accountDAO = new AccountDAO();

    public boolean register(String name, String email, String password) {
        if (userDAO.getUserByEmail(email) != null) return false;
        
        String hashed = PasswordUtils.hashPassword(password);
        boolean success = userDAO.register(name, email, hashed);
        
        if (success) {
            User u = userDAO.getUserByEmail(email);
            String accNum = "AC" + System.currentTimeMillis();
            accountDAO.createAccount(u.getId(), accNum);
        }
        return success;
    }

    public User login(String email, String password) {
        User u = userDAO.getUserByEmail(email);
        if (u != null && PasswordUtils.verifyPassword(password, u.getPasswordHash())) {
            return u;
        }
        return null;
    }
}
