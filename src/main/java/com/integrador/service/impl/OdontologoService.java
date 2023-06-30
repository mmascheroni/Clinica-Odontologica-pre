package com.integrador.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.integrador.dto.OdontologoDto;
import com.integrador.entity.Odontologo;
import com.integrador.exceptions.ResourceNotFoundException;
import com.integrador.repository.OdontologoRepository;
import com.integrador.service.IOdontologoService;
import com.integrador.utils.JsonPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService implements IOdontologoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OdontologoService.class);

    private final OdontologoRepository odontologoRepository;

    private ObjectMapper objectMapper;

    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository, ObjectMapper objectMapper) {
        this.odontologoRepository = odontologoRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public OdontologoDto guardarOdontologo(Odontologo odontologo) {
        Odontologo odontologoNuevo = odontologoRepository.save(odontologo);
        OdontologoDto odontologoDto = objectMapper.convertValue(odontologoNuevo, OdontologoDto.class);

        LOGGER.info("Se ha registrado con exito el odontologo: " + odontologoDto);

        return odontologoDto;
    }

    @Override
    public List<OdontologoDto> listarOdontologos() {
        List<Odontologo> odontologos = odontologoRepository.findAll();
        List<OdontologoDto> odontologosDtos = odontologos.stream()
                .map(odontologo -> {
                    return new OdontologoDto(odontologo.getId(), odontologo.getNombre(), odontologo.getApellido(), odontologo.getMatricula());
                }).toList();
        LOGGER.info("Listado de Odontologos: " + odontologosDtos);
        return odontologosDtos;
    }

    @Override
    public OdontologoDto buscarOdontologoPorId(Long id) {
        Odontologo odontologo = odontologoRepository.findById(id).orElse(null);
        OdontologoDto odontologoDto = null;

        if (odontologo != null) {
            odontologoDto = objectMapper.convertValue(odontologo, OdontologoDto.class);
            LOGGER.info("Odontologo encontrado: {}", odontologoDto);
        } else {
            LOGGER.error("El id no se encuentra registrado en la base de datos");
        }

        return odontologoDto;
    }

    @Override
    public List<OdontologoDto> buscarOdontologoPorCriterio(String criterio) {
        List<Odontologo> odontologos = odontologoRepository.buscarOdontologoPorCriterio(criterio.toLowerCase());

        List<OdontologoDto> odontologosDtos = odontologos.stream()
                .map(odontologo -> {
                    return new OdontologoDto(odontologo.getId(), odontologo.getNombre(), odontologo.getApellido(), odontologo.getMatricula());
                }).toList();

        if (odontologosDtos.size() > 0) {
            LOGGER.info("Listado de Odontologos encontrados con el criterio de busqueda '" + criterio + "': " + odontologosDtos);
        } else {
            LOGGER.error("No se ha encontrado registro de odontologo buscado por criterio de busqueda ingresado  " + criterio);
        }

        return odontologosDtos;

    }

    @Override
    public void eliminarOdontologo(Long id) throws ResourceNotFoundException {
        if (buscarOdontologoPorId(id) != null) {
            odontologoRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado con exito el odontologo con id: {}", id);
        } else {
            LOGGER.error("No se encuentra odontologo registado en la base de datos con id: {}", id);
            throw new ResourceNotFoundException("No se ha encontrado el odontologo con id " + id);
        }
    }

    @Override
    public OdontologoDto actualizarOdontologo(Odontologo odontologo) throws ResourceNotFoundException {
        Odontologo odontologoAActualizar = odontologoRepository.findById(odontologo.getId()).orElse(null);
        OdontologoDto odontologoDto = null;

        if (odontologoAActualizar != null) {
            odontologoAActualizar = odontologo;
            odontologoRepository.save(odontologoAActualizar);
            odontologoDto = objectMapper.convertValue(odontologoAActualizar, OdontologoDto.class);
            LOGGER.info("Odontologo actualizado con exito: {}", odontologoDto);
        } else {
            LOGGER.error("No fue posible actualizar los datos ya que el odontologo no se encuentra registrado");
            throw new ResourceNotFoundException("No se encontro registro del odontologo en la base de datos, por lo que no se puede actualizar");
        }

        return odontologoDto;
    }
}
