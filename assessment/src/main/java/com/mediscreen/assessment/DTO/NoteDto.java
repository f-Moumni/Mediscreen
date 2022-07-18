package com.mediscreen.assessment.DTO;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


public class NoteDto {


    private String id;

    private Integer patientId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date = LocalDate.now();

    private String note;

    public NoteDto() {

    }

    public NoteDto(Integer patientId, String note) {
        this.patientId = patientId;
        this.note      = note;
    }

    public NoteDto(Integer patientId, LocalDate date, String note) {

        this.patientId = patientId;
        this.date      = date;
        this.note      = note;
    }

    public NoteDto(String id, Integer patientId, LocalDate date, String note) {

        this.id        = id;
        this.patientId = patientId;
        this.date      = date;
        this.note      = note;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {

        this.id = id;
    }

    public Integer getPatientId() {

        return patientId;
    }

    public void setPatientId(Integer patientId) {

        this.patientId = patientId;
    }

    public LocalDate getDate() {

        return date;
    }

    public void setDate(LocalDate date) {

        this.date = date;
    }

    public String getNote() {

        return note;
    }

    public void setNote(String note) {

        this.note = note;
    }
}
