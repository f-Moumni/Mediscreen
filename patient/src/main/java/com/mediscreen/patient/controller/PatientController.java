package com.mediscreen.patient.controller;


import com.mediscreen.patient.exception.RessourceNotFoundException;
import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {this.patientService = patientService;}


    @GetMapping("/all")
    public ResponseEntity<List<Patient>> getAllPatient(){
        return new ResponseEntity<List<Patient>>(patientService.getAllPatients(), OK);
    }

    @PutMapping
    public ResponseEntity<Patient>update(@RequestBody @Valid Patient patient) throws RessourceNotFoundException {
        return new ResponseEntity<Patient>(patientService.updatePatient(patient), OK);
    }
}
