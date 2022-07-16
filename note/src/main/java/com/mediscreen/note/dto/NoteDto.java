package com.mediscreen.note.dto;

public class NoteDto {

    private String      PatId;
    private String    e;

    public NoteDto() {

    }

    public NoteDto(String patId, String e) {
        PatId  = patId;
        this.e = e;
    }

    public String getPatId() {

        return PatId;
    }

    public void setPatId(String patId) {

        PatId = patId;
    }

    public String getE() {

        return e;
    }

    public void setE(String e) {

        this.e = e;
    }
}
