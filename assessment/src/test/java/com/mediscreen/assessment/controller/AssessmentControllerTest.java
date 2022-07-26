package com.mediscreen.assessment.controller;

import com.mediscreen.assessment.DTO.ReportDto;
import com.mediscreen.assessment.enums.Gender;
import com.mediscreen.assessment.enums.RiskLevel;
import com.mediscreen.assessment.service.AssessmentService;
import com.mediscreen.assessment.util.JsonTestMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@WebMvcTest(AssessmentController.class)
public class AssessmentControllerTest {

    @Autowired
    MockMvc mvc;
    @MockBean
    private AssessmentService assessmentService;
    private ReportDto         reportDto = new ReportDto(1, "john doe", 32, Gender.MASCULINE, "rue des nations", "08900099", RiskLevel.BORDERLINE);
    @Test
    public void getReportTest_shouldStatus400() throws Exception {
        //Arrange
        when(assessmentService.generateReportById(1)).thenReturn(reportDto);
        //Act
        mvc.perform(get("/assess")
                   .contentType(MediaType.APPLICATION_JSON)
                   .accept(MediaType.APPLICATION_JSON)
                   .param("patientId", "id"))
           .andDo(print())
           .andExpect(status().isBadRequest());
    }
    @Test
    public void getReportTest_shouldReturnReport_ForGivenPatient() throws Exception {
        //Arrange
        when(assessmentService.generateReportById(1)).thenReturn(reportDto);
        //Act
        mvc.perform(get("/assess")
                   .contentType(MediaType.APPLICATION_JSON)
                   .accept(MediaType.APPLICATION_JSON)
                   .param("patientId", "1"))
           .andDo(print())
           .andExpect(status().isOk())
           .andExpect(content().string(JsonTestMapper.asJsonString(reportDto)));
    }
    @Test
    public void getCurlReportById_shouldReturnReport_ForGivenPatientId() throws Exception {
        //Arrange
        when(assessmentService.generateReportById(1)).thenReturn(reportDto);
        String report = "Patient : " + reportDto.getName() + " (age " + reportDto.getAge() + ") diabetes assessment is: " + reportDto.getRiskLevel()
                                                                                                                                     .toString();
        //Act
        mvc.perform(post("/assess/id")
                   .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                   .param("patId", "1"))
           .andDo(print())
           .andExpect(status().isOk())
           .andExpect(content().string(report));
    }

    @Test
    public void getCurlReportById_shouldReturnReport_ForGivenPatientFamilyName() throws Exception {
        //Arrange
        when(assessmentService.generateReportByFamilyName("doe")).thenReturn(List.of(reportDto));
        String report = "Patient : " + reportDto.getName() + " (age " + reportDto.getAge() + ") diabetes assessment is: " + reportDto.getRiskLevel()
                                                                                                                                     .toString();
        //Act
        mvc.perform(post("/assess/familyName")
                   .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                   .param("familyName", "doe"))
           .andDo(print())
           .andExpect(status().isOk())
           .andExpect(content().string(JsonTestMapper.asJsonString(List.of(report))));
    }

}
