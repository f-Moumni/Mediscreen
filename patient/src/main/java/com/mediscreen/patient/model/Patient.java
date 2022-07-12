package com.mediscreen.patient.model;

import com.mediscreen.patient.constant.Gender;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Patient's first name
     **/
    @Column(name = "first_name", nullable = false)
    @NotBlank(message = "the first name cannot be empty or null")
    private String firstName;

    /**
     * Patient's last name
     **/
    @Column(name = "last_name", nullable = false)
    @NotBlank(message = "the last name cannot be empty or null")
    private String    lastName;

    @Column(nullable = false)
    private LocalDate birthdate;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender    gender;

    private String address;

    private String phone;


    public Patient() {

    }

    public Patient(long id, String firstName, String lastName, LocalDate birthdate, Gender gender, String address, String phone) {

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

    public Gender getGender() {

        return gender;
    }

    public void setGender(Gender gender) {

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
