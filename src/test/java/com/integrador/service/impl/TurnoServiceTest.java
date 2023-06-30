package com.integrador.service.impl;


import com.integrador.dto.OdontologoDto;
import com.integrador.dto.PacienteDto;
import com.integrador.dto.TurnoDto;
import com.integrador.entity.Domicilio;
import com.integrador.entity.Odontologo;
import com.integrador.entity.Paciente;
import com.integrador.entity.Turno;
import com.integrador.exceptions.BadRequestException;
import com.integrador.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TurnoServiceTest {

    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    private static Paciente paciente;
    private static Odontologo odontologo;

    @BeforeAll
    public static void init() {
        paciente = new Paciente("Mauro", "Mascheroni", "12345678", LocalDate.of(2023, 07, 31), new Domicilio("8 de octubre", 2830, "Montevideo", "La Blanqueada"));

        odontologo = new Odontologo("Sergio", "Marquez", "AAA001");
    }


    @Test
    @Order(1)
    void deberiaRegistrarTurno() throws BadRequestException {
        PacienteDto pacienteDto = pacienteService.guardarPaciente(paciente);
        OdontologoDto odontologoDto = odontologoService.guardarOdontologo(odontologo);

        TurnoDto turnoDto = turnoService.guardarTurno(new Turno(paciente, odontologo, LocalDateTime.of(LocalDate.of(2023, 9, 29), LocalTime.of(10, 20))));


        Assertions.assertNotNull(turnoDto);
        Assertions.assertNotNull(turnoDto.getId());
        Assertions.assertEquals(turnoDto.getPaciente(), pacienteDto.getNombre() + " " + pacienteDto.getApellido());
        Assertions.assertEquals(turnoDto.getOdontologo(), odontologoDto.getNombre() + " " + odontologoDto.getApellido());
    }


    @Test
    @Order(1)
    void deberiaDevolverTurnoById() throws BadRequestException, ResourceNotFoundException {
        PacienteDto pacienteDto = pacienteService.guardarPaciente(paciente);
        OdontologoDto odontologoDto = odontologoService.guardarOdontologo(odontologo);

        TurnoDto turnoDto = turnoService.guardarTurno(new Turno(paciente, odontologo, LocalDateTime.of(LocalDate.of(2023, 9, 29), LocalTime.of(10, 20))));


        Assertions.assertNotNull(turnoService.buscarTurnoPorId(1L));
    }


    @Test
    @Order(1)
    void deberiaModificarTurno() throws BadRequestException, ResourceNotFoundException {
        PacienteDto pacienteDto = pacienteService.guardarPaciente(paciente);
        OdontologoDto odontologoDto = odontologoService.guardarOdontologo(odontologo);

        Turno turno = new Turno(paciente, odontologo, LocalDateTime.of(LocalDate.of(2023, 9, 29), LocalTime.of(10, 20)));
        TurnoDto turnoDto = turnoService.guardarTurno(turno);
        turno.setFecha(LocalDateTime.of(LocalDate.of(2023, 9, 29), LocalTime.of(10, 10)));

        turnoDto = turnoService.actualizarTurno(turno);


        Assertions.assertEquals(turnoDto.getFecha(), LocalDateTime.of(LocalDate.of(2023, 9, 29), LocalTime.of(10, 10)));


    }


}
