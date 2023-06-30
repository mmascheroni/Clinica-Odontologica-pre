package com.integrador.controller;

import com.integrador.dto.OdontologoDto;
import com.integrador.entity.Odontologo;
import com.integrador.exceptions.ResourceNotFoundException;
import com.integrador.service.IOdontologoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    private IOdontologoService odontologoService;

    @Autowired
    public OdontologoController(IOdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    // GET
    @GetMapping("/{id}")
    public ResponseEntity<OdontologoDto> buscarOdontologoPorId(@PathVariable Long id) throws ResourceNotFoundException {
        ResponseEntity<OdontologoDto> respuesta;
        OdontologoDto odontologoDto = odontologoService.buscarOdontologoPorId(id);

        if (odontologoDto != null) {
            respuesta = new ResponseEntity<>(odontologoDto, null, HttpStatus.OK);
            return respuesta;
        } else {
            throw new ResourceNotFoundException("No se ha encontrado registro de odontologo con id: " + id);
        }
    }

    // GET
    @GetMapping("/odontologo")
    public List<OdontologoDto> buscarOdontologoPorCriterio(@RequestParam String criterio) {
        List<OdontologoDto> odontologoDtos = odontologoService.buscarOdontologoPorCriterio(criterio);

        return odontologoDtos;
    }

    // POST
    @PostMapping("/registrar")
    public ResponseEntity<OdontologoDto> registarOdontologo(@Valid @RequestBody Odontologo odontologo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(odontologoService.guardarOdontologo(odontologo));
    }

    // GET
    @GetMapping
    public List<OdontologoDto> listarTodos() {
        return odontologoService.listarOdontologos();
    }

    // PUT
    @PutMapping("/actualizar")
    public ResponseEntity<OdontologoDto> actualizarOdontologo(@Valid @RequestBody Odontologo odontologo) throws ResourceNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(odontologoService.actualizarOdontologo(odontologo));
    }

    // DELETE
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        odontologoService.eliminarOdontologo(id);
        return ResponseEntity.ok("Odontologo eliminado");
    }

}
