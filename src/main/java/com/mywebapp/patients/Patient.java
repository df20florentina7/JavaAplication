package com.mywebapp.patients;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
public class Patient {
    @Id
    @SequenceGenerator(name = "patient_sequence",
            sequenceName = "patient_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "patient_sequence")
    private Integer id;
    private String name;
    private LocalDate dateOfBirth;
    private String diagnosis;
    @Transient
    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Patient() {
    }

    public Patient(Integer id, String name, LocalDate dateOfBirth, String diagnosis) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.diagnosis = diagnosis;
    }

    public Patient(String name, LocalDate dateOfBirth, String diagnosis) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.diagnosis = diagnosis;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Integer getAge() {
        return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + getDateOfBirth() +
                ", diagnosis='" + getDiagnosis() + '\'' +
                ", age=" + getAge() +
                '}';
    }
}
