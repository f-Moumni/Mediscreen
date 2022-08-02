package com.mediscreen.note.util;

import com.mediscreen.note.DTO.NoteDto;
import com.mediscreen.note.model.Note;
import com.mediscreen.note.service.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * note mapper
 */
@Component
public class NoteMapper {

    private final Logger LOGGER = LoggerFactory.getLogger(NoteMapper.class);

    /**
     * mappe noteDTo to note
     * @param noteDto : noteDTO
     * @return note
     */
    public Note toNote(NoteDto noteDto) {
        LOGGER.debug("mapping noteDTO of patient id:{} to note",noteDto.getPatId());
        return new Note(Integer.parseInt(noteDto.getPatId()), noteDto.getE());
    }

    /**
     * mappe note to noteDTO
     * @param note note
     * @return noteDTO
     */
    public NoteDto toNoteDto(Note note) {
        LOGGER.debug("mapping note of patient id:{} to noteDTO",note.getPatientId());
        return new NoteDto(String.valueOf(note.getPatientId()), note.getNote());
    }
}
