package com.udacity.jwdnd.course1.cloudstorage.forms;

public class LoginForm {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    //do not display password in logs
    public String toString() {
        return "LoginForm{" +
                "username='" + username + '\'' +
                '}';
    }
}