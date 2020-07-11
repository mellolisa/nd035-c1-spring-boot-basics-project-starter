package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.domain.CreateUserResponse;
import com.udacity.jwdnd.course1.cloudstorage.forms.SignUpForm;
import org.springframework.stereotype.Component;

@Component
public class SignUpService {
    public CreateUserResponse createUser(SignUpForm form) {
        CreateUserResponse response = new CreateUserResponse();
        EncryptionService encryptionService = new EncryptionService();

        //get values from sign up form
        String firstName = form.getFirstName();
        String lastName = form.getLastName();
        String username = form.getUsername();
        String password = form.getPassword();

        response = inputValidator(form);
        if(response.getErrorCode() !=0){
            return response;
        }

        //encrypt password
        //String key = "Test";
        //String encryptedPassword = encryptionService.encryptValue(password, key);

        //add to database

        return response;
    }

    public CreateUserResponse inputValidator(SignUpForm form){

        CreateUserResponse response = new CreateUserResponse();

        String firstName = form.getFirstName();
        String lastName = form.getLastName();
        String username = form.getUsername();
        String password = form.getPassword();

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
            default:
                //error is unknown
        }

        response.setErrorMessage(errorMessage);
        return response;
    }
}
