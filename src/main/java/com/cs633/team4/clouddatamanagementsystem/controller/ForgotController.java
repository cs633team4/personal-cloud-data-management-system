package com.cs633.team4.clouddatamanagementsystem.controller;

import com.cs633.team4.clouddatamanagementsystem.model.User;
import com.cs633.team4.clouddatamanagementsystem.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cs633.team4.clouddatamanagementsystem.service.UserService;

@Controller
@RequestMapping("/forgot")
public class ForgotController {

    private final UserService userService;

    public ForgotController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String forgotView() {
        return "forgot";
    }

    @PostMapping()
    public String updateUser(@ModelAttribute User user, Model model) {

        // check if username exists
        if (userService.isUsernameAvailable(user.getUsername())) {
            model.addAttribute("signupError", true);
            model.addAttribute("signupErrorMessage", "The username already exists. Please try another one.");

        } else {
            userService.update(user);
            model.addAttribute("updateSuccess", true);
        }

        return "forgot";
    }
}
