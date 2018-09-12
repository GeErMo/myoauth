package com.ynbwjf.myoauth.controller;

import com.ynbwjf.myoauth.util.VerificationCodeGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class mainController {

    @RequestMapping("/loginPage")
    public ModelAndView showLoginView(){
        System.out.println("进来了");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }

    @RequestMapping("/index")
    public String showMainView(){
        return "hello";
    }

    @RequestMapping("/home")
    public String hello(){
        return "home";
    }

    @RequestMapping("/verificationCode")
    public void verificationCode(HttpServletRequest request, HttpServletResponse response){
        VerificationCodeGenerator.creatVerificationCode(request,response);
        return;
    }
}
