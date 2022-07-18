package com.mediscreen.assessment.proxy;

import com.mediscreen.assessment.DTO.PatientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(value = "patient", url = "${mediscreeen.patientUrl}")
public interface PatientProxy {

    @GetMapping("patient")
    public PatientDto getPatient(@RequestParam Long id);
}
