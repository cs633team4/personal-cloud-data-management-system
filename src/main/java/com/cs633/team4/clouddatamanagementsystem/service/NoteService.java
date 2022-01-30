package com.cs633.team4.clouddatamanagementsystem.service;

import com.cs633.team4.clouddatamanagementsystem.mapper.NoteMapper;
import com.cs633.team4.clouddatamanagementsystem.model.Note;
import com.cs633.team4.clouddatamanagementsystem.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public Integer addNote(Note note) {
        Note newNote = new Note(0, note.getNoteTitle(), note.getNoteDescription(), note.getUserId());
        return noteMapper.insert(newNote);
    }

    public Integer updateNote(Note note) {
        return noteMapper.update(note);
    }

    public Integer deleteNote(Integer noteId) {
        return noteMapper.delete(noteId);
    }

    public Note getNote(Integer noteId) {
        return noteMapper.getNote(noteId);
    }

    public List<Note> getAllNotes(User user) {
        return noteMapper.getAllNotesByUser(user.getUserId());
    }
}

