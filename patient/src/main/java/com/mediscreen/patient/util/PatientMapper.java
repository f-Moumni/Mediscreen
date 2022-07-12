package com.mediscreen.patient.util;

import com.mediscreen.patient.constant.Gender;
import com.mediscreen.patient.dto.PatientDto;
import com.mediscreen.patient.model.Patient;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PatientMapper {

    public PatientDto ToPatientDto(Patient p){
        return new PatientDto(p.getId(),p.getFirstName(),p.getLastName(),p.getBirthdate().toString(), p.getGender()==Gender.FEMININE? "F":"M",p.getAddress(),p.getPhone());
    }
    public Patient ToPatient(PatientDto p){
        return new Patient(p.getId(),p.getGiven(),p.getFamily(),LocalDate.parse(p.getDob()), p.getSex().equalsIgnoreCase("F")?Gender.FEMININE:Gender.MASCULINE,p.getAddress(),p.getPhone());
    }

}