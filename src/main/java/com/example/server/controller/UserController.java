package com.example.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.server.service.UserService;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    public String register(String name, String password) {
        userService.register(name, password);
        return "success";
    }


    @RequestMapping("/login")
    public String login(String name, String password) {
        try {
            userService.login(name, password);
            return "success";
        } catch (Exception e) {
            return "fail";
        }

    }


    @RequestMapping("/insertValue")
    public String insertValue(String name, Integer temp, Integer prpr) {
        userService.insertValue(name, temp, prpr);
        return "success";
    }


    @RequestMapping("/getSetting")
    public String getSetting(String name) {

        try {
            List<Map<String, Object>> setting = userService.getSetting(name);
            return setting.get(0).toString();
        } catch (Exception e) {
            return "fail";
        }
    }


    @RequestMapping("/getInfo")
    public String getInfo() {
        userService.getInfo();
        return "success";
    }

    @RequestMapping("/insertInfo")
    public String insertInfo(String name, Date time) {
        userService.insertInfo(name, time);
        return "success";
    }
}
