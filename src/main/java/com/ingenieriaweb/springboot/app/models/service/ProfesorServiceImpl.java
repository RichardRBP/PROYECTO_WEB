package com.ingenieriaweb.springboot.app.models.service;

import com.ingenieriaweb.springboot.app.models.dao.ICursoDao;
import com.ingenieriaweb.springboot.app.models.dao.IProfesorDao;
import com.ingenieriaweb.springboot.app.models.dao.ITarifaDao;
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
    private IAreaDao areaDao;


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

    @Override
    public List<Area> findByArea(String term) {
        // TODO Auto-generated method stub
        return areaDao.findByAreaLikeIgnoreCase("%" + term + "%");
    }

    @Override
    public List<Area> findAllA() {
        return (List<Area>) areaDao.findAll();
    }

    @Override
    public Page<Area> findAllA(Pageable pageable) {
        return areaDao.findAll(pageable);
    }

    @Override
    public Area findOneA(Long id) {
        return areaDao.findById(id).orElse(null);
    }

    @Override
    public void saveArea(Area area) {
        areaDao.save(area);
    }

    @Override
    public void deleteA(Long id) {
        areaDao.deleteById(id);
    }


}
