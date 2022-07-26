package com.mediscreen.patient.unit;

import com.mediscreen.patient.constant.Gender;
import com.mediscreen.patient.controller.PatientController;
import com.mediscreen.patient.exception.RessourceNotFoundException;
import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.service.PatientService;
import com.mediscreen.patient.util.JsonTestMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static java.time.LocalDate.now;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PatientController.class)
public class PatientControllerTest {

    @Autowired
    private MockMvc        mvc;
    @MockBean
    private PatientService patientService;
    private Patient        patient = new Patient(1, "john", "doe", now(), Gender.MASCULINE, "rue des nations", "0890009");

    @Test
    void getAllPatientsTest_shouldReturnListOfPatients() throws Exception {
        //Arrange
        when(patientService.getAllPatients()).thenReturn(List.of(patient));
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
        //Arrange
        when(patientService.updatePatient(patient)).thenReturn(patient);
        //Act
        mvc.perform(put("/patient")
                   .contentType(MediaType.APPLICATION_JSON)
                   .accept(MediaType.APPLICATION_JSON).content(JsonTestMapper.asJsonString(patient)))
           .andDo(print())
           .andExpect(status().isOk());
    }

    @Test
    void addPatientTest_shouldReturnPatientAdded() throws Exception {
        //Arrange
        when(patientService.savePatient(patient)).thenReturn(patient);
        //Act
        mvc.perform(post("/patient")
                   .contentType(MediaType.APPLICATION_JSON)
                   .accept(MediaType.APPLICATION_JSON).content(JsonTestMapper.asJsonString(patient)))
           .andDo(print())
           .andExpect(status().isCreated());

    }

    @Test
    void removePatientTest_shouldReturnPatientAdded() throws Exception {
        //Arrange
        when(patientService.deletePatient(1)).thenReturn(patient);
        //Act
        mvc.perform(delete("/patient")
                   .contentType(MediaType.APPLICATION_JSON)
                   .accept(MediaType.APPLICATION_JSON).param("id", "1"))
           .andDo(print())
           .andExpect(status().isOk());

    }
    @Test
    void getPatientByIdTest_shouldReturnThrowException() throws Exception {
        //Arrange
        when(patientService.findById(1)).thenThrow(RessourceNotFoundException.class);
        //Act
        mvc.perform(get("/patient")
                   .contentType(MediaType.APPLICATION_JSON)
                   .accept(MediaType.APPLICATION_JSON)
                   .param("id", "1"))
           .andDo(print())
           .andExpect(status().isNotFound());

    }

    @Test
    void getPatientByIdTest_shouldReturnPatient() throws Exception {
        //Arrange
        when(patientService.findById(any(Long.class))).thenReturn(patient);
        //Act
        mvc.perform(get("/patient")
                   .contentType(MediaType.APPLICATION_JSON)
                   .accept(MediaType.APPLICATION_JSON)
                   .param("id", "1"))
           .andDo(print())
           .andExpect(status().isOk());

    }
    @Test
    void getPatientByLastNameTest_shouldReturnPatient() throws Exception {
        //Arrange
        when(patientService.findByLatName(any())).thenReturn(List.of(patient));
        //Act
        mvc.perform(get("/patient/lastname")
                   .contentType(MediaType.APPLICATION_JSON)
                   .accept(MediaType.APPLICATION_JSON)
                   .param("lastname", "doe"))
           .andDo(print())
           .andExpect(status().isOk());

    }


}
