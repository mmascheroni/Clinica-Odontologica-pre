package com.integrador.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PacienteDto {
    private Long id;

    private String nombre;
    private String apellido;
    private String dni;
    private LocalDate fechaDeAlta;
    private DomicilioDto domicilio;

    public PacienteDto() {
    }

    public PacienteDto(Long id, String nombre, String apellido, String dni, LocalDate fechaDeAlta, DomicilioDto domicilio) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaDeAlta = fechaDeAlta;
        this.domicilio = domicilio;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public LocalDate getFechaDeAlta() {
        return fechaDeAlta;
    }

    public void setFechaDeAlta(LocalDate fechaDeAlta) {
        this.fechaDeAlta = fechaDeAlta;
    }

    public DomicilioDto getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(DomicilioDto domicilio) {
        this.domicilio = domicilio;
    }

    @Override
    public String toString() {
        return
                "id: " + id +
                        ", nombre: '" + nombre + '\'' +
                        ", apellido: '" + apellido + '\'' +
                        ", dni: '" + dni + '\'' +
                        ", fechaDeAlta: " + fechaDeAlta +
                        ", domicilio: " + domicilio;
    }
}
