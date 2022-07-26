package com.mediscreen.note.dto;

import java.time.LocalDate;


public class PatientDto {


    private long id;

    /**
     * Patient's first name
     **/

    private String firstName;

    /**
     * Patient's last name
     **/

    private String lastName;


    private LocalDate birthdate;

    private String gender;

    private String address;

    private String phone;


    public PatientDto() {

    }

    public PatientDto(long id, String firstName, String lastName, LocalDate birthdate, String gender, String address, String phone) {

        this.id        = id;
        this.firstName = firstName;
        this.lastName  = lastName;
        this.birthdate = birthdate;
        this.gender    = gender;
        this.address   = address;
        this.phone     = phone;
    }


    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public String getLastName() {

        return lastName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }

    public LocalDate getBirthdate() {

        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {

        this.birthdate = birthdate;
    }

    public String getGender() {

        return gender;
    }

    public void setGender(String gender) {

        this.gender = gender;
    }

    public String getAddress() {

        return address;
    }

    public void setAddress(String address) {

        this.address = address;
    }

    public String getPhone() {

        return phone;
    }

    public void setPhone(String phone) {

        this.phone = phone;
    }
}
