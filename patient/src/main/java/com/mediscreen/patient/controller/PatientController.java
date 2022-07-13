package com.mediscreen.patient.controller;


import com.mediscreen.patient.exception.RessourceNotFoundException;
import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {this.patientService = patientService;}


    @GetMapping("/all")
    public ResponseEntity<List<Patient>> getAllPatient() {

        return new ResponseEntity<>(patientService.getAllPatients(), OK);
    }
    @GetMapping
    public ResponseEntity<Patient> getPatient(@RequestParam Long id) throws RessourceNotFoundException {

        return new ResponseEntity<>(patientService.findById(id), OK);
    }

    @PutMapping
    public ResponseEntity<Patient> updatePatient(@RequestBody @Valid Patient patient) throws RessourceNotFoundException {

        return new ResponseEntity<>(patientService.updatePatient(patient), OK);
    }

    @PostMapping
    public ResponseEntity<Patient> addPatient(@RequestBody @Valid Patient patient)  {

        return new ResponseEntity<>(patientService.savePatient(patient), CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Patient> removePatient(@RequestParam @Valid long id) throws RessourceNotFoundException {

        return new ResponseEntity<>(patientService.deletePatient(id), OK);
    }
}
