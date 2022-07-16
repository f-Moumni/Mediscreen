package com.mediscreen.note.service;

import com.mediscreen.note.exception.RessourceNotFoundException;
import com.mediscreen.note.model.Note;
import com.mediscreen.note.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {this.noteRepository = noteRepository;}

    public Note saveNote(Note newNote) {

        Note note = noteRepository.insert(newNote);
        return note;
    }

    public Note updateNote(Note note) throws RessourceNotFoundException {

        Note noteToUpdate = findById(note.getId());
        return noteRepository.save(note);

    }

    public Note deleteNote(String id) throws RessourceNotFoundException {

        Note note = findById(id);
        noteRepository.delete(note);
        return note;
    }

    public List<Note> findAllByPatientId(Integer id) {

        return noteRepository.findByPatientId(id);
    }

    public Note findById(String id) throws RessourceNotFoundException {

        return noteRepository.findById(id).orElseThrow(() -> new RessourceNotFoundException("note not found"));

    }
}
