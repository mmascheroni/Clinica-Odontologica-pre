package com.integrador.service.impl;


import com.integrador.dto.OdontologoDto;
import com.integrador.entity.Odontologo;
import com.integrador.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;

    private static Odontologo odontologo;


    @BeforeAll
    public static void init() {
        odontologo = new Odontologo("Sergio", "Marquez", "AAA001");
    }

    @Test
    @Order(1)
    void deberiaRegistrarOdontologo() {
        OdontologoDto odontologoDto = odontologoService.guardarOdontologo(odontologo);

        Assertions.assertNotNull(odontologoDto);
        Assertions.assertNotNull(odontologoDto.getId());
        Assertions.assertEquals(odontologoDto.getNombre(), odontologo.getNombre());
    }


    @Test
    @Order(1)
    void deberiaDevolverOdontologoById() {
        OdontologoDto odontologoDto = odontologoService.guardarOdontologo(odontologo);

        Assertions.assertNotNull(odontologoService.buscarOdontologoPorId(odontologoDto.getId()));
    }

    @Test
    @Order(1)
    void deberiaModificarOdontologo() throws ResourceNotFoundException {
        OdontologoDto odontologoDto = odontologoService.guardarOdontologo(odontologo);

        odontologo.setNombre("Mauro");
        odontologo.setApellido("Marquez");

        odontologoDto = odontologoService.actualizarOdontologo(odontologo);


        Assertions.assertEquals(odontologo.getNombre(), odontologo.getNombre());
        Assertions.assertEquals(odontologoDto.getId(), 1);

    }


}
