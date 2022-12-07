package com.mywebapp.patients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@RestController
@RequestMapping(path = "api/v1/patients")
public class PatientController {
    private final PatientService PATIENT_SERVICE;

    @Autowired
    public PatientController(PatientService patientService) {
        this.PATIENT_SERVICE = patientService;
    }

    @GetMapping
    public String showPatientsList() {
        return String.valueOf(PATIENT_SERVICE.listAll());
    }

    @PostMapping
    public void addNewPatient(@RequestBody Patient patient) {
        PATIENT_SERVICE.addNewPatient(patient);
    }

    @DeleteMapping(path = {"{id}"})
    public void deletePatient(@PathVariable("id") Integer id) {
        PATIENT_SERVICE.deletePatient(id);
    }

    @PutMapping(path = "{id}")
    public void updatePatient(
            @PathVariable("id") Integer id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) LocalDate dateOfBirth,
            @RequestParam(required = false) String diagnosis) {
        PATIENT_SERVICE.updatePatient(id, name, dateOfBirth, diagnosis);
    }


}
