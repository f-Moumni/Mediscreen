package com.mediscreen.note.util;

import com.mediscreen.note.dto.NoteDto;
import com.mediscreen.note.model.Note;
import org.springframework.stereotype.Component;

@Component
public class NoteMapper {

    public Note toNote(NoteDto noteDto) {

        return new Note(Integer.parseInt(noteDto.getPatId()), noteDto.getE());
    }

    public NoteDto toNoteDto(Note note) {

        return new NoteDto(String.valueOf(note.getPatientId()), note.getNote());
    }
}
