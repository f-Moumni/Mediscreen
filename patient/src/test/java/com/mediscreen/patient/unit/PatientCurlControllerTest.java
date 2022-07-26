package com.mediscreen.patient.unit;

import com.mediscreen.patient.constant.Gender;
import com.mediscreen.patient.controller.PatientCurlController;
import com.mediscreen.patient.dto.PatientDto;
import com.mediscreen.patient.exception.RessourceNotFoundException;
import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.service.PatientService;
import com.mediscreen.patient.util.JsonTestMapper;
import com.mediscreen.patient.util.PatientMapper;
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

import static java.time.LocalDate.now;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PatientCurlController.class)
public class PatientCurlControllerTest {

    @Autowired
    private MockMvc        mvc;
    @MockBean
    private PatientService patientService;
    @MockBean
    private PatientMapper  patientMapper;

    private Patient    patient    = new Patient(1, "john", "doe", now(), Gender.MASCULINE, "33 rue des nations", "0890009");
    private PatientDto patientDto = new PatientDto(1, "doe", "john", LocalDate.now()
                                                                              .toString(), "M", "0890009", "33 rue des nations");

    @BeforeEach
    void setUp() {

        when(patientMapper.toPatientDto(any())).thenReturn(patientDto);
        when(patientMapper.toPatient(any())).thenReturn(patient);
    }

    @Test
    void getAllPatientsTest_shouldReturnListOfPatientDto() throws Exception {
        //Arrange
        when(patientService.getAllPatients()).thenReturn(List.of(patient));
        //Act
        mvc.perform(get("/patient/getAll")
                   .contentType(MediaType.APPLICATION_JSON)
                   .accept(MediaType.APPLICATION_JSON))
           .andDo(print())
           .andExpect(status().isOk())
           .andExpect(content().string(JsonTestMapper.asJsonString(List.of(patientDto))));

    }

    @Test
    void updatePatientTest_shouldReturnPatientDtoUpdated() throws Exception {
        //Arrange
        when(patientService.updatePatient(patient)).thenReturn(patient);
        //Act
        mvc.perform(put("/patient/update")
                   .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                   .content(JsonTestMapper.asJsonString(patientDto)))
           .andDo(print())
           .andExpect(status().isOk());
    }

    @Test
    void addPatientTest_shouldReturnPatientAdded() throws Exception {
        //Arrange
        when(patientService.savePatient(patient)).thenReturn(patient);
        //Act
        mvc.perform(post("/patient/add")
                   .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                   .content(JsonTestMapper.asJsonString(patient)))
           .andDo(print())
           .andExpect(status().isCreated());

    }

    @Test
    void removePatientTest_shouldReturnPatientAdded() throws Exception {
        //Arrange
        when(patientService.deletePatient(1)).thenReturn(patient);
        //Act
        mvc.perform(delete("/patient/remove")
                   .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                   .accept(MediaType.APPLICATION_JSON).param("id", "1"))
           .andDo(print())
           .andExpect(status().isOk());

    }



}
