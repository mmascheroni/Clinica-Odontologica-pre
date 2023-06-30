package com.integrador.controller;

import com.integrador.dto.TurnoDto;
import com.integrador.entity.Turno;
import com.integrador.exceptions.BadRequestException;
import com.integrador.exceptions.ResourceNotFoundException;
import com.integrador.service.ITurnoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/turnos")
public class TurnoController {

    private final ITurnoService turnoService;

    @Autowired
    public TurnoController(ITurnoService turnoService) {
        this.turnoService = turnoService;
    }

    //GET
    @GetMapping("/{id}")
    public ResponseEntity<TurnoDto> buscarTurnoPorId(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(turnoService.buscarTurnoPorId(id));
    }

    @GetMapping()
    public List<TurnoDto> listarTurnos() {
        return turnoService.listarTurnos();
    }

    // GET
    @GetMapping("/turno")
    public List<TurnoDto> buscarTurnoPorCriterio(@RequestParam String criterio) {
        List<TurnoDto> turnosDtos = turnoService.buscarTurnoPorCriterio(criterio);

        return turnosDtos;
    }

    //POST
    @PostMapping("/registrar")
    public ResponseEntity<TurnoDto> guardarTurno(@Valid @RequestBody Turno turno) throws BadRequestException {
        return ResponseEntity.status(HttpStatus.CREATED).body(turnoService.guardarTurno(turno));
    }

    //DELETE
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarTurno(@PathVariable Long id) throws ResourceNotFoundException {
        turnoService.eliminarTurno(id);
        return ResponseEntity.ok("El turno ha sido eliminado");
    }

    //PUT
    @PutMapping("/actualizar")
    public ResponseEntity<TurnoDto> actualizarTurno(@Valid @RequestBody Turno turno) throws ResourceNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(turnoService.actualizarTurno(turno));
    }
}
