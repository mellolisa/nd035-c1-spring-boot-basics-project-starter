package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.forms.CredentialsForm;
import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.mappers.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {

    private final CredentialMapper credentialMapper;
    private final UserMapper userMapper;

    public CredentialService(CredentialMapper credentialMapper, UserMapper userMapper){
        this.credentialMapper = credentialMapper;
        this.userMapper = userMapper;
    }

    public int handleCredentialsForm(CredentialsForm form, String username){

        User user = userMapper.getUser(username);
        Credential credential = new Credential();
        credential.setUserId(user.getUserId());
        credential.setUrl(form.getUrl());
        credential.setUsername(form.getUsername());
        credential.setPassword(form.getPassword());

        //mock data
        credential.setKey("aaaaa");

        int status = addCredential(credential);
        //just to see if it works
        status = deleteCredential(0);
        return status;
    }

    public List<Credential> getCredentials(String username){
        User user = userMapper.getUser(username);

        return credentialMapper.getCredentials(user.getUserId());
    }

    public int addCredential(Credential credential){

        int status = credentialMapper.insert(credential);

        return status;
    }

    public int deleteCredential(int credentialId){

        int status = credentialMapper.delete(credentialId);

        return status;
    }

}
