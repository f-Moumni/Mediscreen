package com.mediscreen.assessment.service;

import com.mediscreen.assessment.DTO.NoteDto;
import com.mediscreen.assessment.DTO.PatientDto;
import com.mediscreen.assessment.enums.Gender;
import com.mediscreen.assessment.enums.RiskLevel;
import com.mediscreen.assessment.proxy.NoteProxy;
import com.mediscreen.assessment.proxy.PatientProxy;
import com.mediscreen.assessment.util.Calculator;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class AssessmentServiceTest {

    @InjectMocks
    private AssessmentService assessmentService;
    private NoteDto           note  = new NoteDto("62d12c25191bcc3f11d08547", 1, now(), "a few notes for a test with Hémoglobine A1C and Taille ");
    private NoteDto           note2 = new NoteDto("62d12c25191bcc3f11d08548", 1, now().minusMonths(2), "a few notes for a test with Rechute and vertige");
    @Mock
    private NoteProxy         noteProxy;
    @Mock
    private PatientProxy patientProxy;
    @Mock
    private Calculator   calculator;


    @ParameterizedTest
    @CsvSource({"true,8,FEMININE","true,8,MASCULINE","false,7,FEMININE","false,8,FEMININE","false,5,MASCULINE","false,8,MASCULINE"})
    public void riskAssess_shouldReturnRisk_EARLY_ONSET(boolean older, int count,Gender gender) {
        //Arrange
        PatientDto patient = new PatientDto(1, "john", "doe", now(), gender, "0890009", "rue des nations");
        when(noteProxy.getAllNotes(any())).thenReturn(List.of(note, note2));
        when(patientProxy.getPatient(any())).thenReturn(patient);
        when(calculator.isOlderThenThirty(any())).thenReturn(older);
        when(calculator.calculateTriggersNumber(any())).thenReturn(count);
        //Act
        RiskLevel level = assessmentService.riskAssess(patient.getId());
        //Assert
        assertThat(level).isEqualTo(RiskLevel.EARLY_ONSET);
    }
    @ParameterizedTest
    @CsvSource({"true,6,FEMININE","true,6,MASCULINE","false,4,FEMININE","false,5,FEMININE","false,3,MASCULINE","false,4,MASCULINE"})
    public void riskAssess_shouldReturnRiskIN_DANGER(boolean older, int count,Gender gender) {
        //Arrange
        PatientDto patient = new PatientDto(1, "john", "doe", now(), gender, "0890009", "rue des nations");
        when(noteProxy.getAllNotes(any())).thenReturn(List.of(note, note2));
        when(patientProxy.getPatient(any())).thenReturn(patient);
        when(calculator.isOlderThenThirty(any())).thenReturn(older);
        when(calculator.calculateTriggersNumber(any())).thenReturn(count);
        //Act
        RiskLevel level = assessmentService.riskAssess(patient.getId());
        //Assert
        assertThat(level).isEqualTo(RiskLevel.IN_DANGER);
    }

    @ParameterizedTest
    @CsvSource({"true,2,MASCULINE","true,5,MASCULINE","true,2,FEMININE","true,5,FEMININE"})
    public void riskAssess_shouldReturnRisk_BORDERLINE(boolean older, int count,Gender gender) {
        //Arrange
        PatientDto patient = new PatientDto(1, "john", "doe", now(), gender, "0890009", "rue des nations");
        when(noteProxy.getAllNotes(any())).thenReturn(List.of(note, note2));
        when(patientProxy.getPatient(any())).thenReturn(patient);
        when(calculator.isOlderThenThirty(any())).thenReturn(older);
        when(calculator.calculateTriggersNumber(any())).thenReturn(count);
        //Act
        RiskLevel level = assessmentService.riskAssess(patient.getId());
        //Assert
        assertThat(level).isEqualTo(RiskLevel.BORDERLINE);
    }

    @ParameterizedTest
    @CsvSource({"true,1,FEMININE","true,0,FEMININE","true,0,MASCULINE","false,3,FEMININE","false,2,FEMININE","false,2,MASCULINE","false,1,MASCULINE"})
    public void riskAssess_shouldReturnRisk_NONE(boolean older, int count,Gender gender) {
        //Arrange
        PatientDto patient = new PatientDto(1, "john", "doe", now(), gender, "0890009", "rue des nations");
        when(noteProxy.getAllNotes(any())).thenReturn(List.of(note, note2));
        when(patientProxy.getPatient(any())).thenReturn(patient);
        when(calculator.isOlderThenThirty(any())).thenReturn(older);
        when(calculator.calculateTriggersNumber(any())).thenReturn(count);
        //Act
        RiskLevel level = assessmentService.riskAssess(patient.getId());
        //Assert
        assertThat(level).isEqualTo(RiskLevel.NONE);
    }


}
