package com.mediscreen.patient.controller;


import com.mediscreen.patient.exception.RessourceNotFoundException;
import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.service.IPatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

/**
 * PatientController for de crud endpoints operations
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/patient")
public class PatientController {

    private final Logger          LOGGER = LoggerFactory.getLogger(PatientController.class);
    private final IPatientService patientService;

    @Autowired
    public PatientController(IPatientService patientService) {this.patientService = patientService;}


    @GetMapping("/all")
    public ResponseEntity<List<Patient>> getAllPatient() {

        LOGGER.info("get all patient request");
        return new ResponseEntity<>(patientService.getAllPatients(), OK);
    }

    @GetMapping
    public ResponseEntity<Patient> getPatientById(@RequestParam Long id) throws RessourceNotFoundException {

        LOGGER.info("get patient by id :{} request", id);
        return new ResponseEntity<>(patientService.findById(id), OK);
    }

    @GetMapping("lastname")
    public ResponseEntity<List<Patient>> getPatientByLastName(@RequestParam String lastname) {

        LOGGER.info("get patient by lastname :{} request", lastname);
        return new ResponseEntity<>(patientService.findByLatName(lastname), OK);
    }

    @PutMapping
    public ResponseEntity<Patient> updatePatient(@RequestBody @Valid Patient patient) throws RessourceNotFoundException {

        LOGGER.info("update patient id :{} request", patient.getId());
        return new ResponseEntity<>(patientService.updatePatient(patient), OK);
    }


    @PostMapping
    public ResponseEntity<Patient> addPatient(@RequestBody @Valid Patient patient) {

        LOGGER.info("save patient :{} {} request", patient.getFirstName(), patient.getLastName());
        return new ResponseEntity<>(patientService.savePatient(patient), CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Patient> removePatient(@RequestParam @Valid long id) throws RessourceNotFoundException {

        LOGGER.info("remove patient with id:{}  request", id);
        return new ResponseEntity<>(patientService.deletePatient(id), OK);
    }
}
