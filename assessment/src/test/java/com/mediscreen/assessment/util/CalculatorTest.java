package com.mediscreen.assessment.util;

import com.mediscreen.assessment.DTO.PatientDto;
import com.mediscreen.assessment.enums.Gender;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;
@ActiveProfiles("test")
@SpringBootTest
public class CalculatorTest {

    @Autowired
    Calculator calculator;

    @Test
    public void calculateTest_shouldReturnAge_forGivenPatient() {
        //Arrange
        PatientDto patient = new PatientDto(1, "john", "doe", now().minusYears(20), Gender.MASCULINE, "0890009", "rue des nations");
        //Act
        int age = calculator.calculateAge(patient.getBirthdate());
        //Assert
        assertThat(age).isEqualTo(20);

    }

    @ParameterizedTest
    @ValueSource(ints = {20, 29})
    public void isOlderThenThirtyTest_shouldReturnFalse_forYoungPatient(int years) {
        //Arrange
        PatientDto patient = new PatientDto(1, "john", "doe", now().minusYears(years), Gender.MASCULINE, "0890009", "rue des nations");
        //Act
        boolean result = calculator.isOlderThenThirty(patient.getBirthdate());
        //Assert
        assertThat(result).isFalse();

    }


    @ParameterizedTest
    @ValueSource(ints = {42, 30})
    void isOlderThenThirtyTest_shouldReturnTrue(int years) {
        //Arrange
        PatientDto patient = new PatientDto(1, "john", "doe", now().minusYears(years), Gender.MASCULINE, "0890009", "rue des nations");
        //Act
        boolean result = calculator.isOlderThenThirty(patient.getBirthdate());
        //Assert
        assertThat(result).isTrue();

    }

    @Test
    @DisplayName("calculate the number of triggers terminology present notes ")
    public void calculateTriggersNumberTest() {
        //Arrange
        List<String> notes = List.of("a few notes for a test with Hémoglobine A1C and Taille ", "a few notes for a test with Rechute and vertige");
        //Act
        int count = calculator.calculateTriggersNumber(notes);
        //Assert
        assertThat(count).isEqualTo(4);

    }
}
