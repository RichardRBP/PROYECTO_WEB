package com.ingenieriaweb.springboot.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ingenieriaweb.springboot.app.models.dao.IEmpleadoDao;
import com.ingenieriaweb.springboot.app.models.entity.Empleado;

import org.springframework.stereotype.Service;


@Service
public class EmpleadoServiceImpl implements IEmpleadoService{
    
    @Autowired
    private IEmpleadoDao EmpleadoDao;

    @Override
    public List<Empleado> findAll() {
        // TODO Auto-generated method stub
        return (List<Empleado>) EmpleadoDao.findAll();
    }

    

    @Override
    public Page<Empleado> findAll(Pageable pageable) {
        return EmpleadoDao.findAll(pageable);
    }



    @Override
    public Empleado findOne(Long id) {
        // TODO Auto-generated method stub
        return EmpleadoDao.findById(id).orElse(null);
    }



    @Override
    public void save(Empleado empleado) {
        EmpleadoDao.save(empleado);
        
    }



    @Override
    public void delete(Long id) {
        EmpleadoDao.deleteById(id);
        
    }

}
