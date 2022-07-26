package com.mediscreen.note.service;

import com.mediscreen.note.dto.PatientDto;
import com.mediscreen.note.exception.RessourceNotFoundException;
import com.mediscreen.note.model.Note;
import com.mediscreen.note.proxy.PatientProxy;
import com.mediscreen.note.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final PatientProxy   patientProxy;
    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(PatientProxy patientProxy, NoteRepository noteRepository) {

        this.patientProxy   = patientProxy;
        this.noteRepository = noteRepository;
    }

    public Note saveNote(Note newNote) throws RessourceNotFoundException {

        PatientDto patientDto = patientById(newNote.getPatientId());
        return noteRepository.insert(newNote);
    }

    public Note updateNote(Note note) throws RessourceNotFoundException {

        PatientDto patientDto   = patientById(note.getPatientId());
        Note       noteToUpdate = findById(note.getId());
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

    private PatientDto patientById(long id) throws RessourceNotFoundException {

        return patientProxy.getPatientById(id)
                           .orElseThrow(() -> new RessourceNotFoundException("note not found"));
    }
}
