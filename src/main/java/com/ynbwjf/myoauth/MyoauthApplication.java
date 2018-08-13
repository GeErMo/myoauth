package com.ynbwjf.myoauth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
@MapperScan(basePackages = "com.ynbwjf.myoauth.dao")
public class MyoauthApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyoauthApplication.class, args);
    }
}
