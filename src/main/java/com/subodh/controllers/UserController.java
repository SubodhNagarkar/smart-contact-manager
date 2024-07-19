package com.subodh.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;




@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping(value = "/profile")
    public String userProfile() {
        return"user/profile";
    }
    @RequestMapping(value = "/dashboard")
    public String userDashboard(){
        return "user/dashboard";
    }

}
