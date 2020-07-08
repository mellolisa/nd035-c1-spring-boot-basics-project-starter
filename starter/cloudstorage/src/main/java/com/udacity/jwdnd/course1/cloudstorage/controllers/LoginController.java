package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.forms.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping()
    public String firstVisit(@ModelAttribute("loginForm") LoginForm loginForm, Model model) {
        model.addAttribute("firstVisit", true);
        model.addAttribute("logout", true);
        System.out.println("First Visit is: " + model.getAttribute("firstVisit"));
        return "login";
    }

}
