package com.cs633.team4.clouddatamanagementsystem.controller;

import com.cs633.team4.clouddatamanagementsystem.model.User;
import com.cs633.team4.clouddatamanagementsystem.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Handle front-end calls, identify signup.html template served for signup request and populate the view model with data.
 */
@Controller()
@RequestMapping("/signup")
public class SignupController {

    private final UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String signupView() {
        return "signup";
    }

    @PostMapping()
    public String createUser(@ModelAttribute User user, Model model) {

        // check if username exists
        if (!userService.isUsernameAvailable(user.getUsername())) {
            model.addAttribute("signupError", true);
            model.addAttribute("signupErrorMessage", "The username already exists. Please try another one.");

        } else {
            Integer createUser = userService.createUser(user);

            if (createUser < 0) {
                model.addAttribute("signupError", true);
                model.addAttribute("signupErrorMessage", "Something wrong when signing you up. Please try again.");
            } else {
                model.addAttribute("signupSuccess", true);
            }
        }

        return "signup";
    }
}