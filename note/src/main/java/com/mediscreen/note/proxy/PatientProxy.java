package com.mediscreen.note.proxy;

import com.mediscreen.note.DTO.PatientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

/**
 * Patient proxy interface
 */
@Service
@FeignClient(value = "patient", url = "${mediscreeen.patientUrl}")
public interface PatientProxy {

    @GetMapping("patient")
    public Optional<PatientDto> getPatientById(@RequestParam long id);
}