package com.ingenieriaweb.springboot.app.models.service;


import java.util.List;
import com.ingenieriaweb.springboot.app.models.entity.Ciclo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ICicloService {
    
    
    public List<Ciclo> findAll();

    public Page<Ciclo> findAll(Pageable pageable);

    public Ciclo findOne(Long id);

    public void save(Ciclo ciclo);

    public void delete(Long id);

}
