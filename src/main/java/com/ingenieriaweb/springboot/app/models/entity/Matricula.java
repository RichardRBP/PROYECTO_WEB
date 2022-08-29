package com.ingenieriaweb.springboot.app.models.entity;


import javax.persistence.*; 
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

@Entity
@Table(name = "matriculas")
public class Matricula implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @NotNull
    private Integer codigoOperacion;

    @NotEmpty
    private String fechaOperacion;

    @NotEmpty
    private String banco;

    @NotEmpty
    private String monto;
 
     
    private String foto;

    @NotEmpty
    private String estado;

    @NotEmpty
    private String turno;

    
    @ManyToOne(fetch = FetchType.LAZY)
    private Tarifa tarifa;
 
    @ManyToOne(fetch = FetchType.LAZY)
    private Alumno alumno;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCodigoOperacion() {
        return codigoOperacion;
    }

    public void setCodigoOperacion(Integer codigoOperacion) {
        this.codigoOperacion = codigoOperacion;
    }

    public String getFechaOperacion() {
        return fechaOperacion;
    }

    public void setFechaOperacion(String fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public Tarifa getTarifa() {
        return tarifa;
    }

    public void setTarifa(Tarifa tarifa) {
        this.tarifa = tarifa;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }


}
