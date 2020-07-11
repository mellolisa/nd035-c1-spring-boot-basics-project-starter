package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.domain.CreateUserResponse;
import com.udacity.jwdnd.course1.cloudstorage.forms.SignUpForm;
import com.udacity.jwdnd.course1.cloudstorage.services.SignUpService;
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
        System.out.println("First Visit is: " + model.getAttribute("firstVisit"));
        return "signup";
    }

    @PostMapping()
    public String subsequentVisit(@ModelAttribute("signUpForm") SignUpForm signUpForm, Model model) {
        model.addAttribute("firstVisit", false);

        SignUpService service = new SignUpService();
        CreateUserResponse response = service.createUser(signUpForm);

        if(response.getErrorCode() == 0) {
            model.addAttribute("errorCode", 0);
            model.addAttribute("firstName", signUpForm.getFirstName());
        } else {
            model.addAttribute( "errorCode", response.getErrorCode());
            model.addAttribute( "errorMessage", response.getErrorMessage());
        }

        System.out.println("First Visit is: " + model.getAttribute("firstVisit"));
        System.out.println("Error Code is: " + model.getAttribute("errorCode"));
        return "signup";
    }
}
