package com.ingenieriaweb.springboot.app.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "simulacros")
public class Simulacro implements Serializable{

	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String nombre;
    
    @NotEmpty
    private Date Fecha;

    @JsonManagedReference
    @OneToMany(mappedBy = "simulacro", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MatriculaSimulacro> simulacro;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFecha() {
		return Fecha;
	}

	public void setFecha(Date fecha) {
		Fecha = fecha;
	}

	public List<MatriculaSimulacro> getMatriculas_simulacro() {
		return simulacro;
	}

	public void setMatriculas_simulacro(List<MatriculaSimulacro> matriculas_simulacro) {
		this.simulacro = matriculas_simulacro;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    

    
}
