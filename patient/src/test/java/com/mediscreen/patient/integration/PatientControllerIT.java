package com.mediscreen.patient.integration;

import com.mediscreen.patient.constant.Gender;
import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.service.PatientService;
import com.mediscreen.patient.util.JsonTestMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static java.time.LocalDate.now;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest
@Sql(scripts = "classpath:data.sql",
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class PatientControllerIT {

    @Autowired
    private MockMvc        mvc;

    private Patient        patient = new Patient(1, "john", "doe", now(), Gender.MASCULINE, "0890009", "rue des nations");

    @Test
    void getAllPatientsTest_shouldReturnListOfPatients() throws Exception {

        //Act
        mvc.perform(get("/patient/all")
                   .contentType(MediaType.APPLICATION_JSON)
                   .accept(MediaType.APPLICATION_JSON))
           .andDo(print())
           .andExpect(status().isOk())
           .andExpect(content().string(JsonTestMapper.asJsonString(List.of(patient))));
    }

    @Test
    void updatePatientTest_shouldReturnPatientUpdated() throws Exception {

        //Act
        mvc.perform(put("/patient")
                   .contentType(MediaType.APPLICATION_JSON)
                   .accept(MediaType.APPLICATION_JSON).content(JsonTestMapper.asJsonString(patient)))
           .andDo(print())
           .andExpect(status().isOk());
    }

    @Test
    void addPatientTest_shouldReturnPatientAdded() throws Exception {

        //Act
        mvc.perform(post("/patient")
                   .contentType(MediaType.APPLICATION_JSON)
                   .accept(MediaType.APPLICATION_JSON).content(JsonTestMapper.asJsonString(patient)))
           .andDo(print())
           .andExpect(status().isCreated());

    }
    @Test
    void removePatientTest_shouldReturnPatientAdded() throws Exception {

        //Act
        mvc.perform(delete("/patient")
                   .contentType(MediaType.APPLICATION_JSON)
                   .accept(MediaType.APPLICATION_JSON).param("id","1"))
           .andDo(print())
           .andExpect(status().isOk());

    }


}
