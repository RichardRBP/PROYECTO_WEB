package com.ingenieriaweb.springboot.app.models.backup;

public class Horario {
	private String dia;
	private String hora;
	private Integer dia_indice;
	private Integer hora_indice;

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public Integer getDia_indice() {
		return dia_indice;
	}

	public void setDia_indice(Integer dia_indice) {
		this.dia_indice = dia_indice;
	}

	public Integer getHora_indice() {
		return hora_indice;
	}

	public void setHora_indice(Integer hora_indice) {
		this.hora_indice = hora_indice;
	}

}
