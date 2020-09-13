package com.itheima.service;

import com.itheima.pojo.Account;

import java.util.List;

public interface AccountService {
    List<Account> findAll();
    boolean addAccount(Account account);
    boolean deleteAccount(Integer id);
    boolean updateAccount(Account account);
    Account findAccountByID(Integer id);
}
