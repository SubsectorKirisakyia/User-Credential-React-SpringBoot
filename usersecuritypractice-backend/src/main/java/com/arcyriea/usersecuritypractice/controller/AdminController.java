package com.arcyriea.usersecuritypractice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    
    @GetMapping("/home")
    public String home() {
        return "You are on the admin's home page";
    }
    
    @GetMapping("/dashboard")
    public String dashboard(){
        return "You are seeing the admin's dashboard content";
    }
}
