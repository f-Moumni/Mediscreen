package com.mediscreen.patient.service;

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

        Patient patientToUpdate = findById(patient.getId());
        return patientRepository.save(patient);
    }

    public Patient savePatient(Patient patient) {

        return patientRepository.save(patient);
    }

    public Patient deletePatient(long id) throws RessourceNotFoundException {

        Patient patientToDelete = findById(id);
        patientRepository.delete(patientToDelete);
        return patientToDelete;
    }

    public Patient findById(long id) throws RessourceNotFoundException {

        return patientRepository.findById(id)
                                .orElseThrow(() -> new RessourceNotFoundException("user with id: " + id + " not found"));
    }

    public Patient findByLatName(String lastName) throws RessourceNotFoundException {

        return patientRepository.findByLastName(lastName)
                                .stream()
                                .findAny()
                                .orElseThrow(() -> new RessourceNotFoundException("user with lastname : " + lastName + " not found"));
    }

}
