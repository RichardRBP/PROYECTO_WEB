package com.ingenieriaweb.springboot.app.models.dao;

import com.ingenieriaweb.springboot.app.models.entity.TipoAlumno;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ITipoAlumnoDao extends PagingAndSortingRepository<TipoAlumno, Long>{
    
    @Query("select t from TipoAlumno t where t.nombreTipo = ?1")
    TipoAlumno findByNombreTipo(String nombreTipo);
    
}