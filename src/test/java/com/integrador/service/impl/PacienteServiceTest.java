package com.integrador.service.impl;


import com.integrador.dto.PacienteDto;
import com.integrador.entity.Domicilio;
import com.integrador.entity.Paciente;
import com.integrador.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PacienteServiceTest {


    @Autowired
    private PacienteService pacienteService;

    private static Paciente paciente;


    @BeforeAll
    public static void init() {
        paciente = new Paciente("Sergio", "Mascheroni", "12345678", LocalDate.of(2023, 07, 31), new Domicilio("8 de octubre", 2830, "Montevideo", "La Blanqueada"));
    }


    @Test
    @Order(1)
    void deberiaRegistrarPaciente() {
        PacienteDto pacienteDto = pacienteService.guardarPaciente(paciente);

        Assertions.assertNotNull(pacienteDto);
        Assertions.assertNotNull(pacienteDto.getId());
        Assertions.assertEquals(paciente.getNombre(), pacienteDto.getNombre());
    }


    @Test
    @Order(1)
    void deberiaDevolverPacienteById() {
        PacienteDto pacienteDto = pacienteService.guardarPaciente(paciente);

        Assertions.assertNotNull(pacienteService.buscarPacientePorId(pacienteDto.getId()));
    }

    @Test
    @Order(1)
    void deberiaModificarPaciente() throws ResourceNotFoundException {
        PacienteDto pacienteDto = pacienteService.guardarPaciente(paciente);

        paciente.setNombre("Mauro");
        paciente.setApellido("Marquez");

        pacienteDto = pacienteService.actualizarPaciente(paciente);


        Assertions.assertEquals(pacienteDto.getNombre(), paciente.getNombre());
        Assertions.assertEquals(pacienteDto.getId(), 1);

    }
}
