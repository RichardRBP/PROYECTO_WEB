package com.ingenieriaweb.springboot.app.models.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tipoAlumnos")
public class TipoAlumno implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String nombreTipo;

    @JsonManagedReference
    @OneToMany(mappedBy = "tipoAlumno", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Alumno> alumnos;

    @JsonManagedReference
    @OneToMany(mappedBy = "tipoAlumno", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Tarifa> tarifas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    public List<Tarifa> getTarifas() {
        return tarifas;
    }

    public void setTarifas(List<Tarifa> tarifas) {
        this.tarifas = tarifas;
    }
}