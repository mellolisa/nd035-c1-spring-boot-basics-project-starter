package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.forms.SignUpForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignUpController {

    @GetMapping()
    public String firstVisit(@ModelAttribute("signUpForm") SignUpForm signUpForm, Model model) {
        model.addAttribute("firstVisit", true);
        return "signup";
    }

    @PostMapping()
    public String subsequentVisit(@ModelAttribute("signUpForm") SignUpForm signUpForm, Model model) {
        model.addAttribute("firstVisit", false);
        model.addAttribute( "errorCode", 0);
        return "signup";
    }
}
