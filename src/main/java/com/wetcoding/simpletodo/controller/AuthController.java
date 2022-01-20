package com.wetcoding.simpletodo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello!";
    }
}
