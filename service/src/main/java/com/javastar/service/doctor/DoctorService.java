package com.javastar.service.doctor;

import com.javastar.dao.doctor.DoctorRepository;
import com.javastar.model.doctor.Doctor;
import com.javastar.service.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class DoctorService {

    @Autowired
    private final DoctorRepository doctorRepository;

    @PostConstruct
    public void initDoctor() {
        doctorRepository.saveAll(Stream.of(new Doctor(111,"Pierre","Cardiac"),
                new Doctor(112, "Christophe","eye")).collect(Collectors.toList()));
    }

    public List<Doctor> getDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor getDoctor(Long id) throws ResourceNotFoundException {
        // Add Exception ifNotExist
        return doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id :" + id));
    }

    public void addDoctor(Doctor d) {
        doctorRepository.save(d);
    }

    public void updateDoctor(Doctor d, long id) throws ResourceNotFoundException {
        Doctor existingDoctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id :" + id));
        existingDoctor.setName(d.getName());
        existingDoctor.setSpecialist(d.getSpecialist());
        doctorRepository.save(d);
    }

    public void deleteDoctor(long id) throws ResourceNotFoundException {
        Doctor existingDoctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id :" + id));
        doctorRepository.delete(existingDoctor);
    }
}
