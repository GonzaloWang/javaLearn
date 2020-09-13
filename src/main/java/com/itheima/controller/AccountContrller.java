package com.itheima.controller;

import com.itheima.pojo.Account;
import com.itheima.service.AccountService;
import com.itheima.utils.ObjectAndJsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountContrller {
    @Autowired
    private AccountService service;
    @Autowired
    private ObjectAndJsonUtils jsonUtils;



    @RequestMapping("/list")
    public String list() {
        return "list";
    }

    @RequestMapping("/findAll")
    public void findAll(HttpServletResponse response) throws IOException {
        List<Account> all = service.findAll();
        jsonUtils.ObjectToJson(response,all);
    }

    @RequestMapping("/addAccount")
    public void addAccount(Account account, HttpServletResponse response) throws IOException {
        System.out.println("addAccount");
        boolean b = service.addAccount(account);
        response.getWriter().print(b);
    }

    @RequestMapping("/deleteAccount")
    public void deleteAccount(Account account, HttpServletResponse response) throws IOException {
        System.out.println(account.getId());
        Integer id = account.getId();
        boolean b = service.deleteAccount(id);
        response.getWriter().print(b);
    }

    @RequestMapping("/findAccountByID")
    public void findAccountByID(Account account, HttpServletResponse response) throws IOException {
        System.out.println(account.getId());
        Integer id = account.getId();
        Account account1 = service.findAccountByID(id);
        jsonUtils.ObjectToJson(response,account1);
    }



    @RequestMapping("/updateAccount")
    public void updateAccount(Account account, HttpServletResponse response) throws IOException {
        boolean b = service.updateAccount(account);
        response.getWriter().print(b);
    }
}
