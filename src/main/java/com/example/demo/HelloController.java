package com.example.demo;

import org.springframework.stereotype.Controller;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }
}