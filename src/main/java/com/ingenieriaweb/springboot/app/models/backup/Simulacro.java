package com.ingenieriaweb.springboot.app.models.backup;

public class Simulacro {

	private String Nombre;
	private Integer Numero;
	private String Fecha;
	private Integer NumeroPreguntas;

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public Integer getNumero() {
		return Numero;
	}

	public void setNumero(Integer numero) {
		Numero = numero;
	}

	public String getFecha() {
		return Fecha;
	}

	public void setFecha(String fecha) {
		Fecha = fecha;
	}

	public Integer getNumeroPreguntas() {
		return NumeroPreguntas;
	}

	public void setNumeroPreguntas(Integer numeroPreguntas) {
		NumeroPreguntas = numeroPreguntas;
	}

}
