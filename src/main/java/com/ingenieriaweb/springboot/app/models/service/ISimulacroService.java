package com.ingenieriaweb.springboot.app.models.service;

import com.ingenieriaweb.springboot.app.models.entity.Simulacro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ISimulacroService {
    
    
    public List<Simulacro> findAll();

    public Page<Simulacro> findAll(Pageable pageable);

    public Simulacro findOne(Long id);

    public void save(Simulacro simulacro);

    public void delete(Long id);

}
