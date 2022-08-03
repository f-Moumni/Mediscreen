package com.mediscreen.assessment.proxy;

import com.mediscreen.assessment.DTO.PatientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Patient proxy interface
 */
@FeignClient(value = "patient", url = "${mediscreeen.patientUrl}")
public interface PatientProxy {

    /**
     * get patient by id
     * @param id : patient id
     * @return : patient
     */
    @GetMapping("patient")
    public PatientDto getPatientById(@RequestParam Long id);


    /**
     * get patient by family name
     * @param lastname : patient's family name
     * @return list of patients
     */
    @GetMapping("patient/lastname")
    public List<PatientDto> getPatientByFamilyName(@RequestParam String lastname);
}

