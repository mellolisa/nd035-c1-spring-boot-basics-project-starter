package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.forms.FilesForm;
import com.udacity.jwdnd.course1.cloudstorage.mappers.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mappers.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.SuperDuperFile;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {

    private final FileMapper fileMapper;
    private final UserMapper userMapper;

    public FileService(FileMapper fileMapper, UserMapper userMapper){
        this.fileMapper = fileMapper;
        this.userMapper = userMapper;
    }

    public int handleFilesForm(FilesForm form, String username){

        User user = userMapper.getUser(username);
        SuperDuperFile file = new SuperDuperFile();
        file.setUserId(user.getUserId());

        //TODO get the filedata

        int status = 100;

        //status = addFile(file);
        status = 1;  //hardcode it to work for now

        System.out.println("Status: " + status);
        return status;
    }

    public List<SuperDuperFile> getFiles(String username){
        User user = userMapper.getUser(username);

        return fileMapper.getFiles(user.getUserId());
    }

    public int addFile(SuperDuperFile file){

        int status = fileMapper.insert(file);

        return status;
    }

    public int deleteFile(int fileId){

        int status = fileMapper.delete(fileId);

        return status;
    }

}

