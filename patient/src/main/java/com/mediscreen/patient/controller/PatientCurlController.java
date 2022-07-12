package com.mediscreen.patient.controller;

import com.mediscreen.patient.dto.PatientDto;
import com.mediscreen.patient.exception.RessourceNotFoundException;
import com.mediscreen.patient.service.PatientService;
import com.mediscreen.patient.util.PatientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/patient")
public class PatientCurlController {

    private final PatientService patientService;
    private final PatientMapper  patientMapper;

    @Autowired
    public PatientCurlController(PatientService patientService, PatientMapper patientMapper) {

        this.patientService = patientService;
        this.patientMapper  = patientMapper;
    }


    @GetMapping("getAll")
    public ResponseEntity<List<PatientDto>> getAllPatient() {

        List<PatientDto> patientDtoList = patientService.getAllPatients()
                                                        .stream()
                                                        .map(patientMapper::ToPatientDto)
                                                        .collect(Collectors.toList());
        return new ResponseEntity<>(patientDtoList, OK);
    }

    @PutMapping(value = "update", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PatientDto> updatePatient(@Valid PatientDto patientDto) throws RessourceNotFoundException {

        return new ResponseEntity<>(patientMapper.ToPatientDto(patientService.updatePatient(patientMapper.ToPatient(patientDto))), OK);
    }

    @PostMapping(value = "add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PatientDto> addPatient(@Valid PatientDto patientDto) {

        return new ResponseEntity<>(patientMapper.ToPatientDto(patientService.savePatient(patientMapper.ToPatient(patientDto))), CREATED);
    }

    @DeleteMapping("remove")
    public ResponseEntity<PatientDto> removePatient(@Valid long id) throws RessourceNotFoundException {

        return new ResponseEntity<>(patientMapper.ToPatientDto(patientService.deletePatient(id)), OK);
    }
}
