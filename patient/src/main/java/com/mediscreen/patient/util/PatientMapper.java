package com.mediscreen.patient.util;

import com.mediscreen.patient.constant.Gender;
import com.mediscreen.patient.dto.PatientDto;
import com.mediscreen.patient.model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * patient mapper
 */
@Component
public class PatientMapper {

    private final Logger LOGGER = LoggerFactory.getLogger(PatientMapper.class);

    /**
     * mappe patient to patientDTO
     *
     * @param p
     *         patient
     *
     * @return patientdto
     */
    public PatientDto toPatientDto(Patient p) {

        LOGGER.debug("mapping patient with id: {} to patientDto ", p.getId());
        return new PatientDto(p.getId(), p.getLastName(), p.getFirstName(), p.getBirthdate()
                                                                             .toString(), p.getGender() == Gender.FEMININE ? "F" : "M", p.getAddress(), p.getPhone());
    }

    /**
     * mappe patientDTO to patient
     *
     * @param p:patientDTO
     *
     * @return patient
     */
    public Patient toPatient(PatientDto p) {
        LOGGER.debug("mapping patientDto with id: {} to patient ", p.getId());
        return new Patient(p.getId(), p.getGiven(), p.getFamily(), LocalDate.parse(p.getDob()), p.getSex()
                                                                                                 .equalsIgnoreCase("F") ? Gender.FEMININE : Gender.MASCULINE, p.getAddress(), p.getPhone());
    }

}
