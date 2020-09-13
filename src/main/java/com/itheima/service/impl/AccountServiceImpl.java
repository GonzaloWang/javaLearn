package com.itheima.service.impl;

import com.itheima.dao.AccountMapper;
import com.itheima.pojo.Account;
import com.itheima.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public List<Account> findAll() {
        return accountMapper.findAll();
    }

    @Override
    public boolean addAccount(Account account) {
        return accountMapper.addAccount(account) == 1;
    }

    @Override
    public boolean deleteAccount(Integer id) {
        return accountMapper.deleteAccount(id) == 1;
    }

    @Override
    public boolean updateAccount(Account account) {
        return accountMapper.updateAccount(account) == 1;
    }

    @Override
    public Account findAccountByID(Integer id) {
        return accountMapper.findAccountByID(id);
    }


}
