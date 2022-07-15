package com.mediscreen.note.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Document(collection = "notes")
public class Note {

    @Id
    private String id;

    @Field(value = "patient_id")
    private Integer patientId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private String note;

    public Note() {

    }

    public Note(Integer patientId, LocalDate date, String note) {

        this.patientId = patientId;
        this.date      = date;
        this.note      = note;
    }

    public Note(String id, Integer patientId, LocalDate date, String note) {

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
