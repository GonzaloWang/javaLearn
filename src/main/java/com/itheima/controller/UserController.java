package com.itheima.controller;

import com.itheima.pojo.User;
import com.itheima.service.UserService;
import com.itheima.utils.ObjectAndJsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private ObjectAndJsonUtils jsonUtils;

    @Autowired
    private UserService service;

    @RequestMapping("/findAll")
    public void findAll(HttpServletResponse response) throws IOException {
        List<User> all = service.findAll();
        jsonUtils.ObjectToJson(response,all);
    }

    @RequestMapping("/addUser")
    public void addAccount(User user, HttpServletResponse response) throws IOException {
        boolean b = service.addUser(user);
        response.getWriter().print(b);
    }

    @RequestMapping("/deleteUser")
    public void deleteAccount(User user, HttpServletResponse response) throws IOException {
        System.out.println(user.getId());
        Integer id = user.getId();
        boolean b = service.deleteUser(id);
        response.getWriter().print(b);
    }

    @RequestMapping("/findUserByID")
    public void findAccountByID(User user, HttpServletResponse response) throws IOException {
        System.out.println(user.getId());
        Integer id = user.getId();
        User account1 = service.findUserByID(id);
        jsonUtils.ObjectToJson(response,account1);
    }



    @RequestMapping("/updateUser")
    public void updateAccount(User user, HttpServletResponse response) throws IOException {
        System.out.println(user.getUsername());
        boolean b = service.updateUser(user);
        response.getWriter().print(b);
    }

}
