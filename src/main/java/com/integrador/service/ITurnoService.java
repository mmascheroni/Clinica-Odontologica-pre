package com.integrador.service;

import com.integrador.dto.TurnoDto;
import com.integrador.entity.Turno;
import com.integrador.exceptions.BadRequestException;
import com.integrador.exceptions.ResourceNotFoundException;

import java.util.List;

public interface ITurnoService {

    TurnoDto guardarTurno(Turno turno) throws BadRequestException;

    List<TurnoDto> listarTurnos();

    TurnoDto buscarTurnoPorId(Long id) throws ResourceNotFoundException;

    List<TurnoDto> buscarTurnoPorCriterio(String criterio);

    TurnoDto actualizarTurno(Turno turno) throws ResourceNotFoundException;

    void eliminarTurno(Long id) throws ResourceNotFoundException;
}
