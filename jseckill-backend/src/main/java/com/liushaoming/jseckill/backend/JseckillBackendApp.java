package com.liushaoming.jseckill.backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("com.liushaoming.jseckill.backend.dao")
public class JseckillBackendApp extends SpringBootServletInitializer{

    public static void main(String[] args) {
        SpringApplication.run(JseckillBackendApp.class, args);
    }
}

