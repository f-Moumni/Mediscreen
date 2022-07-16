package com.mediscreen.note.util;

import com.mediscreen.note.dto.NoteDto;
import com.mediscreen.note.model.Note;
import com.mediscreen.note.util.NoteMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class NoteMapperTest {

    @Autowired
    NoteMapper noteMapper;

    NoteDto noteDto = new NoteDto("1", "a few notes for a test");
    Note     note    = new Note("62d12c25191bcc3f11d08547", 1, LocalDate.now(), "a few notes for a test");
    @Test
    public void toNoteTest(){
        //Act
        Note result =noteMapper.toNote(noteDto);
        //Assert
        assertThat(result.getPatientId()).isEqualTo(1);
        assertThat(result.getNote()).isEqualTo("a few notes for a test");
    }

    @Test
    public void toNoteDtoTest(){
        //Act
        NoteDto result =noteMapper.toNoteDto(note);
        //Assert
        assertThat(result).isEqualToComparingFieldByField(noteDto);

    }
}
