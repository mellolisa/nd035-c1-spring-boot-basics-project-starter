package com.udacity.jwdnd.course1.cloudstorage.forms;

import java.sql.Blob;

public class FilesForm {
    private Blob fileUpload;

    public Blob getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(Blob fileUpload) {
        this.fileUpload = fileUpload;
    }
}
