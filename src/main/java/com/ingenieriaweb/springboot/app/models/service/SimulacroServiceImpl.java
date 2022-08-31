package com.ingenieriaweb.springboot.app.models.service;

import com.ingenieriaweb.springboot.app.models.dao.ISimulacroDao;
import com.ingenieriaweb.springboot.app.models.entity.Simulacro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimulacroServiceImpl implements ISimulacroService{
    
    @Autowired
    private ISimulacroDao SimulacroDao;

    @Override
    public List<Simulacro> findAll() {
        // TODO Auto-generated method stub
        return (List<Simulacro>) SimulacroDao.findAll();
    }

    @Override
    public Page<Simulacro> findAll(Pageable pageable) {
        return SimulacroDao.findAll(pageable);
    }

    @Override
    public Simulacro findOne(Long id) {
        // TODO Auto-generated method stub
        return SimulacroDao.findById(id).orElse(null);
    }

    @Override
    public void save(Simulacro simulacro) {
        SimulacroDao.save(simulacro);
    }

    @Override
    public void delete(Long id) {
        SimulacroDao.deleteById(id);
    }

}
