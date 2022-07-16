package com.mediscreen.note.controller;

import com.mediscreen.note.exception.RessourceNotFoundException;
import com.mediscreen.note.model.Note;
import com.mediscreen.note.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping("note")
@RestController
public class NoteController {

    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {this.noteService = noteService;}



    @PostMapping
    public ResponseEntity<Note> saveNote(@RequestBody Note newNote) {

        return new ResponseEntity<>(noteService.saveNote(newNote), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Note> updateNote(@RequestBody Note newNote) throws RessourceNotFoundException {

        return new ResponseEntity<>(noteService.updateNote(newNote), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Note>> getAllNote(@RequestParam Integer patientId) {

        return new ResponseEntity<>(noteService.findAllByPatientId(patientId), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Note> deleteNote(@RequestParam String id) throws RessourceNotFoundException {

        return new ResponseEntity<>(noteService.deleteNote(id), HttpStatus.OK);
    }
}
