package com.mediscreen.patient.controller;

import com.mediscreen.patient.dto.Response;

import com.mediscreen.patient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.HttpStatus.OK;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

import static java.time.LocalDateTime.now;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {this.patientService = patientService;}


    @GetMapping("/all")
    public ResponseEntity<Response> getAllPatient(){
        return  ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("patients",patientService.getAllPatients()))
                        .message("all patients got successfully")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
}
