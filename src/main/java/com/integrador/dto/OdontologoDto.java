package com.integrador.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OdontologoDto {

    private Long id;
    private String nombre;
    private String apellido;
    private String matricula;

    public OdontologoDto() {
    }

    public OdontologoDto(Long id, String nombre, String apellido, String matricula) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = matricula;
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

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public String toString() {
        return
                "id: " + id +
                        ", nombre: '" + nombre + '\'' +
                        ", apellido: '" + apellido + '\'' +
                        ", matricula: '" + matricula + '\'';
    }
}
