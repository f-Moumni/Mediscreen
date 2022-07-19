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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssessmentService {


    private final NoteProxy noteProxy;

    private final PatientProxy patientProxy;

    private final Calculator calculator;
    private final ReportMapper reportMapper;

    @Autowired
    public AssessmentService(NoteProxy noteProxy, PatientProxy patientProxy, Calculator ageCalculator, ReportMapper reportMapper) {

        this.noteProxy    = noteProxy;
        this.patientProxy = patientProxy;
        this.calculator   = ageCalculator;
        this.reportMapper = reportMapper;
    }


    public RiskLevel riskAssess(Long patientId) {


        PatientDto patient                  = patientProxy.getPatient(patientId);
        boolean    isOlderThenThirty        = calculator.isOlderThenThirty(patient.getBirthdate());
        Gender     gender                   = patient.getGender();
        int        terminologyTriggersCount = calculateTerminologyTriggers(Math.toIntExact(patientId));

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

    private int calculateTerminologyTriggers(int patientId) {

        List<String> notes = noteProxy.getAllNotes(patientId)
                                      .stream()
                                      .map(NoteDto::getNote)
                                      .collect(Collectors.toList());
        return calculator.calculateTriggersNumber(notes);
    }

    public ReportDto generateReport(long patientId) {
        PatientDto patient   = patientProxy.getPatient(patientId);
        RiskLevel  riskLevel = riskAssess(patientId);
        int age=calculator.calculateAge(patient.getBirthdate());
        return reportMapper.toReportDto(patient,riskLevel,age);
    }
}

