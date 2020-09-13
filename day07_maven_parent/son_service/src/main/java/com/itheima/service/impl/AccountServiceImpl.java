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
    private AccountMapper mapper;

    @Override
    public List<Account> findAll() {
        return mapper.findAll();
    }
}
