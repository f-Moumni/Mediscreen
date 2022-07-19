package com.mediscreen.assessment.util;

import com.mediscreen.assessment.DTO.PatientDto;
import com.mediscreen.assessment.DTO.ReportDto;
import com.mediscreen.assessment.enums.RiskLevel;
import org.springframework.stereotype.Component;

@Component
public class ReportMapper {


    public ReportDto toReportDto(PatientDto patient, RiskLevel riskLevel, int age) {
        ReportDto report = new ReportDto();
        report.setPatient(patient.getLastName() + " " + patient.getFirstName());
        report.setAge(age);
        report.setRiskLevel(riskLevel.toString());
        return report;
    }
}
