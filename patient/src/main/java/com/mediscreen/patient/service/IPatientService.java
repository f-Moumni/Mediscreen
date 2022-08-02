package com.mediscreen.patient.service;

import com.mediscreen.patient.exception.RessourceNotFoundException;
import com.mediscreen.patient.model.Patient;

import java.util.List;

/**
 * IPatientService interface for curd operation of patient
 */
public interface IPatientService {

    List<Patient> getAllPatients();

    Patient updatePatient(Patient patient) throws RessourceNotFoundException;

    Patient savePatient(Patient patient);

    Patient deletePatient(long id) throws RessourceNotFoundException;

    Patient findById(long id) throws RessourceNotFoundException;

    List<Patient> findByLatName(String lastName);
}
