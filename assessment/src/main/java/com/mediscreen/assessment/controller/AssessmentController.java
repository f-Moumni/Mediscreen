package com.mediscreen.assessment.controller;

import com.mediscreen.assessment.DTO.ReportDto;
import com.mediscreen.assessment.service.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @PostMapping(value = "id", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCurlReportById(int patId) {

        ReportDto reportDto = assessmentService.generateReportById(patId);
        String report = "Patient : " + reportDto.getName() + " (age " + reportDto.getAge() + ") diabetes assessment is: " + reportDto.getRiskLevel()
                                                                                                                                     .toString();
        return new ResponseEntity<>(report, HttpStatus.OK);
    }

    @PostMapping(value = "familyName", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getCurlReportByFamilyName(String familyName) {

        List<String> reports = assessmentService.generateReportByFamilyName(familyName).stream().map(p -> {
            return "Patient : " + p.getName() + " (age " + p.getAge() + ") diabetes assessment is: " + p.getRiskLevel()
                                                                                                        .toString();
        }).collect(Collectors.toList());
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }
}
