package com.mediscreen.assessment.util;

import com.mediscreen.assessment.DTO.PatientDto;
import com.mediscreen.assessment.DTO.ReportDto;
import com.mediscreen.assessment.enums.Gender;
import com.mediscreen.assessment.enums.RiskLevel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ReportMapperTest {

    @Autowired
    ReportMapper reportMapper;


    @Test
    public void toReportDtoTest_shouldReturnReportDto() {
        //Arrange
        PatientDto patient   = new PatientDto(1, "john", "doe", now().minusYears(32), Gender.MASCULINE, "rue des nations", "08900099");
        ReportDto  reportDto = new ReportDto(1, "john doe", 32, Gender.MASCULINE, "rue des nations", "08900099", RiskLevel.BORDERLINE);
        //Act
        ReportDto result = reportMapper.toReportDto(patient, RiskLevel.BORDERLINE, 32);
        //Assert
        assertThat(result).isEqualToComparingFieldByField(reportDto);
    }
}
