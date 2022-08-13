package com.ingenieriaweb.springboot.app.models.service;

import java.util.List;
import com.ingenieriaweb.springboot.app.models.entity.Rol;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IRolService {
    
    public List<Rol> findAll();

    public Page<Rol> findAll(Pageable pageable);

}
