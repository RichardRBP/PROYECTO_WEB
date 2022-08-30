package com.ingenieriaweb.springboot.app.models.service;

import java.util.List;
import com.ingenieriaweb.springboot.app.models.entity.Matricula;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IMatriculaService {
    
    public List<Matricula> findAll();

	public List<Matricula> findAllAceptadas();

    public Page<Matricula> findAll(Pageable pageable);

    public List<Matricula> findByEstado();

    public Matricula findOne(Long id);

    public void save(Matricula matricula);

    public void delete(Long id);
}
