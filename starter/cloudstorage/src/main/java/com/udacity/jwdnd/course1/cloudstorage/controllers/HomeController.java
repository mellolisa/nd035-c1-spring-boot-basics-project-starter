package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.forms.CredentialsForm;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final CredentialService credentialService;

    public HomeController(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @GetMapping()
    public String showHomePage(Authentication authentication, @ModelAttribute("credentialsForm") CredentialsForm credentialsForm, Model model) {
        model.addAttribute("firstVisit", true);
        model.addAttribute("showCredentials", false);

        String username = authentication.getName();

        List<Credential> credentials = credentialService.getCredentials(username);
        if(!credentials.isEmpty()) {
            model.addAttribute("showCredentials", true);
            model.addAttribute("credentials", credentials);
        }

        System.out.println("Home First Visit is: " + model.getAttribute("firstVisit"));
        System.out.println("Credentials: " + credentials);
        return "home";
    }
}