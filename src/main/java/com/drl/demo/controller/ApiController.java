package com.drl.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class ApiController {

    @RequestMapping(path = "/hello")
    public String hello() {
        return "hello world";
    }

    @GetMapping(path = "/hello")
    public String getHello() {
        return "Get: hello world";
    }

    @PostMapping(path = "/hello")
    public String postHello() {
        return "Post: hello world!";
    }
}
