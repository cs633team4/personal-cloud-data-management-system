package com.cs633.team4.clouddatamanagementsystem.controller;

import com.cs633.team4.clouddatamanagementsystem.model.Note;
import com.cs633.team4.clouddatamanagementsystem.model.User;
import com.cs633.team4.clouddatamanagementsystem.service.NoteService;
import com.cs633.team4.clouddatamanagementsystem.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Handle front-end calls, identify home.html template served for note request and populate view model with data.
 */
@Controller
@RequestMapping("/note")
public class NoteController {

    private final UserService userService;
    private final NoteService noteService;

    public NoteController(UserService userService, NoteService noteService) {
        this.userService = userService;
        this.noteService = noteService;
    }

    @PostMapping("/add")
    public String addAndUpdateNote(@ModelAttribute(value = "note") Note note, Authentication auth, Model model) {

        try {
            User user = userService.getUser(auth.getName());
            note.setUserId(user.getUserId());

            // check if note already exists, if not add note, else update note
            if (note.getNoteId() == null) {
                noteService.addNote(note);
                model.addAttribute("success", true);
                model.addAttribute("message", "Add note successfully!");

            } else {
                noteService.updateNote(note);
                model.addAttribute("success", true);
                model.addAttribute("message", "Update note successfully!");
            }
        } catch (Exception e) {
            model.addAttribute("error", true);
            model.addAttribute("message", "Something wrong when adding or updating note. Please try again.");
        }

        return "result";
    }

    @GetMapping("/delete/{noteId}")
    public String deleteNote(@PathVariable Integer noteId, Model model) {

        try {
            this.noteService.deleteNote(noteId);
            model.addAttribute("delete", true);
            model.addAttribute("message", "Delete note successfully!");

        } catch (Exception e) {
            model.addAttribute("error", true);
            model.addAttribute("message", "Something wrong when deleting Note. Please try again.");
        }

        return "result";
    }
}
