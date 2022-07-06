package com.mediscreen.patient.service;

import com.mediscreen.patient.exception.AlreadyExistsException;
import com.mediscreen.patient.exception.RessourceNotFoundException;
import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {

        this.patientRepository = patientRepository;
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient updatePatient(Patient patient) throws RessourceNotFoundException {
         Patient patientToUpdate = patientRepository.findById(patient.getId()).orElseThrow(()->  new RessourceNotFoundException("user with id: " +patient.getId()+" not found"));
        return patientRepository.save(patient);
    }


}
