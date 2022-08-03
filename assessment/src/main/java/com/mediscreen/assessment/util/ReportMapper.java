package com.mediscreen.assessment.util;

import com.mediscreen.assessment.DTO.PatientDto;
import com.mediscreen.assessment.DTO.ReportDto;
import com.mediscreen.assessment.enums.RiskLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class ReportMapper {

    private final Logger LOGGER = LoggerFactory.getLogger(ReportMapper.class);


    public ReportDto toReportDto(PatientDto patient, RiskLevel riskLevel, int age) {

        LOGGER.debug("mapping of report for patient {} {}", patient.getFirstName(), patient.getLastName());
        return new ReportDto((int) patient.getId(), patient.getFirstName() + " " + patient.getLastName(),
                age, patient.getGender(), patient.getAddress(), patient.getPhone(), riskLevel);

    }
}
