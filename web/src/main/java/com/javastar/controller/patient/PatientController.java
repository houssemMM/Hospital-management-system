package com.javastar.controller.patient;

import com.javastar.model.doctor.Doctor;
import com.javastar.model.patient.Patient;
import com.javastar.service.exception.ResourceNotFoundException;
import com.javastar.service.patient.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@AllArgsConstructor
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/api/patients")
    public List<Patient> getPatients() {
        return patientService.getPatients();
    }

    // get patient by id
    @GetMapping("/patient/{id}")
    public Patient getDoctorById(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        return patientService.getPatient(id);
    }
    // create patient
    // update patient
    // delete patient
}
