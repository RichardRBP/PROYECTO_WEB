package com.ingenieriaweb.springboot.app.models.service;

import com.ingenieriaweb.springboot.app.models.entity.Curso;
import com.ingenieriaweb.springboot.app.models.entity.Profesor;
import com.ingenieriaweb.springboot.app.models.entity.Tarifa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProfesorService {


    //  Profesor
    public List<Profesor> findByApellido(String term);

    public List<Profesor> findAllP();

    public Page<Profesor> findAllP(Pageable pageable);

    public Profesor findOneP(Long id);

    public void saveProfesor(Profesor profesor);

    public void deleteP(Long id);

    // Curso
    public List<Curso> findByEtapa(String term);

    public List<Curso> findAllC();

    public Page<Curso> findAllC(Pageable pageable);

    public Curso findOneC(Long id);

    public void saveCurso(Curso curso);

    public void deleteC(Long id);

    //Tarifa

    public List<Tarifa> findByNombre(String term);

    public List<Tarifa> findAllT();

    public Page<Tarifa> findAllT(Pageable pageable);

    public Tarifa findOneT(Long id);

    public void saveTarifa(Tarifa tarifa);

    public void deleteT(Long id);




}
