package com.mediscreen.note.service;

import com.mediscreen.note.DTO.PatientDto;
import com.mediscreen.note.exception.RessourceNotFoundException;
import com.mediscreen.note.model.Note;
import com.mediscreen.note.proxy.PatientProxy;
import com.mediscreen.note.repository.NoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService implements INoteService {

    private final PatientProxy   patientProxy;
    private final NoteRepository noteRepository;
    private final Logger         LOGGER = LoggerFactory.getLogger(NoteService.class);

    @Autowired
    public NoteService(PatientProxy patientProxy, NoteRepository noteRepository) {

        this.patientProxy   = patientProxy;
        this.noteRepository = noteRepository;
    }

    @Override
    public Note saveNote(Note newNote) throws RessourceNotFoundException {
        LOGGER.info("saving note");
        PatientDto patientDto = patientById(newNote.getPatientId());
        return noteRepository.insert(newNote);
    }

    @Override
    public Note updateNote(Note note) throws RessourceNotFoundException {
        LOGGER.info("updating note");
        PatientDto patientDto   = patientById(note.getPatientId());
        Note       noteToUpdate = findById(note.getId());
        return noteRepository.save(note);

    }

    @Override
    public Note deleteNote(String id) throws RessourceNotFoundException {
        LOGGER.info("deleting note");
        Note note = findById(id);
        noteRepository.delete(note);
        return note;
    }

    @Override
    public List<Note> findAllByPatientId(Integer id) {
        LOGGER.info("getting all notes of patient id {}",id);
        return noteRepository.findByPatientId(id);
    }

    @Override
    public Note findById(String id) throws RessourceNotFoundException {
        LOGGER.info("getting note by id {}",id);
        return noteRepository.findById(id).orElseThrow(() -> new RessourceNotFoundException("note not found"));

    }

    @Override
    public PatientDto patientById(long id) throws RessourceNotFoundException {
        LOGGER.info("getting patient by id {}",id);
        return patientProxy.getPatientById(id)
                           .orElseThrow(() -> new RessourceNotFoundException("patient with id: "+id+ " not found"));
    }
}
