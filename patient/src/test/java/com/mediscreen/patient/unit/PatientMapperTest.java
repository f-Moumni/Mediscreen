package com.mediscreen.patient.unit;

import com.mediscreen.patient.constant.Gender;
import com.mediscreen.patient.dto.PatientDto;
import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.util.PatientMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
@ActiveProfiles("test")
public class PatientMapperTest {

    @Autowired
    PatientMapper patientMapper;

    private final Patient    patient    = new Patient(1, "john", "doe", LocalDate.of(2022, 7, 6)
            , Gender.MASCULINE, "33 rue des nations", "0890009");
    private final PatientDto patientDto = new PatientDto(1, "doe", "john",
            LocalDate.of(2022, 7, 6)
                     .toString(), "M", "33 rue des nations", "0890009");
    private final Patient    patient2    = new Patient(2, "Janne", "doe", LocalDate.of(2022, 7, 6)
            , Gender.FEMININE, "33 rue des nations", "0890009");
    private final PatientDto patientDto2 = new PatientDto(2, "doe", "Janne",
            LocalDate.of(2022, 7, 6)
                     .toString(), "F", "33 rue des nations", "0890009");
    @Test
    void toPatientDtoTest(){
        //Act
        PatientDto result =patientMapper.toPatientDto(patient);
        //Asset
        assertThat(result).isEqualToComparingFieldByField(patientDto);
    }
    @Test
    void toPatientTest(){
        //Act
        Patient result =patientMapper.toPatient(patientDto);
        //Asset
        assertThat(result).isEqualToComparingFieldByField(patient);
    }
    @Test
    void toPatientDtoTest_withFEMININEPatient(){
        //Act
        PatientDto result =patientMapper.toPatientDto(patient2);
        //Asset
        assertThat(result).isEqualToComparingFieldByField(patientDto2);
    }
    @Test
    void toPatientTest_withFEMININEPatient(){
        //Act
        Patient result =patientMapper.toPatient(patientDto2);
        //Asset
        assertThat(result).isEqualToComparingFieldByField(patient2);
    }
}
