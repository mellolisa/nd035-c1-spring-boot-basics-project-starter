package com.udacity.jwdnd.course1.cloudstorage.forms;

public class CredentialsForm {
    private String username;
    private String password;
    private String salt;
    private String url;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        //do not display passwords in forms
        return "CredentialsForm{" +
                ", username='" + username + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
