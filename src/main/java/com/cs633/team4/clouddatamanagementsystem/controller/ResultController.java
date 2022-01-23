package com.cs633.team4.clouddatamanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Interpret the results that html templates redirect, to ensure a smooth user experience.
 */
@Controller
@RequestMapping("/result")
public class ResultController {

    @GetMapping
    public String ResultView() {
        return "result";
    }
}
