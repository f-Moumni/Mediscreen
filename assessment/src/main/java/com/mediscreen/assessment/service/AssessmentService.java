package com.mediscreen.assessment.service;

import com.mediscreen.assessment.DTO.NoteDto;
import com.mediscreen.assessment.DTO.PatientDto;
import com.mediscreen.assessment.DTO.ReportDto;
import com.mediscreen.assessment.enums.Gender;
import com.mediscreen.assessment.enums.RiskLevel;
import com.mediscreen.assessment.proxy.NoteProxy;
import com.mediscreen.assessment.proxy.PatientProxy;
import com.mediscreen.assessment.util.Calculator;
import com.mediscreen.assessment.util.ReportMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * AssessmentService class assesses the level of diabetes risk and generates a report
 */
@Service
public class AssessmentService implements IAssessmentService {

    private final Logger       LOGGER = LoggerFactory.getLogger(AssessmentService.class);
    private final NoteProxy    noteProxy;
    private final PatientProxy patientProxy;
    private final Calculator   calculator;
    private final ReportMapper reportMapper;

    @Autowired
    public AssessmentService(NoteProxy noteProxy, PatientProxy patientProxy, Calculator ageCalculator, ReportMapper reportMapper) {

        this.noteProxy    = noteProxy;
        this.patientProxy = patientProxy;
        this.calculator   = ageCalculator;
        this.reportMapper = reportMapper;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public RiskLevel riskAssess(PatientDto patient) {

        LOGGER.info("assessment of the level of risk for patient {} {}", patient.getFirstName(), patient.getLastName());
        boolean isOlderThenThirty        = calculator.isOlderThenThirty(patient.getBirthdate());
        Gender  gender                   = patient.getGender();
        int     terminologyTriggersCount = calculateTerminologyTriggers(Math.toIntExact(patient.getId()));

        if (terminologyTriggersCount >= 8) {
            return RiskLevel.EARLY_ONSET;

        } else if (isOlderThenThirty) {
            if (terminologyTriggersCount >= 6) {
                return RiskLevel.IN_DANGER;
            } else {
                return (terminologyTriggersCount >= 2) ? RiskLevel.BORDERLINE : RiskLevel.NONE;
            }
        } else {
            if (gender.equals(Gender.FEMININE)) {
                if (terminologyTriggersCount >= 7) {
                    return RiskLevel.EARLY_ONSET;
                } else {return (terminologyTriggersCount >= 4) ? RiskLevel.IN_DANGER : RiskLevel.NONE;}
            } else {
                if (terminologyTriggersCount >= 5) {
                    return RiskLevel.EARLY_ONSET;
                } else {
                    return (terminologyTriggersCount >= 3) ? RiskLevel.IN_DANGER : RiskLevel.NONE;
                }
            }
        }
    }

    /**
     * Calculate the terminology Triggers present in the patient notes
     * @param patientId patient id
     * @return number of terminology Triggers
     */
    private int calculateTerminologyTriggers(int patientId) {
        LOGGER.info("calculate Terminology Triggers for patient id {}", patientId);
        List<String> notes = noteProxy.getAllNotes(patientId)
                                      .stream()
                                      .map(NoteDto::getNote)
                                      .collect(Collectors.toList());
        return calculator.calculateTriggersNumber(notes);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public ReportDto generateReportById(long patientId) {
        LOGGER.info("generation of report for patient id: {}", patientId);
        PatientDto patient   = patientProxy.getPatientById(patientId);
        RiskLevel  riskLevel = riskAssess(patient);
        int        age       = calculator.calculateAge(patient.getBirthdate());
        return reportMapper.toReportDto(patient, riskLevel, age);
    }


     /**
     * {@inheritDoc}
     */
    @Override
    public List<ReportDto> generateReportByFamilyName(String familyName) {
        LOGGER.info("generation of report for patients with familyName: {}", familyName);
        return patientProxy.getPatientByFamilyName(familyName).stream().map(p -> {
            RiskLevel riskLevel = riskAssess(p);
            int       age       = calculator.calculateAge(p.getBirthdate());
            return reportMapper.toReportDto(p, riskLevel, age);
        }).collect(Collectors.toList());

    }
}

