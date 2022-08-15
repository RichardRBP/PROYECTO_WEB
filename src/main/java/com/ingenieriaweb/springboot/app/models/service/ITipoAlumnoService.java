package com.ingenieriaweb.springboot.app.models.service;


import java.util.List;
import com.ingenieriaweb.springboot.app.models.entity.TipoAlumno;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ITipoAlumnoService {

    public List<TipoAlumno> findAll();
    
    public Page<TipoAlumno> findAll(Pageable pageable);
    
    public TipoAlumno findOne(Long id);
    
    public void save(TipoAlumno tipoAlumno);
    
    public void delete(Long id);
    
    
}
