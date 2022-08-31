package com.ingenieriaweb.springboot.app.models.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "matriculasimulacro")
public class MatriculaSimulacro implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private Integer pcorrecta;

    @NotEmpty
    private Integer pincorrecta;

    @NotNull
    private Integer nota;

    @ManyToOne(fetch = FetchType.LAZY)
    private Matricula matricula;


	@ManyToOne(fetch = FetchType.LAZY)
	private Simulacro simulacro;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPcorrecta() {
        return pcorrecta;
    }

    public void setPcorrecta(Integer pcorrecta) {
        this.pcorrecta = pcorrecta;
    }

    public Integer getPincorrecta() {
        return pincorrecta;
    }

    public void setPincorrecta(Integer pincorrecta) {
        this.pincorrecta = pincorrecta;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public Simulacro getSimulacro() {
        return simulacro;
    }

    public void setSimulacro(Simulacro simulacro) {
        this.simulacro = simulacro;
    }

}
