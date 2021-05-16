package com.javastar.controller.doctor;

import com.javastar.model.doctor.Doctor;
import com.javastar.service.doctor.DoctorService;
import com.javastar.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@AllArgsConstructor
@RequestMapping("/api")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    // get all doctors
    @GetMapping("/doctors")
    public List<Doctor> getAllDoctors() {
        return doctorService.getDoctors();
    }

    // get doctor by id
    @GetMapping("/doctor/{id}")
    public Doctor getDoctorById(@PathVariable (value = "id") long id) throws ResourceNotFoundException {
        return doctorService.getDoctor(id);
    }
    // create doctor
    // update doctor
    // delete doctor

}
