package services;

import dao.AccountDAO;
import models.Account;

public class AccountService {
    private AccountDAO accountDAO = new AccountDAO();

    public Account getAccount(int userId) {
        return accountDAO.getAccountByUserId(userId);
    }
}
