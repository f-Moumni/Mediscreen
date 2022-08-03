package com.mediscreen.note.controller;

import com.mediscreen.note.exception.RessourceNotFoundException;
import com.mediscreen.note.model.Note;
import com.mediscreen.note.service.INoteService;
import com.mediscreen.note.service.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping("note")
@RestController
public class NoteController {

    private final  Logger      LOGGER = LoggerFactory.getLogger(NoteController.class);

    private final INoteService noteService;

    @Autowired
    public NoteController(INoteService noteService) {this.noteService = noteService;}



    @PostMapping
    public ResponseEntity<Note> saveNote(@RequestBody Note newNote) throws RessourceNotFoundException {
        LOGGER.info("saving note request");
        Note note = noteService.saveNote(newNote);
        return new ResponseEntity<>(note, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Note> updateNote(@RequestBody Note newNote) throws RessourceNotFoundException {
        LOGGER.info("update note request");
        return new ResponseEntity<>(noteService.updateNote(newNote), HttpStatus.OK);
    }

    /**
     * get all notes for given patient
     * @param patientId patient id
     * @return list note
     */
    @GetMapping
    public ResponseEntity<List<Note>> getAllNote(@RequestParam Integer patientId) {
        LOGGER.info("get All note request");
        return new ResponseEntity<>(noteService.findAllByPatientId(patientId), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Note> deleteNote(@RequestParam String id) throws RessourceNotFoundException {
        LOGGER.info("delete note request");
        return new ResponseEntity<>(noteService.deleteNote(id), HttpStatus.OK);
    }
}
