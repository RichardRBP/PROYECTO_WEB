package com.ingenieriaweb.springboot.app.models.service;


import java.util.List;
import com.ingenieriaweb.springboot.app.models.entity.Alumno;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IAlumnoService {
    
    
    public List<Alumno> findAll();

    public Page<Alumno> findAll(Pageable pageable);

    public Alumno findOne(Long id);

    public void save(Alumno alumno);

    public void delete(Long id);

    public Alumno findByDni(String dni);
}