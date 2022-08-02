package com.mediscreen.note.service;

import com.mediscreen.note.DTO.PatientDto;
import com.mediscreen.note.exception.RessourceNotFoundException;
import com.mediscreen.note.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface INoteService {


    Note saveNote(Note newNote) throws RessourceNotFoundException;

    Note updateNote(Note note) throws RessourceNotFoundException;

    Note deleteNote(String id) throws RessourceNotFoundException;

    List<Note> findAllByPatientId(Integer id);

    Note findById(String id) throws RessourceNotFoundException;

    PatientDto patientById(long id) throws RessourceNotFoundException;
}
