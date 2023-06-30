package com.integrador.service;

import com.integrador.dto.PacienteDto;
import com.integrador.entity.Paciente;
import com.integrador.exceptions.ResourceNotFoundException;

import java.util.List;

public interface IPacienteService {

    PacienteDto guardarPaciente(Paciente paciente);

    List<PacienteDto> listarPacientes();

    PacienteDto buscarPacientePorId(Long id);

    List<PacienteDto> buscarPacientePorCriterio(String criterio);

    void eliminarPaciente(Long id) throws ResourceNotFoundException;

    PacienteDto actualizarPaciente(Paciente paciente) throws ResourceNotFoundException;
}
