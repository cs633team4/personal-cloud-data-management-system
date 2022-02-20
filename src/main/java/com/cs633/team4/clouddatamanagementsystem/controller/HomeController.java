package com.cs633.team4.clouddatamanagementsystem.controller;

import com.cs633.team4.clouddatamanagementsystem.model.*;
import com.cs633.team4.clouddatamanagementsystem.service.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Handle front-end calls, identify home.html template served for home request and populate view model with data.
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    private final UserService userService;
    private final ImageService imageService;
    private final FileService fileService;
    private final NoteService noteService;
    private final CredentialService credentialService;
    private final EncryptionService encryptionService;

    public HomeController(UserService userService, ImageService imageService, FileService fileService,
                          NoteService noteService, CredentialService credentialService,
                          EncryptionService encryptionService) {

        this.userService = userService;
        this.imageService = imageService;
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
    }

    @GetMapping()
    public String homeView(Authentication authentication, Model model) {

        final User loggedInUser = userService.getUser(authentication.getName());

        List<Image> images = new ArrayList<>();
        List<File> files = new ArrayList<>();
        List<Note> notes = new ArrayList<>();
        List<Credential> credentials = new ArrayList<>();

        try {
            images = imageService.getAllImages(loggedInUser);
            files = fileService.getAllFiles(loggedInUser);
            notes = noteService.getAllNotes(loggedInUser);
            credentials = credentialService.getAllCredentials(loggedInUser);
        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("user", loggedInUser);
        model.addAttribute("images", images);
        model.addAttribute("files", files);
        model.addAttribute("notes", notes);
        model.addAttribute("credentials", credentials);
        model.addAttribute("encryptionService", encryptionService);

        return "home";
    }
}
