package com.mediscreen.assessment.service;

import com.mediscreen.assessment.DTO.PatientDto;
import com.mediscreen.assessment.DTO.ReportDto;
import com.mediscreen.assessment.enums.RiskLevel;

import java.util.List;

/**
 * AssessmentService Interface  for
 */
public interface IAssessmentService {

    /**
     * evaluate the level of risk of dibaetus for a given patient
     *
     * @param patient
     *         : patient
     *
     * @return the level of risk
     */
    RiskLevel riskAssess(PatientDto patient);

    /**
     * generate report with demographic information of the patient in addition to diabetes risk
     *
     * @param patientId
     *         patient id
     *
     * @return report
     */
    ReportDto generateReportById(long patientId);

    /**
     * generate report with demographic information of the patient in addition to diabetes risk
     *
     * @param familyName
     *         : patient's family name
     *
     * @return report
     */
    List<ReportDto> generateReportByFamilyName(String familyName);
}
