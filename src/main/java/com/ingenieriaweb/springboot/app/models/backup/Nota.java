package com.ingenieriaweb.springboot.app.models.backup;

public class Nota {

	private Simulacro simulacro;
//	private Alumno alumno;
	private Double Nota;

	public Simulacro getSimulacro() {
		return simulacro;
	}

	public void setSimulacro(Simulacro simulacro) {
		this.simulacro = simulacro;
	}

//	public Alumno getAlumno() {
//		return alumno;
//	}
//	public void setAlumno(Alumno alumno) {
//		this.alumno = alumno;
//	}
	public Double getNota() {
		return Nota;
	}

	public void setNota(Double nota) {
		Nota = nota;
	}

}
