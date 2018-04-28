package com.example.server.controller;

import com.example.server.bean.InfoBean;
import com.example.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class HtmlController {
    @Autowired
    private UserService userService;

    @RequestMapping("/freemarker")
    public String hello(Map<String, List<InfoBean>> map) {
        List<Map<String, Object>> info = userService.getInfo();
        List<InfoBean> strings = new ArrayList<>();
        for (Map<String, Object> item : info) {
            InfoBean infoBean = new InfoBean();
            infoBean.setSunlight((String) item.get("sunlight"));
            infoBean.setPolice((String) item.get("police"));
            infoBean.setTemp((String) item.get("temp"));
            infoBean.setHumidity((String) item.get("humidity"));
            strings.add(infoBean);
        }
        map.put("list", strings);
        return "hello_freemarker";
    }

}
