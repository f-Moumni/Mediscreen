package com.mediscreen.patient.integration;

import com.mediscreen.patient.constant.Gender;
import com.mediscreen.patient.dto.PatientDto;
import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.util.JsonTestMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest
@Sql(scripts = "classpath:data.sql",
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class PatientCurlControllerIT {

    private final Patient    patient    = new Patient(1, "john", "doe", LocalDate.of(2022, 7, 6), Gender.MASCULINE, "33 rue des nations", "0890009");
    private final PatientDto patientDto = new PatientDto(1, "doe", "john", LocalDate.of(2022, 7, 6)
                                                                                    .toString(), "M", "33 rue des nations", "0890009");
    @Autowired
    private MockMvc mvc;

    @Test
    void getAllPatientsTest_shouldReturnListOfPatients() throws Exception {

        //Act
        mvc.perform(get("/patient/getAll")
                   .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                   .accept(MediaType.APPLICATION_JSON))
           .andDo(print())
           .andExpect(status().isOk())
           .andExpect(content().string(JsonTestMapper.asJsonString(List.of(patientDto))));
    }

    @Test
    void updatePatientTest_shouldReturnPatientUpdated() throws Exception {

        //Act
        mvc.perform(put("/patient/update")
                   .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                   .param("id", "1")
                   .param("family", "doe")
                   .param("given", "john")
                   .param("dob", LocalDate.of(2022, 7, 6).toString())
                   .param("sex", "M")
                   .param("address", "33 rue des nations")
                   .param("phone", "0890009"))
           .andDo(print())
           .andExpect(status().isOk());
    }

    @Test
    void addPatientTest_shouldReturnPatientAdded() throws Exception {
        //Act
        mvc.perform(post("/patient/add")
                   .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                   .param("id", "1")
                   .param("family", "doe")
                   .param("given", "john")
                   .param("dob", LocalDate.of(2022, 7, 6).toString())
                   .param("sex", "M")
                   .param("address", "33 rue des nations")
                   .param("phone", "0890009"))
           .andDo(print())
           .andExpect(status().isCreated());

    }

    @Test
    void removePatientTest_shouldReturnPatientAdded() throws Exception {

        //Act
        mvc.perform(delete("/patient/remove")
                   .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                   .accept(MediaType.APPLICATION_JSON).param("id", "1"))
           .andDo(print())
           .andExpect(status().isOk());

    }


}
