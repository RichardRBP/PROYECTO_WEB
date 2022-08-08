package com.ingenieriaweb.springboot.app.models.dao;

import com.ingenieriaweb.springboot.app.models.entity.Profesor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface IProfesorDao extends PagingAndSortingRepository<Profesor, Long> {

    @Query("select p from Profesor p where p.apellido like %?1%")
    public List<Profesor> findByApellido(String term);

    public List<Profesor> findByApellidoLikeIgnoreCase(String term);

}
