package com.mediscreen.note.controller;

import com.mediscreen.note.exception.RessourceNotFoundException;
import com.mediscreen.note.model.Note;
import com.mediscreen.note.service.NoteService;
import com.mediscreen.note.util.JsonTestMapper;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(NoteController.class)
public class NoteControllerTest {

    Note note = new Note("62d12c25191bcc3f11d08547", 1, LocalDate.now(), "a few notes for a test");
    @Autowired
    private MockMvc     mvc;
    @MockBean
    private NoteService noteService;

    @Test
    void SaveNoteTest_shouldReturnNoteAdded() throws Exception {
        //Arrange
        when(noteService.saveNote(note)).thenReturn(note);
        //Act
        mvc.perform(post("/note")
                   .contentType(MediaType.APPLICATION_JSON)
                   .accept(MediaType.APPLICATION_JSON).content(JsonTestMapper.asJsonString(note)))
           .andDo(print())
           .andExpect(status().isCreated());

    }

    @Test
    void updateNoteTest_shouldReturnNoteUpdated() throws Exception {
        //Arrange
        when(noteService.updateNote(note)).thenReturn(note);
        //Act
        mvc.perform(put("/note")
                   .contentType(MediaType.APPLICATION_JSON)
                   .accept(MediaType.APPLICATION_JSON).content(JsonTestMapper.asJsonString(note)))
           .andDo(print())
           .andExpect(status().isOk());
    }

    @Test
    void addNoteTest_shouldReturnStatus404() throws Exception {
        //Arrange
        when(noteService.saveNote(any(Note.class))).thenThrow(RessourceNotFoundException.class);
        //Act
        mvc.perform(post("/note")
                   .contentType(MediaType.APPLICATION_JSON)
                   .accept(MediaType.APPLICATION_JSON).content(JsonTestMapper.asJsonString(note)))
           .andDo(print())
           .andExpect(status().isNotFound());

    }

    @Test
    void updateNoteTest_shouldReturnStatus404() throws Exception {
        //Arrange
        when(noteService.updateNote(any(Note.class))).thenThrow(RessourceNotFoundException.class);
        //Act
        mvc.perform(put("/note")
                   .contentType(MediaType.APPLICATION_JSON)
                   .accept(MediaType.APPLICATION_JSON).content(JsonTestMapper.asJsonString(note)))
           .andDo(print())
           .andExpect(status().isNotFound());

    }

    @Test
    void deleteNoteTest_shouldReturnNoteDeleted() throws Exception {
        //Arrange
        when(noteService.deleteNote(note.getId())).thenReturn(note);
        //Act
        mvc.perform(delete("/note")
                   .contentType(MediaType.APPLICATION_JSON)
                   .accept(MediaType.APPLICATION_JSON).param("id", "62d12c25191bcc3f11d08547"))
           .andDo(print())
           .andExpect(status().isOk());

    }

    @Test
    void deleteNoteTest_shouldReturnStatus404() throws Exception {
        //Arrange
        when(noteService.deleteNote(note.getId())).thenThrow(RessourceNotFoundException.class);
        //Act
        mvc.perform(delete("/note")
                   .contentType(MediaType.APPLICATION_JSON)
                   .accept(MediaType.APPLICATION_JSON).param("id", "62d12c25191bcc3f11d08547"))
           .andDo(print())
           .andExpect(status().isNotFound());

    }

    @Test
    void getAllNoteTest_shouldReturnListOfNote() throws Exception {
        //Arrange
        when(noteService.findAllByPatientId(note.getPatientId())).thenReturn(List.of(note));
        //Act
        mvc.perform(get("/note")
                   .contentType(MediaType.APPLICATION_JSON)
                   .accept(MediaType.APPLICATION_JSON).param("patientId", "1"))
           .andDo(print())
           .andExpect(status().isOk());

    }

}
