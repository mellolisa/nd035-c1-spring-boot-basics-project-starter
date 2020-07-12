package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.domain.CreateUserResponse;
import com.udacity.jwdnd.course1.cloudstorage.forms.SignUpForm;
import com.udacity.jwdnd.course1.cloudstorage.mappers.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;

@Component
public class SignUpService {
    private final UserService userService;

    public SignUpService(UserService userService) {
        this.userService = userService;
    }

    public CreateUserResponse handleSignUpForm(SignUpForm form) {
        CreateUserResponse response;

        //get values from sign up form
        User user = new User();
        user.setFirstName(form.getFirstName());
        user.setLastName(form.getLastName());
        user.setUsername(form.getUsername());
        user.setPassword(form.getPassword());

        response = inputValidator(user);
        if(response.getErrorCode() !=0){
            return response;
        }

        //Create new user
        response = createUser(user);
        return response;
    }

    public CreateUserResponse createUser(User user){
        CreateUserResponse response = new CreateUserResponse();

        String username = user.getUsername();
        response.setUsername(username);

        //see if username is available
        if(!userService.isUsernameAvailable(username)){
            response = getErrorResponse(500);
            return response;
        }

        int rowsAdded = userService.createUser(user);
        if (rowsAdded < 0){
            response = getErrorResponse(501);
        }
        
        return response;
    }

    public CreateUserResponse inputValidator(User user){

        CreateUserResponse response = new CreateUserResponse();

        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String username = user.getUsername();
        String password = user.getPassword();

        response.setUsername(username);

        //validate input
        int errorCode = 0;

        //validate firstName
        errorCode = firstNameValidator(firstName);
        if(errorCode !=0){
            response = getErrorResponse(errorCode);
            return response;
        }

        //validate lastName
        errorCode = lastNameValidator(lastName);
        if(errorCode !=0){
            response = getErrorResponse(errorCode);
            return response;
        }

        //validate username
        errorCode = usernameValidator(username);
        if(errorCode != 0){
            response = getErrorResponse(errorCode);
            return response;
        }

        //validate password
        errorCode = passwordValidator(password);
        if(errorCode !=0){
            response = getErrorResponse(errorCode);
            return response;
        }

        response.setErrorCode(0);
        return response;
    }

    public int firstNameValidator(String firstName){
        int errorCode = 0;

        if(firstName.length() < 2){
            return 100;
        }
        else if(firstName.length() > 25){
            return 101;
        }

        return errorCode;
    }

    public int lastNameValidator(String firstName){
        int errorCode = 0;

        if(firstName.length() < 2){
            return 200;
        }
        else if(firstName.length() > 25){
            return 201;
        }

        return errorCode;
    }

    public int usernameValidator(String username){
        int errorCode = 0;

        if(username.length() < 5){
            return 300;
        }
        else if(username.length() > 25){
            return 301;
        }

        return errorCode;
    }

    public int passwordValidator(String username){
        int errorCode = 0;

        if(username.length() < 5){
            return 400;
        }
        else if(username.length() > 25){
            return 401;
        }

        return errorCode;
    }

    public CreateUserResponse getErrorResponse(int errorCode){
        CreateUserResponse response = new CreateUserResponse();
        String errorMessage = "System Error - Please try again!";

        response.setErrorCode(errorCode);

        switch(errorCode) {
            case 100:
                errorMessage = "First Name should be at least 2 characters.";
                break;
            case 101:
                errorMessage = "First Name should be at most 25 characters.";
                break;
            case 102:
                errorMessage = "First Name should only contain letters and hyphens.";
                break;
            case 200:
                errorMessage = "Last Name should be at least 2 characters.";
                break;
            case 201:
                errorMessage = "Last Name should be at most 25 characters.";
                break;
            case 202:
                errorMessage = "Last Name should only contain letters and hyphens.";
                break;
            case 300:
                errorMessage = "Username should be at least 5 characters.";
                break;
            case 301:
                errorMessage = "Username should be at most 25 characters.";
                break;
            case 302:
                errorMessage = "Username should only contain letters and numbers and the following special characters (@, -, .).";
                break;
            case 400:
                errorMessage = "Password should be at least 5 characters.";
                break;
            case 401:
                errorMessage = "Password should be at most 25 characters.";
                break;
            case 402:
                errorMessage = "Password should only contain letters and numbers and the following special characters (!, @, #, $, %, *).";
                break;
            case 500:
                errorMessage = "Username is not available - please try again!";
                break;
            case 501:
                errorMessage = "Database error - please try again!";
                break;
            default:
                //error is unknown
        }

        response.setErrorMessage(errorMessage);
        return response;
    }

}
