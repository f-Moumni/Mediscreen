package com.mediscreen.note.controller;

import com.mediscreen.note.DTO.NoteDto;
import com.mediscreen.note.model.Note;
import com.mediscreen.note.service.NoteService;
import com.mediscreen.note.util.NoteMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(NoteCurlController.class)
public class NoteCurlControllerTest {

    NoteDto noteDto = new NoteDto("1", "a few notes for a test");
    Note    note    = new Note("62d12c25191bcc3f11d08547", 1, LocalDate.now(), "a few notes for a test");
    @Autowired
    private MockMvc     mvc;
    @MockBean
    private NoteService noteService;
    @MockBean
    private NoteMapper  noteMapper;

    @BeforeEach
    void setUp() {

        when(noteMapper.toNote(noteDto)).thenReturn(note);
        when(noteMapper.toNoteDto(note)).thenReturn(noteDto);
    }

    @Test
    void SaveNoteTest_shouldReturnNoteAdded() throws Exception {
        //Arrange
        when(noteService.saveNote(note)).thenReturn(note);
        //Act
        mvc.perform(post("/patHistory/add")
                   .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                   .param("patId", "1").param("e", "a few notes for a test"))
           .andDo(print())
           .andExpect(status().isCreated());

    }



    @Test
    void deleteNoteTest_shouldReturnNoteDeleted() throws Exception {
        //Arrange
        when(noteService.deleteNote(note.getId())).thenReturn(note);
        //Act
        mvc.perform(delete("/patHistory/delete")
                   .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                   .param("id", "62d12c25191bcc3f11d08547"))
           .andDo(print())
           .andExpect(status().isOk());

    }

    @Test
    void getAllNoteTest_shouldReturnListOfNote() throws Exception {
        //Arrange
        when(noteService.findAllByPatientId(note.getPatientId())).thenReturn(List.of(note));
        //Act
        mvc.perform(get("/patHistory/getAll")
                   .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                   .param("patientId", "1"))
           .andDo(print())
           .andExpect(status().isOk());

    }
}
