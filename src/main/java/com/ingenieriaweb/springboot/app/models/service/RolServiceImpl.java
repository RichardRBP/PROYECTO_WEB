package com.ingenieriaweb.springboot.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ingenieriaweb.springboot.app.models.dao.IRolDao;
import com.ingenieriaweb.springboot.app.models.entity.Rol;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


public class RolServiceImpl  implements IRolService{
    
    @Autowired
    private IRolDao rolDao;

    @Override
    public List<Rol> findAll() {
        // TODO Auto-generated method stub
        return (List<Rol>) rolDao.findAll();
    }

    

    @Override
    public Page<Rol> findAll(Pageable pageable) {
        return rolDao.findAll(pageable);
    }

}
