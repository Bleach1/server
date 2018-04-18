package com.example.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void register(String name, String password) {
        jdbcTemplate.update("insert into users values (null ,?,?,null,null);", name, password);
    }


    public void login(String name, String password) {
        String sql = "select name from users where name=?and password=?";
        jdbcTemplate.queryForObject(sql, new Object[]{name, password}, String.class);
    }


    public void insertValue(String name, Integer temp, Integer prpr) {

        String sql = "UPDATE users SET temp = '" + temp + "', prpr = '" + prpr + "'WHERE name ='" + name + "'";
        jdbcTemplate.update(sql);
    }


    public List<Map<String, Object>> getSetting(String name) {
        String sql = "select * from users where name='" + name + "'";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        return maps;
    }
}
