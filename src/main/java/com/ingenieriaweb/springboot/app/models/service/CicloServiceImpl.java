package com.ingenieriaweb.springboot.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ingenieriaweb.springboot.app.models.dao.ICicloDao;
import com.ingenieriaweb.springboot.app.models.entity.Ciclo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CicloServiceImpl  implements ICicloService{
    
    @Autowired
    private ICicloDao CicloDao;

    @Override
    public List<Ciclo> findAll() {
        // TODO Auto-generated method stub
        return (List<Ciclo>) CicloDao.findAll();
    }

    

    @Override
    public Page<Ciclo> findAll(Pageable pageable) {
        return CicloDao.findAll(pageable);
    }



    @Override
    public Ciclo findOne(Long id) {
        // TODO Auto-generated method stub
        return CicloDao.findById(id).orElse(null);
    }



    @Override
    public void save(Ciclo ciclo) {
        CicloDao.save(ciclo);
        
    }



    @Override
    public void delete(Long id) {
        CicloDao.deleteById(id);
        
    }

}
