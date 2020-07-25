package com.udacity.jwdnd.course1.cloudstorage.forms;

public class NotesForm {
    private String noteActionType;
    private String noteId;
    private String title;
    private String description;

    public String getNoteActionType() {
        return noteActionType;
    }

    public void setNoteActionType(String noteActionType) {
        this.noteActionType = noteActionType;
    }

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "NotesForm{" +
                "noteActionType='" + noteActionType + '\'' +
                ", noteId='" + noteId + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
