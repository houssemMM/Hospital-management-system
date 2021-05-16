package com.javastar.service.patient;

import com.javastar.dao.patient.PatientRepository;
import com.javastar.model.patient.BloodGroup;
import com.javastar.model.patient.Gender;
import com.javastar.model.patient.Patient;
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

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    PatientService patientService;

    @BeforeEach
    public void setUp() {
        patientService = new PatientService(patientRepository);
    }

    @Test
    @DisplayName("Should Find Patient By Id")
    public void shouldFindPatientById() throws ResourceNotFoundException {
        Patient expectedPatient = new Patient(123L, "Marie", LocalDate.of(1990,12,4), Gender.FEMALE, BloodGroup.O);
        // when
        Mockito.when(patientRepository.findById(123L)).thenReturn(Optional.of(expectedPatient));
        // actual
        Patient actualPatient = patientService.getPatient(123L);
        // Assert
        Assertions.assertThat(actualPatient.getName()).isEqualTo(expectedPatient.getName());
        Assertions.assertThat(actualPatient.getBirthDate()).isEqualTo(expectedPatient.getBirthDate());
        Assertions.assertThat(actualPatient.getGender()).isEqualTo(expectedPatient.getGender());
        Assertions.assertThat(actualPatient.getBloodGroup()).isEqualTo(expectedPatient.getBloodGroup());
    }

    @Test
    @DisplayName("Should throw exception when patient doest not exist")
    void shouldThrowsExceptionPatientByIdNotFound() {

        /*Assertions.assertThatExceptionOfType(ResourceNotFoundException.class)
                .isThrownBy(() -> doctorService.getDoctor(123L))
                .withMessage("Doctor not found with id :123");*/
        // when
        Throwable thrown = catchThrowable(() ->
            patientService.getPatient(123L));
        // then
        Assertions.assertThat(thrown)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Patient not found with id :123");
    }

    @Test
    @DisplayName("Should Add Patient")
    public void shouldAddPatient() {
        Patient actualPatient = new Patient(123L, "Marie", LocalDate.of(1990,12,4), Gender.FEMALE, BloodGroup.O);
        // test
        patientService.addPatient(actualPatient);
        // verify
        Mockito.verify(patientRepository, Mockito.times(1))
                .save(ArgumentMatchers.any(Patient.class));
    }

    @Test
    @DisplayName("Should Modify Patient")
    void shouldModifyPatient() throws ResourceNotFoundException {
        Patient expectedPatient = new Patient(123L, "Marie", LocalDate.of(1990,12,4), Gender.FEMALE, BloodGroup.O);
        // when
        Mockito.when(patientRepository.findById(123L)).thenReturn(Optional.of(expectedPatient));
        // test
        patientService.updatePatient(expectedPatient, 123L);
        // verify
        Mockito.verify(patientRepository, Mockito.times(1))
                .save(ArgumentMatchers.any(Patient.class));
    }

    @Test
    @DisplayName("Should Delete Patient")
    public void shouldDeletePatient() throws ResourceNotFoundException {
        Patient expectedPatient = new Patient(123L, "Marie", LocalDate.of(1990,12,4), Gender.FEMALE, BloodGroup.O);
        // when
        Mockito.when(patientRepository.findById(123L)).thenReturn(Optional.of(expectedPatient));
        // test
        patientService.deletePatient(123L);
        // verify
        Mockito.verify(patientRepository, Mockito.times(1))
                .delete(ArgumentMatchers.any(Patient.class));
    }
}