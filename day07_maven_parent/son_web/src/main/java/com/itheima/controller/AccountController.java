package com.itheima.controller;

import com.itheima.pojo.Account;
import com.itheima.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class AccountController {
    @Autowired
    private AccountService service;

    @RequestMapping("/findAll")
    public String findAll(Model model){
        List<Account> list = service.findAll();
        model.addAttribute("list",list);
        return "list";
    }
}
