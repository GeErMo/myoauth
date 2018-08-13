package com.ynbwjf.myoauth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("main")
public class mainController {

    @RequestMapping("hello")
    public String showMainView(){
        return "hello";
    }
}
