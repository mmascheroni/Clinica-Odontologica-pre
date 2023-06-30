package com.integrador.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Entity
@Table(name = "PACIENTES")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50, message = "El nombre debe tener hasta 50 caracteres")
    @NotNull(message = "El nombre el paciente no puede ser nulo")
    @NotBlank(message = "Debe especificarse el nombre del paciente")
    private String nombre;

    @Size(max = 50, message = "El apellido debe tener hasta 50 caracteres")
    @NotNull(message = "El apellido el paciente no puede ser nulo")
    @NotBlank(message = "Debe especificarse el apellido del paciente")
    private String apellido;

    @Pattern(regexp = "\\d+", message = "El campo dni sólo admite caracteres numéricos")
    @Size(max = 8, message = "La cedula debe ser hasta 8 caracteres")
    @NotBlank(message = "Debe especificarse el dni del paciente")
    private String dni;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "La fecha no puede ser anterior al día de hoy")
    @NotNull(message = "Debe especificarse la fecha de ingreso del paciente")
    private LocalDate fechaDeAlta;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "domicilio_id")
    private Domicilio domicilio;

    // Constructor Vacio
    public Paciente() {
    }

    // Constructor sin id
    public Paciente(String nombre, String apellido, String dni, LocalDate fechaDeAlta, Domicilio domicilio) {
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

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    @Override
    public String toString() {
        return "Id: " + id + " - Nombre: " + nombre + " - Apellido: " + apellido + " - DNI: " + dni + " - Fechas de alta: " + fechaDeAlta + " - Domicilio: " + domicilio;
    }
}
