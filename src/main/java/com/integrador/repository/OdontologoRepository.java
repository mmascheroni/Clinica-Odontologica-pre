package com.integrador.repository;

import com.integrador.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo, Long> {

    // @Query("SELECT o FROM Odontologo o WHERE o.id LIKE %:criterio% OR o.nombre
    // LIKE %:criterio% OR o.apellido LIKE %:criterio% OR o.matricula LIKE
    // %:criterio%")
    // List<Odontologo> buscarOdontologoPorCriterio(@Param("criterio") String
    // criterio);

    @Query("SELECT o FROM Odontologo o WHERE o.id LIKE %:criterio% OR LOWER(o.nombre) LIKE LOWER(concat('%', :criterio, '%')) OR LOWER(o.apellido) LIKE LOWER(concat('%', :criterio, '%')) OR LOWER(o.matricula) LIKE LOWER(concat('%', :criterio, '%'))")
    List<Odontologo> buscarOdontologoPorCriterio(@Param("criterio") String criterio);

}
