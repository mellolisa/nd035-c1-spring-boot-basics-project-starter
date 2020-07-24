package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.forms.CredentialsForm;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.AuthenticationService;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final CredentialService credentialService;

    public HomeController(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @GetMapping()
    public String getHomePage(Authentication authentication,
                               @ModelAttribute("credentialsForm") CredentialsForm credentialsForm,
                               Model model) {
        showHomePage(authentication, credentialsForm, model);
        return "home";
    }

    @GetMapping("/{id}")
    public String deleteActions(Authentication authentication, @ModelAttribute("credentialsForm") CredentialsForm credentialsForm, Model model,
                                @PathVariable(value="id") String credId,
                                @RequestParam(required = false) String action){

        int id = Integer.parseInt(credId);

        model.addAttribute("toggleCredentials", true);

        if(credentialService.deleteCredential(id) == 1){
            System.out.println("Deleted credential: " + id);
            model.addAttribute("credSuccess", "The delete action was successful.");
        }
        else {
            model.addAttribute("credError", "The delete action was unsuccessful.");
        }

        showHomePage(authentication, credentialsForm, model);

        return "home";
    }

    @PostMapping()
    public String postActions(Authentication authentication,
                              @ModelAttribute("credentialsForm") CredentialsForm credentialsForm,
                              Model model){

        model.addAttribute("toggleCredentials", true);
        String formAction = credentialsForm.getCredActionType();

        if(credentialService.handleCredentialsForm(credentialsForm, authentication.getName()) > 0){
            System.out.println("Credential Action: " + formAction);
            model.addAttribute("credSuccess", "The " + formAction + " action was successful.");
        } else {
            model.addAttribute("credError", "The " + formAction + " action was unsuccessful.");
        }

        showHomePage(authentication, credentialsForm, model);

        return "home";
    }

    public void showHomePage(Authentication authentication, @ModelAttribute("credentialsForm") CredentialsForm credentialsForm, Model model){
        model.addAttribute("firstVisit", true);
        model.addAttribute("showCredentials", false);

        String username = authentication.getName();

        List<Credential> credentials = credentialService.getCredentials(username);
        if(!credentials.isEmpty()) {
            model.addAttribute("showCredentials", true);
            model.addAttribute("credentials", credentials);
        }

        System.out.println("Home First Visit is: " + model.getAttribute("firstVisit"));
        System.out.println("Credentials: " + model.getAttribute("credentials"));
        System.out.println("toggleCredentials: " + model.getAttribute("toggleCredentials"));
    }
}