package ru.dima.myblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping
    @ResponseBody
    public String hello() {
        return "Welcome to my blog.";
    }
}
