package com.admin.dashboard.be.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/api/v1/")
public class UserController {

    @GetMapping("/dashboard")
    public String testApp() {
        return "Hello Spring Security";
    }

}
