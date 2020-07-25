package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.forms.CredentialsForm;
import com.udacity.jwdnd.course1.cloudstorage.forms.NotesForm;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final CredentialService credentialService;
    private final NoteService noteService;

    public HomeController(CredentialService credentialService, NoteService noteService) {
        this.credentialService = credentialService;
        this.noteService = noteService;
    }

    @GetMapping()
    public String getHomePage(Authentication authentication,
                               @ModelAttribute("credentialsForm") CredentialsForm credentialsForm,
                               @ModelAttribute("notesForm") NotesForm notesForm,
                               Model model) {
        showHomePage(authentication, credentialsForm, notesForm, model);
        return "home";
    }

    @GetMapping("/credential/{id}")
    public String deleteCredActions(Authentication authentication,
                                @ModelAttribute("credentialsForm") CredentialsForm credentialsForm,
                                @ModelAttribute("notesForm") NotesForm notesForm,
                                Model model,
                                @PathVariable(value="id") String objectId){

        int id = Integer.parseInt(objectId);
        model.addAttribute("toggleCredentials", true);

        if(credentialService.deleteCredential(id) == 1){
            System.out.println("Deleted credential: " + id);
            model.addAttribute("credSuccess", "The Delete action was successful.");
        }
        else {
            model.addAttribute("credError", "The Delete action was unsuccessful.");
        }

        showHomePage(authentication, credentialsForm, notesForm, model);
        return "home";
    }

    @GetMapping("/note/{id}")
    public String deleteNoteActions(Authentication authentication,
                                    @ModelAttribute("credentialsForm") CredentialsForm credentialsForm,
                                    @ModelAttribute("notesForm") NotesForm notesForm,
                                    Model model,
                                    @PathVariable(value="id") String objectId){

        int id = Integer.parseInt(objectId);
        model.addAttribute("toggleNotes", true);

        if(noteService.deleteNote(id) == 1){
            System.out.println("Deleted note: " + id);
            model.addAttribute("noteSuccess", "The Delete action was successful.");
        }
        else {
            model.addAttribute("noteError", "The Delete action was unsuccessful.");
        }

        showHomePage(authentication, credentialsForm, notesForm, model);
        return "home";
    }

    @PostMapping("/credential")
    public String postCredentialActions(Authentication authentication,
                              @ModelAttribute("credentialsForm") CredentialsForm credentialsForm,
                              @ModelAttribute("notesForm") NotesForm notesForm,
                              Model model){

        model.addAttribute("toggleCredentials", true);
        String formAction = credentialsForm.getCredActionType();

        if(credentialService.handleCredentialsForm(credentialsForm, authentication.getName()) > 0){
            System.out.println("Credential Action: " + formAction);
            model.addAttribute("credSuccess", "The " + formAction + " action was successful.");
        } else {
            model.addAttribute("credError", "The " + formAction + " action was unsuccessful.");
        }

        showHomePage(authentication, credentialsForm, notesForm, model);

        return "home";
    }

    @PostMapping("/note")
    public String postNoteActions(Authentication authentication,
                              @ModelAttribute("credentialsForm") CredentialsForm credentialsForm,
                              @ModelAttribute("notesForm") NotesForm notesForm,
                              Model model){

        model.addAttribute("toggleNotes", true);
        String formAction = notesForm.getNoteActionType();

        if(noteService.handleNotesForm(notesForm, authentication.getName()) > 0){
            System.out.println("Note Action: " + formAction);
            model.addAttribute("noteSuccess", "The " + formAction + " action was successful.");
        } else {
            model.addAttribute("noteError", "The " + formAction + " action was unsuccessful.");
        }

        showHomePage(authentication, credentialsForm, notesForm, model);

        return "home";
    }

    public void showHomePage(Authentication authentication,
                             @ModelAttribute("credentialsForm") CredentialsForm credentialsForm,
                             @ModelAttribute("notesForm") NotesForm notesForm,
                             Model model){
        model.addAttribute("showCredentials", false);
        model.addAttribute("showNotes", false);

        String username = authentication.getName();

        List<Credential> credentials = credentialService.getCredentials(username);
        if(!credentials.isEmpty()) {
            model.addAttribute("showCredentials", true);
            model.addAttribute("credentials", credentials);
        }

        List<Note> notes = noteService.getNotes(username);
        if(!notes.isEmpty()) {
            model.addAttribute("showNotes", true);
            model.addAttribute("notes", notes);
        }

        System.out.println("Credentials: " + model.getAttribute("credentials"));
        System.out.println("Notes: " + model.getAttribute("notes"));
        System.out.println("toggleCredentials: " + model.getAttribute("toggleCredentials"));
    }
}