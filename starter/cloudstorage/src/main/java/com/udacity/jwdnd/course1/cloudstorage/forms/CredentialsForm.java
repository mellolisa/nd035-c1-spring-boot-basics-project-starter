package com.udacity.jwdnd.course1.cloudstorage.forms;

public class CredentialsForm {
    private String credActionType;
    private String credentialId;
    private String username;
    private String password;
    private String salt;
    private String url;

    public String getCredActionType() {
        return credActionType;
    }

    public void setCredActionType(String credActionType) {
        this.credActionType = credActionType;
    }

    public String getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(String credentialId) {
        this.credentialId = credentialId;
    }

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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
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
                ", credActionType'" + credActionType + '\'' +
                ", credentialId'" + credentialId + '\'' +
                ", username='" + username + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
