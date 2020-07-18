package com.udacity.jwdnd.course1.cloudstorage.model;

public class Credential {
    private int credentialId;
    private int userId;
    private String username;
    private String key;
    private String password;
    private String url;

    public int getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(int credentialId) {
        this.credentialId = credentialId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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
        //do not show passwords in logs
        return "Credential{" +
                "credentialId=" + credentialId +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", key='" + key + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
