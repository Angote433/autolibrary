package com.arnold.autolibrary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StudentController {
    @RequestMapping("/")
    public String index(){
        return "index.html";
    }
}
