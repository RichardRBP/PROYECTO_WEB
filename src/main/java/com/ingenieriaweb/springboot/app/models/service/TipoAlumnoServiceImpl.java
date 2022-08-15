package com.ingenieriaweb.springboot.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ingenieriaweb.springboot.app.models.dao.ITipoAlumnoDao;
import com.ingenieriaweb.springboot.app.models.entity.TipoAlumno;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TipoAlumnoServiceImpl implements ITipoAlumnoService {
    
    @Autowired
    private ITipoAlumnoDao TipoAlumnoDao;
    
    @Override
    public List<TipoAlumno> findAll() {
        // TODO Auto-generated method stub
        return (List<TipoAlumno>) TipoAlumnoDao.findAll();
    }
    
    @Override
    public Page<TipoAlumno> findAll(Pageable pageable) {
        return TipoAlumnoDao.findAll(pageable);
    }
    
    @Override
    public TipoAlumno findOne(Long id) {
        // TODO Auto-generated method stub
        return TipoAlumnoDao.findById(id).orElse(null);
    }
    
    @Override
    public void save(TipoAlumno tipoAlumno) {
        TipoAlumnoDao.save(tipoAlumno);
        
    }
    
    @Override
    public void delete(Long id) {
        TipoAlumnoDao.deleteById(id);
        
    }
    
}
    
