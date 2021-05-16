package com.javastar.service.patient;

import com.javastar.dao.patient.PatientRepository;
import com.javastar.model.patient.BloodGroup;
import com.javastar.model.patient.Gender;
import com.javastar.model.patient.Patient;
import com.javastar.service.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class PatientService {
    @Autowired
    private final PatientRepository patientRepository;

    public List<Patient> getPatients() {
        return patientRepository.findAll();
    }

    @PostConstruct
    public void initPatient() {
        patientRepository.saveAll(Stream.of(new Patient(111,"Jean",
                LocalDate.of(1981,4,12), Gender.MALE, BloodGroup.AB))
                .collect(Collectors.toList()));
    }

    public Patient getPatient(Long id) throws ResourceNotFoundException {
        // Add Exception ifNotExist
        return patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id :" + id));
    }

    public void addPatient(Patient p) {
        patientRepository.save(p);
    }

    public void updatePatient(Patient p, long id) throws ResourceNotFoundException {
        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id :" + id));
        existingPatient.setName(p.getName());
        existingPatient.setBirthDate(p.getBirthDate());
        existingPatient.setGender(p.getGender());
        existingPatient.setBloodGroup(p.getBloodGroup());
        patientRepository.save(p);
    }

    public void deletePatient(long id) throws ResourceNotFoundException {
        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id :" + id));
        patientRepository.delete(existingPatient);
    }
}
