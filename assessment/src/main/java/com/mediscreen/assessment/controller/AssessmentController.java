package com.mediscreen.assessment.controller;

import com.mediscreen.assessment.DTO.ReportDto;
import com.mediscreen.assessment.service.AssessmentService;
import com.mediscreen.assessment.service.IAssessmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * assessment api rest controller
 */
@CrossOrigin(origins = "*")
@RequestMapping("assess")
@RestController
public class AssessmentController {
    private final Logger             LOGGER = LoggerFactory.getLogger(AssessmentController.class);
    private final IAssessmentService assessmentService;

    @Autowired
    public AssessmentController(IAssessmentService assessmentService) {this.assessmentService = assessmentService;}

    /**
     * get report for given patient id
     * @param patientId: patient id
     * @return report in json form
     */
    @GetMapping()
    public ResponseEntity<ReportDto> getReport(@RequestParam long patientId) {
        LOGGER.info(" get report request for patient id {} ",patientId);
        return new ResponseEntity<>(assessmentService.generateReportById(patientId), HttpStatus.OK);
    }

    /**
     * get a report for a given patient id in curl
     * @param patId : patient id
     * @return report in string format
     */
    @PostMapping(value = "id", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCurlReportById(int patId) {
        LOGGER.info(" get report request for patient id {} ",patId);
        ReportDto reportDto = assessmentService.generateReportById(patId);
        String report = "Patient : " + reportDto.getName() + " (age " + reportDto.getAge() + ") diabetes assessment is: " + reportDto.getRiskLevel()
                                                                                                                                     .toString();
        return new ResponseEntity<>(report, HttpStatus.OK);
    }

    /**
     * get a reports for patients with given family name in curl
     * @param familyName : patients family name
     * @return list of report in string format
     */
    @PostMapping(value = "familyName", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getCurlReportByFamilyName(String familyName) {
        LOGGER.info(" get report request for patients with family name {} ",familyName);
        List<String> reports = assessmentService.generateReportByFamilyName(familyName).stream().map(p -> "Patient : " + p.getName() + " (age " + p.getAge() + ") diabetes assessment is: " + p.getRiskLevel()
                                                                                                                                                                                           .toString()).collect(Collectors.toList());
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }
}
