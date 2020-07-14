package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.forms.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping()
    public String showLoginPage(@ModelAttribute("loginForm") LoginForm loginForm, Model model) {
        model.addAttribute("firstVisit", true);
        System.out.println("Home First Visit is: " + model.getAttribute("firstVisit"));
        return "home";
    }
}