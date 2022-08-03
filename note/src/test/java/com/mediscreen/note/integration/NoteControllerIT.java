package com.mediscreen.note.integration;

import com.mediscreen.note.model.Note;
import com.mediscreen.note.repository.NoteRepository;
import com.mediscreen.note.util.JsonTestMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest
public class NoteControllerIT {


    Note note = new Note("62d12c25191bcc3f11d08547", 4, LocalDate.now(), "a few notes for a test");
    @Autowired
    NoteRepository noteRepository;

    @Autowired
    private MockMvc mvc;

    @BeforeEach
    void setUp() {

        noteRepository.insert(note);
    }

    @AfterEach
    void tearDown() {

        noteRepository.delete(note);
    }

    @Test
    void SaveNoteTest_shouldReturnNoteAdded() throws Exception {
        noteRepository.delete(note);
        //Act
        mvc.perform(post("/note")
                   .contentType(MediaType.APPLICATION_JSON)
                   .accept(MediaType.APPLICATION_JSON).content(JsonTestMapper.asJsonString(note)))
           .andDo(print())
           .andExpect(status().isCreated());

    }


    @Test
    void updateNoteTest_shouldReturnNoteUpdated() throws Exception {
        //Act
        mvc.perform(put("/note")
                   .contentType(MediaType.APPLICATION_JSON)
                   .accept(MediaType.APPLICATION_JSON).content(JsonTestMapper.asJsonString(note)))
           .andDo(print())
           .andExpect(status().isOk());

    }

    @Test
    void deleteNoteTest_shouldReturnNoteDeleted() throws Exception {

        //Act
        mvc.perform(delete("/note")
                   .contentType(MediaType.APPLICATION_JSON)
                   .accept(MediaType.APPLICATION_JSON).param("id", "62d12c25191bcc3f11d08547"))
           .andDo(print())
           .andExpect(status().isOk());

    }
    @Test
    void deleteNoteTest_shouldReturnStatus404() throws Exception {

        noteRepository.delete(note);
        //Act
        mvc.perform(delete("/note")
                   .contentType(MediaType.APPLICATION_JSON)
                   .accept(MediaType.APPLICATION_JSON).param("id", "62d12c25191bcc3f11d08547"))
           .andDo(print())
           .andExpect(status().isNotFound());
    }

    @Test
    void getAllNoteTest_shouldReturnListOfNote() throws Exception {
        //Act
        mvc.perform(get("/note")
                   .contentType(MediaType.APPLICATION_JSON)
                   .accept(MediaType.APPLICATION_JSON).param("patientId", "4"))
           .andDo(print())
           .andExpect(status().isOk());

    }
}
