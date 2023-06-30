package com.integrador.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.integrador.dto.OdontologoDto;
import com.integrador.dto.PacienteDto;
import com.integrador.dto.TurnoDto;
import com.integrador.entity.Turno;
import com.integrador.exceptions.BadRequestException;
import com.integrador.exceptions.ResourceNotFoundException;
import com.integrador.repository.TurnoRepository;
import com.integrador.service.ITurnoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService implements ITurnoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);

    private final TurnoRepository turnoRepository;
    private final OdontologoService odontologoService;
    private final PacienteService pacienteService;
    private ObjectMapper objectMapper;

    @Autowired
    public TurnoService(TurnoRepository turnoRepository, OdontologoService odontologoService, PacienteService pacienteService, ObjectMapper objectMapper) {
        this.turnoRepository = turnoRepository;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;
        this.objectMapper = objectMapper;
    }


    @Override
    public TurnoDto guardarTurno(Turno turno) throws BadRequestException {
        TurnoDto turnoDto = null;

        PacienteDto paciente = pacienteService.buscarPacientePorId(turno.getPaciente().getId());
        OdontologoDto odontologo = odontologoService.buscarOdontologoPorId(turno.getOdontologo().getId());

        if (paciente == null || odontologo == null) {
            if (paciente == null && odontologo == null) {
                LOGGER.error("El paciente y el odontologo no se encuentra en la base de datos");
                throw new BadRequestException("El paciente y el odontologo no se encuentra en la base de datos");
            }
            if (odontologo == null) {
                LOGGER.error("El odontologo no se encuentra en la base de datos");
                throw new BadRequestException("El odontologo no se encuentra en la base de datos");
            }
            if (paciente == null) {
                LOGGER.error("El paciente no se encuentra en la base de datos");
                throw new BadRequestException("El paciente no se encuentra en la base de datos");
            }
        } else {
            Turno turnoG = turnoRepository.save(turno);
            turnoDto = new TurnoDto(turnoG.getId(), paciente.getNombre() + " " + paciente.getApellido(), odontologo.getNombre() + " " + odontologo.getApellido(), turnoG.getFecha());
            LOGGER.info("Se ha registrado el turno correctamente {}", turnoDto);
        }

        return turnoDto;
    }

    @Override
    public List<TurnoDto> listarTurnos() {
        List<Turno> turnos = turnoRepository.findAll();
        List<TurnoDto> turnosDtos = turnos.stream()
                .map(turno -> TurnoDto.fromTurno(turno)).toList();
        LOGGER.info("Listado de turnos: {}", turnosDtos);
        return turnosDtos;
    }

    @Override
    public TurnoDto buscarTurnoPorId(Long id) throws ResourceNotFoundException {
        Turno turnoBuscado = turnoRepository.findById(id).orElse(null);

        TurnoDto turnoDto = null;

        if (turnoBuscado != null) {
            turnoDto = TurnoDto.fromTurno(turnoBuscado);
            LOGGER.info("Se ha encontrado el turno con id " + id + ": " + turnoDto);
        } else {
            LOGGER.warn("No se ha encontrado ningun turno con id: {}", id);
            throw new ResourceNotFoundException("No se ha encontrado ningun turno con id: " + id);
        }

        return turnoDto;
    }

    @Override
    public List<TurnoDto> buscarTurnoPorCriterio(String criterio) {
        List<Turno> turnos = turnoRepository.buscarTurnoPorCriterio(criterio.toLowerCase());

        List<TurnoDto> turnosDtos = turnos.stream()
                .map(turno -> TurnoDto.fromTurno(turno)).toList();

        if (turnosDtos.size() > 0) {
            LOGGER.info("Listado de Turnos encontrados con el criterio de busqueda '" + criterio + "': " + turnosDtos);
        } else {
            LOGGER.error("No se ha encontrado registro de Turnos que coincidan con criterio de busqueda ingresado  " + criterio);
        }

        return turnosDtos;
    }


    @Override
    public TurnoDto actualizarTurno(Turno turno) throws ResourceNotFoundException {
        Turno turnoAActualizar = turnoRepository.findById(turno.getId()).orElse(null);
        TurnoDto turnoDto = null;
        PacienteDto paciente = pacienteService.buscarPacientePorId(turno.getPaciente().getId());
        OdontologoDto odontologo = odontologoService.buscarOdontologoPorId(turno.getOdontologo().getId());

        if (turnoAActualizar == null || paciente == null || odontologo == null) {
            if (turnoAActualizar == null) {
                LOGGER.error("No fue posible actualizar los datos ya que el paciente no se encuentra registrado");
                throw new ResourceNotFoundException("No se encontro registro del turno para actualizar");
            }
            if (paciente == null && odontologo == null) {
                LOGGER.error("El paciente y el odontologo no se encuentra en la base de datos");
                throw new ResourceNotFoundException("El paciente y el odontologo no se encuentra en la base de datos");
            }
            if (odontologo == null) {
                LOGGER.error("El odontologo no se encuentra en la base de datos");
                throw new ResourceNotFoundException("El odontologo no se encuentra en la base de datos");
            }
            if (paciente == null) {
                LOGGER.error("El paciente no se encuentra en la base de datos");
                throw new ResourceNotFoundException("El paciente no se encuentra en la base de datos");
            }
        } else {
            Turno turnoG = turnoRepository.save(turno);
            turnoDto = new TurnoDto(turnoG.getId(), paciente.getNombre() + " " + paciente.getApellido(), odontologo.getNombre() + " " + odontologo.getApellido(), turnoG.getFecha());
            LOGGER.info("Se ha actualizado el turno correctamente {}", turnoDto);
        }

        return turnoDto;
    }

    @Override
    public void eliminarTurno(Long id) throws ResourceNotFoundException {

        if (buscarTurnoPorId(id) != null) {
            turnoRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el turno con id {}", id);
        } else {
            LOGGER.error("No se ha encontrado el turno con id {}", id);
            throw new ResourceNotFoundException("No se ha encontrado el turno con id: " + id);
        }
    }
}
