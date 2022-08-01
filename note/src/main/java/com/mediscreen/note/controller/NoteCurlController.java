package com.mediscreen.note.controller;

import com.mediscreen.note.DTO.NoteDto;
import com.mediscreen.note.exception.RessourceNotFoundException;
import com.mediscreen.note.service.NoteService;
import com.mediscreen.note.util.NoteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/patHistory")
public class NoteCurlController {

    private final NoteService noteService;
    private final NoteMapper  noteMapper;

    @Autowired
    public NoteCurlController(NoteService noteService, NoteMapper noteMapper) {

        this.noteService = noteService;
        this.noteMapper  = noteMapper;
    }

    @PostMapping(value = "add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NoteDto> saveNote(NoteDto newNote) throws RessourceNotFoundException {

        return new ResponseEntity<>(noteMapper.toNoteDto(noteService.saveNote(noteMapper.toNote(newNote))), HttpStatus.CREATED);
    }



    @GetMapping(value = "getAll")
    public ResponseEntity<List<NoteDto>> getAllNote(String patientId) {

        return new ResponseEntity<>(noteService.findAllByPatientId(Integer.parseInt(patientId))
                                               .stream()
                                               .map(noteMapper::toNoteDto)
                                               .collect(Collectors.toList()), HttpStatus.OK);
    }

    @DeleteMapping(value = "delete")
    public ResponseEntity<NoteDto> deleteNote(String id) throws RessourceNotFoundException {

        return new ResponseEntity<>(noteMapper.toNoteDto(noteService.deleteNote(id)), HttpStatus.OK);
    }
}
