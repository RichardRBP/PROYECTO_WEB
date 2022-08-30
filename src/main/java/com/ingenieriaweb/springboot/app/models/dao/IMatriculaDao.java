package com.ingenieriaweb.springboot.app.models.dao;
import com.ingenieriaweb.springboot.app.models.entity.Matricula;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IMatriculaDao extends PagingAndSortingRepository<Matricula, Long>{
    
    @Query("select m from Matricula m where m.estado = 'RESERVADA' ")
    public List<Matricula> findByEstado();

    
    @Query("select m from Matricula m where m.estado = 'ACEPTADA' ")
    public List<Matricula> findAllAceptadas();

    

}
