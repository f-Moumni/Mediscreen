package com.mediscreen.patient.controller;

import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {this.patientService = patientService;}


    @GetMapping("/all")
    public ResponseEntity<List<Patient>> getAllPatient(){
        return new ResponseEntity<List<Patient>>(patientService.getAllPatients(), HttpStatus.OK);
    }
}
