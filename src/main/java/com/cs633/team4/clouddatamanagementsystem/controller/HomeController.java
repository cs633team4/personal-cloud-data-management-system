package com.cs633.team4.clouddatamanagementsystem.controller;

import com.cs633.team4.clouddatamanagementsystem.model.Credential;
import com.cs633.team4.clouddatamanagementsystem.model.File;
import com.cs633.team4.clouddatamanagementsystem.model.Note;
import com.cs633.team4.clouddatamanagementsystem.model.User;
import com.cs633.team4.clouddatamanagementsystem.service.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Handle front-end calls, identify home.html template served for home request and populate view model with data.
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    private final UserService userService;
    private final FileService fileService;
    private final NoteService noteService;
    private final CredentialService credentialService;
    private final EncryptionService encryptionService;

    public HomeController(UserService userService, FileService fileService,
                          NoteService noteService, CredentialService credentialService,
                          EncryptionService encryptionService) {

        this.userService = userService;
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
    }

    @GetMapping()
    public String homeView(Authentication authentication, Model model) {

        final User loggedInUser = userService.getUser(authentication.getName());

        List<File> files = fileService.getAllFiles(loggedInUser);
        List<Note> notes = noteService.getAllNotes(loggedInUser);
        List<Credential> credentials = credentialService.getAllCredentials(loggedInUser);

        model.addAttribute("user", loggedInUser);
        model.addAttribute("files", files);
        model.addAttribute("notes", notes);
        model.addAttribute("credentials", credentials);
        model.addAttribute("encryptionService", encryptionService);

        return "home";
    }
}
