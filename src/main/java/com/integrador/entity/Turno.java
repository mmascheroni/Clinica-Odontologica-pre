package com.integrador.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "TURNOS")
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "paciente_id", nullable = false)
    @NotNull(message = "El paciente no puede ser nulo")
    private Paciente paciente;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "odontologo_id", nullable = false)
    @NotNull(message = "El odontologo no puede ser nulo")
    private Odontologo odontologo;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @FutureOrPresent(message = "La fecha no puede ser anterior al día de hoy")
    @NotNull(message = "Debe especificarse la fecha y hora del turno")
    private LocalDateTime fechaYHora;

    public Turno() {
    }

    public Turno(Paciente paciente, Odontologo odontologo, LocalDateTime fechaYHora) {
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fechaYHora = fechaYHora;
    }

    public Long getId() {
        return id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public LocalDateTime getFecha() {
        return fechaYHora;
    }

    public void setFecha(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }

    @Override
    public String toString() {
        return "Turno{" +
                "id=" + id +
                ", paciente=" + paciente +
                ", odontologo=" + odontologo +
                ", fechaYHora=" + fechaYHora +
                '}';
    }
}
