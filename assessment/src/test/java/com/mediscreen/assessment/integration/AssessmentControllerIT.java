package com.mediscreen.assessment.integration;

import com.mediscreen.assessment.util.JsonTestMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles(" test ")
@AutoConfigureMockMvc
@SpringBootTest
public class AssessmentControllerIT {

    @Autowired
    MockMvc mvc;


    @Test
    public void getReportTest_shouldReturnReport_ForGivenPatient() throws Exception {
        //Act
        mvc.perform(get("/assess")
                   .contentType(MediaType.APPLICATION_JSON)
                   .accept(MediaType.APPLICATION_JSON)
                   .param("patientId", "4"))
           .andDo(print())
           .andExpect(status().isOk());
    }
    @Test
    public void getReportTest_shouldStatus404() throws Exception {
        //Act
        mvc.perform(get("/assess")
                   .contentType(MediaType.APPLICATION_JSON)
                   .accept(MediaType.APPLICATION_JSON)
                   .param("patientId", "1000000"))
           .andDo(print())
           .andExpect(status().isNotFound());
    }
    @Test
    public void getReportTest_shouldStatus400() throws Exception {
        //Act
        mvc.perform(get("/assess")
                   .contentType(MediaType.APPLICATION_JSON)
                   .accept(MediaType.APPLICATION_JSON)
                   .param("patientId", "id"))
           .andDo(print())
           .andExpect(status().isBadRequest());
    }
    @Test
    public void getCurlReportById_shouldReturnReport_ForGivenPatientId() throws Exception {
           //Act
        mvc.perform(post("/assess/id")
                   .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                   .param("patId", "4"))
           .andDo(print())
           .andExpect(status().isOk());

    }

    @Test
    public void getCurlReportByFamilyName_shouldReturnReport_ForGivenPatientFamilyName() throws Exception {

        //Act
        mvc.perform(post("/assess/familyName")
                   .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                   .param("familyName", "Pippa"))
           .andDo(print())
           .andExpect(status().isOk());
    }

}
