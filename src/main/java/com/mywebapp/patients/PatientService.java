package com.mywebapp.patients;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PatientService {
    private PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> listAll() {
        return (List<Patient>) patientRepository.findAll();
    }

    public void addNewPatient(Patient patient) {
        Optional<Patient> patientOptional = patientRepository.findPatientByName(patient.getName());
        if (patientOptional.isPresent()) {
            throw new IllegalStateException("there is a patient with this name already");
        }
        patientRepository.save(patient);
    }

    public void deletePatient(Integer id) {
        boolean exists = patientRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("patient with id " + id + " does not exist");
        }
        patientRepository.deleteById(id);
    }

    @Transactional
    public void updatePatient(Integer id, String name, LocalDate dateOfBirth, String diagnosis) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                "patient with this id does not exist"));
        if ((name != null) && (name.length() > 0) && !Objects.equals(patient.getName(), name)) {
            patient.setName(name);
        }
        if ((dateOfBirth != null) && (dateOfBirth.lengthOfMonth() > 0) && dateOfBirth.lengthOfYear() > 0 && !Objects.equals(patient.getDateOfBirth(), dateOfBirth)) {
            patient.setDateOfBirth(dateOfBirth);
        }
        if (diagnosis != null && diagnosis.length() > 0 && !Objects.equals(patient.getDiagnosis(), diagnosis)) {
            patient.setDiagnosis(diagnosis);
        }
    }
}
