package com.arcyriea.usersecuritypractice.controller;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/user")
public class HomeController {

   

    @GetMapping("/home")
    public String home() {
        return "You are on the home page";
    }
    
    @GetMapping("/dashboard")
    public String dashboard(){
        return "You are seeing the dashboard content";
    }

    
    
    
}
