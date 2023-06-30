package com.integrador.controller;

import com.integrador.dto.PacienteDto;
import com.integrador.entity.Paciente;
import com.integrador.exceptions.ResourceNotFoundException;
import com.integrador.service.IPacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private final IPacienteService pacienteService;

    @Autowired
    public PacienteController(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }


    // POST
    @PostMapping("/registrar")
    public ResponseEntity<PacienteDto> registrarPaciente(@Valid @RequestBody Paciente paciente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteService.guardarPaciente(paciente));
    }

    // GET
    @GetMapping("/{id}")
    public ResponseEntity<PacienteDto> buscarPacientePorId(@PathVariable Long id) throws ResourceNotFoundException {
        ResponseEntity<PacienteDto> respuesta;
        PacienteDto pacienteDto = pacienteService.buscarPacientePorId(id);

        if (pacienteDto != null) {
            respuesta = new ResponseEntity<>(pacienteDto, null, HttpStatus.OK);
            return respuesta;
        } else {
            throw new ResourceNotFoundException("No se ha encontrado registro de paciente con id: " + id);
        }
    }

    // GET
    @GetMapping("/paciente")
    public List<PacienteDto> buscarPacientePorCriterio(@RequestParam String criterio) {
        List<PacienteDto> pacientesDtos = pacienteService.buscarPacientePorCriterio(criterio);

        return pacientesDtos;
    }

    // DELETE
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.ok("Paciente eliminado");
    }

    // GET
    @GetMapping
    public List<PacienteDto> listarTodosLosPacientes() {
        return pacienteService.listarPacientes();
    }

    // PUT
    @PutMapping("/actualizar")
    public ResponseEntity<PacienteDto> actualzarPaciente(@Valid @RequestBody Paciente paciente) throws ResourceNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(pacienteService.actualizarPaciente(paciente));
    }

}