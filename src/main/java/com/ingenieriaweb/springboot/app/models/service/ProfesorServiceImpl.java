package com.ingenieriaweb.springboot.app.models.service;

import com.ingenieriaweb.springboot.app.models.dao.IAulaDao;
import com.ingenieriaweb.springboot.app.models.dao.ICursoDao;
import com.ingenieriaweb.springboot.app.models.dao.IProfesorDao;
import com.ingenieriaweb.springboot.app.models.dao.ITarifaDao;
import com.ingenieriaweb.springboot.app.models.entity.Aula;
import com.ingenieriaweb.springboot.app.models.entity.Curso;
import com.ingenieriaweb.springboot.app.models.entity.Profesor;
import com.ingenieriaweb.springboot.app.models.entity.Tarifa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    private IAulaDao aulaDao;

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
    public List<Tarifa> findByNombre(String term) {
        // TODO Auto-generated method stub
        return tarifaDao.findByNombreLikeIgnoreCase("%" + term + "%");
    }

    @Override
    public List<Tarifa> findAllT() {
        return (List<Tarifa>) tarifaDao.findAll();
    }

    @Override
    public Page<Tarifa> findAllT(Pageable pageable) {
        return tarifaDao.findAll(pageable);
    }

    @Override
    public Tarifa findOneT(Long id) {
        return tarifaDao.findById(id).orElse(null);
    }

    @Override
    public void saveTarifa(Tarifa tarifa) {
        tarifaDao.save(tarifa);
    }

    @Override
    public void deleteT(Long id) {
        tarifaDao.deleteById(id);
    }

    //Area


}
