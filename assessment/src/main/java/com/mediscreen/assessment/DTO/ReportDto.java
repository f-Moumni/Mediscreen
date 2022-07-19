package com.mediscreen.assessment.DTO;

public class ReportDto {

    /**
     * Patient's name
     **/

    private String patient;


    /**
     *Patient's age
     */
    private int age;

    /**
     * diabetes assessment
     */
    private String riskLevel;

    public ReportDto() {

    }

    public ReportDto(String patient, int age, String riskLevel) {

        this.patient   = patient;
        this.age       = age;
        this.riskLevel = riskLevel;
    }

    public String getPatient() {

        return patient;
    }

    public int getAge() {

        return age;
    }

    public String getRiskLevel() {

        return riskLevel;
    }

    public void setPatient(String patient) {

        this.patient = patient;
    }

    public void setAge(int age) {

        this.age = age;
    }

    public void setRiskLevel(String riskLevel) {

        this.riskLevel = riskLevel;
    }
}
