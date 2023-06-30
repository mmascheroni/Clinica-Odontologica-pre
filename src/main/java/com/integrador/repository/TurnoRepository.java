package com.integrador.repository;

import com.integrador.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TurnoRepository extends JpaRepository<Turno, Long> {

//    @Query("SELECT t FROM Turno t WHERE t.id LIKE %:criterio% OR LOWER(t.paciente) LIKE LOWER(concat('%', :criterio, '%')) OR LOWER(t.odontologo) LIKE LOWER(concat('%', :criterio, '%')) OR t.fechaYHora LIKE %:criterio%")
//    List<Turno> buscarTurnoPorCriterio(@Param("criterio") String criterio);

    @Query("SELECT t FROM Turno t WHERE t.id LIKE %:criterio% OR LOWER(t.paciente.nombre) LIKE LOWER(concat('%', :criterio, '%')) OR LOWER(t.paciente.apellido) LIKE LOWER(concat('%', :criterio, '%')) OR LOWER(t.odontologo.nombre) LIKE LOWER(concat('%', :criterio, '%')) OR LOWER(t.odontologo.apellido) LIKE LOWER(concat('%', :criterio, '%')) OR t.fechaYHora LIKE %:criterio%")
    List<Turno> buscarTurnoPorCriterio(@Param("criterio") String criterio);

}
