package com.mediscreen.assessment.util;

import com.mediscreen.assessment.DTO.PatientDto;
import com.mediscreen.assessment.DTO.ReportDto;
import com.mediscreen.assessment.enums.RiskLevel;
import org.springframework.stereotype.Component;

@Component
public class ReportMapper {

    public ReportDto toReportDto(PatientDto patient, RiskLevel riskLevel, int age) {

        return new ReportDto((int) patient.getId(), patient.getFirstName() + " " + patient.getLastName(),
                age, patient.getGender(), patient.getAddress(), patient.getPhone(), riskLevel);

    }
}
