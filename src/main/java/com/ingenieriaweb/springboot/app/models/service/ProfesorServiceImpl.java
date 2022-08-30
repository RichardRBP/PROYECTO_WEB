package com.ingenieriaweb.springboot.app.models.service;

import com.ingenieriaweb.springboot.app.models.dao.*;
import com.ingenieriaweb.springboot.app.models.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProfesorServiceImpl implements IProfesorService {

    //Profesor

    @Autowired
    private IProfesorDao profesorDao;

    @Autowired
    private ICursoDao cursoDao;

    @Autowired
    private ITarifaDao tarifaDao;

    @Autowired
    private IAreaDao areaDao;

    @Autowired
    private IAulaDao aulaDao;

    @Autowired
    private ICarreraDao carreraDao;

    @Autowired
    private IAlumnoDao alumnoDao;



    //Aula

    @Override
    public List<Aula> findByAula(String term) {
        // TODO Auto-generated method stub
        return aulaDao.findByAulaLikeIgnoreCase("%" + term + "%");
    }

    @Override
    public List<Aula> findAllA() {
        // TODO Auto-generated method stub
        return (List<Aula>) aulaDao.findAll();
    }

    @Override
    public Page<Aula> findAllA(Pageable pageable) {
        return aulaDao.findAll(pageable);
    }

    @Override
    public Aula findOneA(Long id) {
        return aulaDao.findById(id).orElse(null);
    }

    @Override
    public void saveAula(Aula aula) {
        aulaDao.save(aula);
    }

    @Override
    public void deleteA(Long id) {
        aulaDao.deleteById(id);
    }

    //Profesor

    @Override
    public List<Profesor> findByApellido(String term) {
        // TODO Auto-generated method stub
        return profesorDao.findByApellidoLikeIgnoreCase("%" + term + "%");
    }

    @Override
    public List<Profesor> findAllP() {
        return (List<Profesor>) profesorDao.findAll();
    }

    @Override
    public Page<Profesor> findAllP(Pageable pageable) {
        return profesorDao.findAll(pageable);
    }

    @Override
    public Profesor findOneP(Long id) {
        return profesorDao.findById(id).orElse(null);
    }

    @Override
    public void saveProfesor(Profesor profesor) {
        profesorDao.save(profesor);
    }

    @Override
    public void deleteP(Long id) {
        profesorDao.deleteById(id);
    }

    //Curso

    @Override
    public List<Curso> findByEtapa(String term) {
        // TODO Auto-generated method stub
        return cursoDao.findByEtapaLikeIgnoreCase("%" + term + "%");

    }

    @Override
    public List<Curso> findAllC() {
        return (List<Curso>) cursoDao.findAll();
    }

    @Override
    public Page<Curso> findAllC(Pageable pageable) {
        return cursoDao.findAll(pageable);
    }

    @Override
    public Curso findOneC(Long id) {
        return cursoDao.findById(id).orElse(null);
    }

    @Override
    public void saveCurso(Curso curso) {
        cursoDao.save(curso);
    }

    @Override
    public void deleteC(Long id) {
        cursoDao.deleteById(id);
    }

    //Tarifa

    @Override
    @Transactional(readOnly = true)
    public List<Tarifa> findByNombre(String term) {
        // TODO Auto-generated method stub
        return tarifaDao.findByNombreLikeIgnoreCase("%" + term + "%");
    }

    @Override
    @Transactional
    public List<Tarifa> findAllT() {
        return (List<Tarifa>) tarifaDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Tarifa> findAllT(Pageable pageable) {
        return tarifaDao.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Tarifa findOneT(Long id) {
        return tarifaDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void saveTarifa(Tarifa tarifa) {
        tarifaDao.save(tarifa);
    }

    @Override
    @Transactional
    public void deleteT(Long id) {
        tarifaDao.deleteById(id);
    }

    //Area

    @Override
    public List<Area> findByArea(String term) {
        // TODO Auto-generated method stub
        return areaDao.findByAreaLikeIgnoreCase("%" + term + "%");
    }

    @Override
    public List<Area> findAllAr() {
        return (List<Area>) areaDao.findAll();
    }

    @Override
    public Page<Area> findAllAr(Pageable pageable) {
        return areaDao.findAll(pageable);
    }

    @Override
    public Area findOneAr(Long id) {
        return areaDao.findById(id).orElse(null);
    }

    @Override
    public void saveArea(Area area) {
        areaDao.save(area);
    }

    @Override
    public void deleteAr(Long id) {
        areaDao.deleteById(id);
    }

    //Carrera

    @Override
    public List<Carrera> findByCarrera(String term) {
        // TODO Auto-generated method stub
        return carreraDao.findByNombreLikeIgnoreCase("%" + term + "%");
    }

    @Override
    public List<Carrera> findAllCar() {
        return (List<Carrera>) carreraDao.findAll();
    }

    @Override
    public Page<Carrera> findAllCar(Pageable pageable) {
        return carreraDao.findAll(pageable);
    }

    @Override
    public Carrera findOneCar(Long id) {
        return carreraDao.findById(id).orElse(null);
    }

    @Override
    public void saveCarrera(Carrera carrera) {
        carreraDao.save(carrera);
    }

    @Override
    public void deleteCar(Long id) {
        carreraDao.deleteById(id);
    }

    //Alumno

    @Override
    public List<Alumno> findByAlumno(String term) {
        // TODO Auto-generated method stub
        return alumnoDao.findByNombresLikeIgnoreCase("%" + term + "%");
    }

    @Override
        public List<Alumno> findByIngresante(String term) {
        // TODO Auto-generated method stub
        return alumnoDao.findByIngresanteLikeIgnoreCase("%" + term + "%");
    }

    @Override
    public List<Alumno> findAllAlu() {
        return (List<Alumno>) alumnoDao.findAll();
    }

    @Override
    public Page<Alumno> findAllAlu(Pageable pageable) {
        return alumnoDao.findAll(pageable);
    }

    @Override
    public Alumno findOneAlu(Long id) {
        return alumnoDao.findById(id).orElse(null);
    }

    @Override
    public void saveAlumno(Alumno alumno) {
        alumnoDao.save(alumno);
    }

    @Override
    public void deleteAlu(Long id) {
        alumnoDao.deleteById(id);
    }






}
