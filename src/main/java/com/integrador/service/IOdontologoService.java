package com.integrador.service;

import com.integrador.dto.OdontologoDto;
import com.integrador.entity.Odontologo;
import com.integrador.exceptions.ResourceNotFoundException;

import java.util.List;

public interface IOdontologoService {

    OdontologoDto guardarOdontologo(Odontologo odontologo);

    List<OdontologoDto> listarOdontologos();

    OdontologoDto buscarOdontologoPorId(Long id);

    List<OdontologoDto> buscarOdontologoPorCriterio(String criterio);

    void eliminarOdontologo(Long id) throws ResourceNotFoundException;

    OdontologoDto actualizarOdontologo(Odontologo odontologo) throws ResourceNotFoundException;
}
