package com.integrador.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.integrador.dto.DomicilioDto;
import com.integrador.dto.PacienteDto;
import com.integrador.entity.Paciente;
import com.integrador.exceptions.ResourceNotFoundException;
import com.integrador.repository.PacienteRepository;
import com.integrador.service.IPacienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PacienteService implements IPacienteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);

    private final PacienteRepository pacienteRepository;
    private ObjectMapper objectMapper;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository, ObjectMapper objectMapper) {
        this.pacienteRepository = pacienteRepository;
        this.objectMapper = objectMapper;
    }


    @Override
    @Transactional
    public PacienteDto guardarPaciente(Paciente paciente) {
        Paciente pacienteNuevo = pacienteRepository.save(paciente);

        DomicilioDto domicilioDto = objectMapper.convertValue(pacienteNuevo.getDomicilio(), DomicilioDto.class);

        PacienteDto pacienteDto = objectMapper.convertValue(pacienteNuevo, PacienteDto.class);
        pacienteDto.setDomicilio(domicilioDto);
        LOGGER.info("Nuevo paciente registrado con exito: {}", pacienteDto);

        return pacienteDto;
    }

    @Override
    public List<PacienteDto> listarPacientes() {
        List<Paciente> pacientes = pacienteRepository.findAll();

        List<PacienteDto> pacientesDtos = pacientes.stream()
                .map(paciente -> {
//                  Domicilio domicilio = paciente.getDomicilio();
                    DomicilioDto domicilioDto = objectMapper.convertValue(paciente.getDomicilio(), DomicilioDto.class);

                    return new PacienteDto(paciente.getId(), paciente.getNombre(), paciente.getApellido(), paciente.getDni(), paciente.getFechaDeAlta(), domicilioDto);
                }).toList();


        LOGGER.info("Listado de todos los pacientes: {}", pacientesDtos);
        return pacientesDtos;
    }

    @Override
    public PacienteDto buscarPacientePorId(Long id) {
        Paciente pacienteBuscado = pacienteRepository.findById(id).orElse(null);

        PacienteDto pacienteDto = null;

        if (pacienteBuscado != null) {
            DomicilioDto domicilioDto = objectMapper.convertValue(pacienteBuscado.getDomicilio(), DomicilioDto.class);
            pacienteDto = objectMapper.convertValue(pacienteBuscado, PacienteDto.class);
            pacienteDto.setDomicilio(domicilioDto);
            LOGGER.info("Se ha encontrado el paciente con id: " + id + " sus datos son: " + pacienteDto);
        } else {
            LOGGER.info("El id no se encuentra registrado en la base de datos");
        }

        return pacienteDto;
    }

    @Override
    public List<PacienteDto> buscarPacientePorCriterio(String criterio) {
        List<Paciente> pacientes = pacienteRepository.buscarPacientePorCriterio(criterio);

        List<PacienteDto> pacientesDtos = pacientes.stream()
                .map(paciente -> {
                    DomicilioDto domicilioDto = objectMapper.convertValue(paciente.getDomicilio(), DomicilioDto.class);

                    return new PacienteDto(paciente.getId(), paciente.getNombre(), paciente.getApellido(), paciente.getDni(), paciente.getFechaDeAlta(), domicilioDto);
                }).toList();

        if (pacientesDtos.size() > 0) {
            LOGGER.info("Listado de Pacientes encontrados con el criterio de busqueda '" + criterio + "': " + pacientesDtos);
        } else {
            LOGGER.error("No se ha encontrado registro de Paciente buscado por criterio de busqueda ingresado  " + criterio);
        }

        return pacientesDtos;

    }

    @Override
    public void eliminarPaciente(Long id) throws ResourceNotFoundException {

        if (buscarPacientePorId(id) != null) {
            pacienteRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el paciente con id: {}", id);
        } else {
            LOGGER.error("No se ha encontrado registro de paciente con id: {}", id);
            throw new ResourceNotFoundException("No se encontro registro de paciente con id " + id + " en la base de datos");
        }


    }

    @Override
    public PacienteDto actualizarPaciente(Paciente paciente) throws ResourceNotFoundException {
        Paciente pacienteAActualizar = pacienteRepository.findById(paciente.getId()).orElse(null);
        PacienteDto pacienteActualizadoDto = null;

        if (pacienteAActualizar != null) {
            if (paciente.getDomicilio().getCalle() == null || paciente.getDomicilio().getLocalidad() == null || paciente.getDomicilio().getProvincia() == null) {
                DomicilioDto domicilioDto = objectMapper.convertValue(pacienteAActualizar.getDomicilio(), DomicilioDto.class);
                paciente.setDomicilio(pacienteAActualizar.getDomicilio());
                pacienteAActualizar = paciente;
                pacienteRepository.save(pacienteAActualizar);


                pacienteActualizadoDto = objectMapper.convertValue(pacienteAActualizar, PacienteDto.class);
                pacienteActualizadoDto.setDomicilio(domicilioDto);

            } else {
                pacienteAActualizar = paciente;
                pacienteRepository.save(pacienteAActualizar);

                DomicilioDto domicilioDto = objectMapper.convertValue(pacienteAActualizar.getDomicilio(), DomicilioDto.class);
                pacienteActualizadoDto = objectMapper.convertValue(pacienteAActualizar, PacienteDto.class);
                pacienteActualizadoDto.setDomicilio(domicilioDto);
                LOGGER.info("Paciente actualizado con exito: {}", pacienteActualizadoDto);
            }

            LOGGER.info("Paciente actualizado con exito: {}", pacienteActualizadoDto);
        } else {
            LOGGER.error("No fue posible actualizar los datos ya que el paciente no se encuentra registrado");
            throw new ResourceNotFoundException("No se encontro registro del paciente en la base de datos, por lo que no se puede actualizar");
        }

        return pacienteActualizadoDto;
    }
}
