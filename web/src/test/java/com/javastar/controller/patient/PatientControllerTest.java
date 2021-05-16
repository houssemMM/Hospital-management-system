package com.javastar.controller.patient;

import com.javastar.model.doctor.Doctor;
import com.javastar.model.patient.BloodGroup;
import com.javastar.model.patient.Gender;
import com.javastar.model.patient.Patient;
import com.javastar.service.patient.PatientService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(controllers = PatientController.class)
public class PatientControllerTest {

    @MockBean
    PatientService patientService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Should List All Patients when making GET request to endpoint - /api/patients")
    void shouldListAllPatients() throws Exception {
        Patient patient1 = new Patient(123L, "Marie", LocalDate.of(1990,12,4), Gender.FEMALE, BloodGroup.O);
        Patient patient2 = new Patient(124L, "Peter", LocalDate.of(1990,12,4), Gender.FEMALE, BloodGroup.O);
        // when
        Mockito.when(patientService.getPatients()).thenReturn(Arrays.asList(patient1, patient2));
        // expect
        mockMvc.perform(MockMvcRequestBuilders.get("/api/patients"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(2)));
    }
}