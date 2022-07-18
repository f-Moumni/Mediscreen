package com.mediscreen.assessment.DTO;

import com.mediscreen.assessment.enums.Gender;
import com.mediscreen.assessment.enums.RiskLevel;

import java.time.LocalDate;

public class ReportDto {

    /**
     * Patient's first name
     **/

    private String firstName;

    /**
     * Patient's last name
     **/

    private String lastName;

    private LocalDate birthdate;

    private Gender gender;

    private String address;

    private String phone;

    private RiskLevel riskLevel;

    public ReportDto() {

    }

    public ReportDto(String firstName, String lastName, LocalDate birthdate, Gender gender, String address, String phone, RiskLevel riskLevel) {

        this.firstName = firstName;
        this.lastName  = lastName;
        this.birthdate = birthdate;
        this.gender    = gender;
        this.address   = address;
        this.phone     = phone;
        this.riskLevel = riskLevel;
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

    public RiskLevel getRiskLevel() {

        return riskLevel;
    }

    public void setRiskLevel(RiskLevel riskLevel) {

        this.riskLevel = riskLevel;
    }
}
