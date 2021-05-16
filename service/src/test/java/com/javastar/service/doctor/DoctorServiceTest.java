package com.javastar.service.doctor;

import com.javastar.dao.doctor.DoctorRepository;
import com.javastar.model.doctor.Doctor;
import com.javastar.service.exception.ResourceNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

@ExtendWith(MockitoExtension.class)
public class DoctorServiceTest {

    @Mock
    private DoctorRepository doctorRepository;

    DoctorService doctorService;

    @BeforeEach
    public void setUp() {
        doctorService = new DoctorService(doctorRepository);
    }

    @Test
    @DisplayName("Should Find Doctor By Id")
    void shouldFindDoctorById() throws ResourceNotFoundException {
        Doctor expectedDoctor = new Doctor(123L, "Jean", "Eye");
        // when
        Mockito.when(doctorRepository.findById(123L)).thenReturn(Optional.of(expectedDoctor));
        // actual
        Doctor actualDoctor = doctorService.getDoctor(123L);
        // Assert
        Assertions.assertThat(actualDoctor.getName()).isEqualTo(expectedDoctor.getName());
        Assertions.assertThat(actualDoctor.getSpecialist()).isEqualTo(expectedDoctor.getSpecialist());
    }

    @Test
    @DisplayName("Should throw exception when doctor doest not exist")
    void shouldThrowsExceptionDoctorByIdNotFound() {

        /*Assertions.assertThatExceptionOfType(ResourceNotFoundException.class)
                .isThrownBy(() -> doctorService.getDoctor(123L))
                .withMessage("Doctor not found with id :123");*/
        // when
        Throwable thrown = catchThrowable(() -> doctorService.getDoctor(123L));
        // then
        Assertions.assertThat(thrown)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Doctor not found with id :123");
    }

    @Test
    @DisplayName("Should Add Doctor")
    void shouldAddDoctor() {
        Doctor actualDoctor = new Doctor(123L, "SAM", "skin");
        // test
        doctorService.addDoctor(actualDoctor);
        // verify
        Mockito.verify(doctorRepository, Mockito.times(1))
                .save(ArgumentMatchers.any(Doctor.class));
    }

    @Test
    @DisplayName("Should Modify Doctor")
    void shouldModifyDoctor() throws ResourceNotFoundException {
        Doctor expectedDoctor = new Doctor(123L, "Jean", "Eye");
        // when
        Mockito.when(doctorRepository.findById(123L)).thenReturn(Optional.of(expectedDoctor));
        // test
        doctorService.updateDoctor(expectedDoctor, 123L);
        // verify
        Mockito.verify(doctorRepository, Mockito.times(1))
                .save(ArgumentMatchers.any(Doctor.class));
    }

    @Test
    @DisplayName("Should delete Doctor")
    void shouldDeleteDoctor() throws ResourceNotFoundException {
        Doctor expectedDoctor = new Doctor(123L, "Jean", "Eye");
        // when
        Mockito.when(doctorRepository.findById(123L)).thenReturn(Optional.of(expectedDoctor));
        // test
        doctorService.deleteDoctor(123L);
        // verify
        Mockito.verify(doctorRepository, Mockito.times(1))
                .delete(ArgumentMatchers.any(Doctor.class));
    }
}