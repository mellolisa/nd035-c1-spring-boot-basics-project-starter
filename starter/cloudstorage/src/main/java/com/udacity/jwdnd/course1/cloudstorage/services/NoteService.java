package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.forms.NotesForm;
import com.udacity.jwdnd.course1.cloudstorage.mappers.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.mappers.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteMapper noteMapper;
    private final UserMapper userMapper;

    public NoteService(NoteMapper noteMapper, UserMapper userMapper){
        this.noteMapper = noteMapper;
        this.userMapper = userMapper;
    }

    public int handleNotesForm(NotesForm form, String username){

        User user = userMapper.getUser(username);
        Note note = new Note();
        note.setUserId(user.getUserId());
        note.setNoteTitle(form.getTitle());
        note.setNoteDescription(form.getDescription());
        String action = form.getNoteActionType();
        String noteId = form.getNoteId();
        if(!noteId.isEmpty()){
            note.setNoteId(Integer.parseInt(noteId));
        }

        int status = 100;

        if(action.contains("Add")){
            status = addNote(note);
        } else if(action.contains("Edit")){
            status = editNote(note);
        }

        System.out.println("Status: " + status);
        return status;
    }

    public List<Note> getNotes(String username){
        User user = userMapper.getUser(username);

        return noteMapper.getNotes(user.getUserId());
    }

    public int addNote(Note note){

        int status = noteMapper.insert(note);

        return status;
    }

    public int deleteNote(int noteId){

        int status = noteMapper.delete(noteId);

        return status;
    }

    public int editNote(Note note){

        int status = noteMapper.update(note);

        return status;
    }

}

