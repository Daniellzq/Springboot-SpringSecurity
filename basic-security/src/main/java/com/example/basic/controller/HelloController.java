package com.example.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class HelloController {

    @RequestMapping("/hello")
    public String hello(ModelAndView mav){
        return "hello";
    }

}
