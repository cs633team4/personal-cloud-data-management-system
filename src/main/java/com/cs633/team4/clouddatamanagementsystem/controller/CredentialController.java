package com.cs633.team4.clouddatamanagementsystem.controller;

import com.cs633.team4.clouddatamanagementsystem.model.Credential;
import com.cs633.team4.clouddatamanagementsystem.model.User;
import com.cs633.team4.clouddatamanagementsystem.service.CredentialService;
import com.cs633.team4.clouddatamanagementsystem.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Handle front-end calls, identify home.html template served for credential request and populate view model with data.
 */
@Controller
@RequestMapping("/credential")
public class CredentialController {

    private final UserService userService;
    private final CredentialService credentialService;

    public CredentialController(UserService userService, CredentialService credentialService) {
        this.userService = userService;
        this.credentialService = credentialService;
    }

    @PostMapping("/add")
    public String postCredential(Authentication authentication, @ModelAttribute(value = "credential") Credential credential, Model model) {

        try {
            User user = userService.getUser(authentication.getName());
            credential.setUserId(user.getUserId());

            if (credential.getCredentialId() == null){
                this.credentialService.addCredential(credential);
                model.addAttribute("success", true);
                model.addAttribute("message", "Add credential successfully!");
            } else {
                this.credentialService.updateCredential(credential);
                model.addAttribute("success", true);
                model.addAttribute("message", "Update credential successfully!");
            }
        } catch (Exception e) {
            model.addAttribute("error", true);
            model.addAttribute("message", "Something wrong when adding or updating credential. Please try again.");
        }

        return "result";
    }

    @GetMapping("/delete/{credentialId}")
    public String deleteCredential(@PathVariable Integer credentialId,Model model) {
        try {
            this.credentialService.deleteCredential(credentialId);
            model.addAttribute("delete", true);
            model.addAttribute("message", "Delete credential successfully!");

        }catch (Exception e){
            model.addAttribute("error", true);
            model.addAttribute("message", "Something wrong when deleting Credential.");
        }

        return "result";
    }

}
