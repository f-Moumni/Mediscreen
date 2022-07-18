package com.mediscreen.assessment.proxy;

import com.mediscreen.assessment.DTO.NoteDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@FeignClient(value = "notes", url = "${mediscreeen.noteUrl}")
public interface NoteProxy {

    @GetMapping("note")
    public List<NoteDto> getAllNotes(@RequestParam Integer patientId);
}
