package com.ingenieriaweb.springboot.app.models.service;

import com.ingenieriaweb.springboot.app.models.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProfesorService {

    // Aula
    public List<Aula> findByAula(String aula);

    public List<Aula> findAllA();

    public Page<Aula> findAllA(Pageable pageable);

    public Aula findOneA(Long id);

    public void saveAula(Aula aula);

    public void deleteA(Long id);

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


    //Area
    public List<Area> findByArea(String term);

    public List<Area> findAllAr();

    public Page<Area> findAllAr(Pageable pageable);

    public Area findOneAr(Long id);

    public void saveArea(Area area);

    public void deleteAr(Long id);

    //Carrera

    public List<Carrera> findByCarrera(String term);

    public List<Carrera> findAllCar();

    public Page<Carrera> findAllCar(Pageable pageable);

    public Carrera findOneCar(Long id);

    public void saveCarrera(Carrera carrera);

    public void deleteCar(Long id);

    //Alumno

    public List<Alumno> findByAlumno(String term);

    public List<Alumno> findAllAlu();

    public Page<Alumno> findAllAlu(Pageable pageable);

    public Alumno findOneAlu(Long id);

    public void saveAlumno(Alumno alumno);

    public void deleteAlu(Long id);

}

