package com.ingenieriaweb.springboot.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ingenieriaweb.springboot.app.models.dao.IAlumnoDao; 
import com.ingenieriaweb.springboot.app.models.entity.Alumno;

import org.springframework.stereotype.Service; 

@Service
public class AlumnoServiceImpl  implements IAlumnoService{
    
    @Autowired
    private IAlumnoDao AlumnoDao;

    @Override
    public List<Alumno> findAll() {
        // TODO Auto-generated method stub
        return (List<Alumno>) AlumnoDao.findAll();
    }

    

    @Override
    public Page<Alumno> findAll(Pageable pageable) {
        return AlumnoDao.findAll(pageable);
    }



    @Override
    public Alumno findOne(Long id) {
        // TODO Auto-generated method stub
        return AlumnoDao.findById(id).orElse(null);
    }



    @Override
    public void save(Alumno alumno) {
        AlumnoDao.save(alumno);
        
    }



    @Override
    public void delete(Long id) {
        AlumnoDao.deleteById(id);
        
    }

}
