package com.javastar.controller.doctor;

import com.javastar.model.doctor.Doctor;
import com.javastar.service.doctor.DoctorService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

@WebMvcTest(controllers = DoctorController.class)
public class DoctorControllerTest {

    @MockBean
    DoctorService doctorService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Should List All Doctors when making GET request to endpoint - /api/doctors")
    void shouldListAllDoctors() throws Exception {
        Doctor doctor1 = new Doctor(123L, "Jean", "Eye");
        Doctor doctor2 = new Doctor(124L, "Nathalie", "Nose");
        // when
        Mockito.when(doctorService.getDoctors()).thenReturn(Arrays.asList(doctor1, doctor2));
        // expect
        mockMvc.perform(MockMvcRequestBuilders.get("/api/doctors"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(2)));
    }

}