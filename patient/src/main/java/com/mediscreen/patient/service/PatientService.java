package com.mediscreen.patient.service;

import com.mediscreen.patient.exception.RessourceNotFoundException;
import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * patient service class for curd operation of patient
 */
@Transactional
@Service
public class PatientService implements IPatientService {

    private final Logger            LOGGER = LoggerFactory.getLogger(PatientService.class);
    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {

        this.patientRepository = patientRepository;
    }

    @Override
    public List<Patient> getAllPatients() {
        LOGGER.info("getting all patients");
        return patientRepository.findAll();
    }

    @Override
    public Patient updatePatient(Patient patient) throws RessourceNotFoundException {
        LOGGER.info("updating patient: {} {}", patient.getFirstName(),patient.getLastName());
        Patient patientToUpdate = findById(patient.getId());
        return patientRepository.save(patient);
    }

    @Override
    public Patient savePatient(Patient patient) {
        LOGGER.info("saving patient: {} {}", patient.getFirstName(),patient.getLastName());
        return patientRepository.save(patient);
    }

    @Override
    public Patient deletePatient(long id) throws RessourceNotFoundException {
        LOGGER.info("updating patient with id: {} ", id);
        Patient patientToDelete = findById(id);
        patientRepository.delete(patientToDelete);
        return patientToDelete;
    }

    @Override
    public Patient findById(long id) throws RessourceNotFoundException {
        LOGGER.info("fetching patient with id: {} ", id);
        return patientRepository.findById(id)
                                .orElseThrow(() -> new RessourceNotFoundException("user with id: " + id + " not found"));
    }

    @Override
    public List<Patient> findByLatName(String lastName) {
        LOGGER.info("fetching patient with lastname: {} ", lastName);
        return patientRepository.findByLastName(lastName);

    }

}
