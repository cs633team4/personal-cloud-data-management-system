package com.cs633.team4.clouddatamanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Handle front-end calls, identify login.html template served for login and logout request and populate view model with data.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping()
    public String loginView() {
        return "login";
    }

    @PostMapping("/logout")
    public String logout() { return "login"; }

}
