package com.mediscreen.assessment.DTO;

import com.mediscreen.assessment.enums.Gender;

public class ReportDto {

    private int id;

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    /**
     * Patient's name
     **/

    private String name;

    /**
     * Patient's age
     */
    private int age;

    private Gender gender;

    private String address;

    private String phone;

    /**
     * diabetes assessment
     */
    private String riskLevel;

    public ReportDto() {

    }

    public ReportDto(int id, String name, int age, Gender gender, String address, String phone, String riskLevel) {

        this.id        = id;
        this.name      = name;
        this.age       = age;
        this.gender    = gender;
        this.address   = address;
        this.phone     = phone;
        this.riskLevel = riskLevel;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public int getAge() {

        return age;
    }

    public void setAge(int age) {

        this.age = age;
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

    public String getRiskLevel() {

        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {

        this.riskLevel = riskLevel;
    }
}
