package com.mediscreen.assessment.controller;

import com.mediscreen.assessment.DTO.ReportDto;
import com.mediscreen.assessment.service.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RequestMapping("assess")
@RestController
public class AssessmentController {

    private final AssessmentService assessmentService;

    @Autowired
    public AssessmentController(AssessmentService assessmentService) {this.assessmentService = assessmentService;}

    @GetMapping()
    public ResponseEntity<ReportDto> getReport(@RequestParam long patientId) {

        return new ResponseEntity<>(assessmentService.generateReportById(patientId), HttpStatus.OK);
    }

    @PostMapping (value = "id", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCurlReportById( int patId) {

        ReportDto reportDto = assessmentService.generateReportById(patId);
        String    report    = "Patient : " + reportDto.getPatient() + " (age " + reportDto.getAge() + ") diabetes assessment is: " + reportDto.getRiskLevel();
        return new ResponseEntity<>(report, HttpStatus.OK);
    }
    @PostMapping (value = "familyName", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCurlReportByFamilyName( String familyName) {

        ReportDto reportDto = assessmentService.generateReportByFamilyName(familyName);
        String    report    = "Patient : " + reportDto.getPatient() + " (age " + reportDto.getAge() + ") diabetes assessment is: " + reportDto.getRiskLevel();
        return new ResponseEntity<>(report, HttpStatus.OK);
    }
}
