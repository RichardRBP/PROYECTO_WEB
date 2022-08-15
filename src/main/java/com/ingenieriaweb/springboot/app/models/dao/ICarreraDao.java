package com.ingenieriaweb.springboot.app.models.dao;

import com.ingenieriaweb.springboot.app.models.entity.Carrera;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ICarreraDao extends PagingAndSortingRepository<Carrera, Long> {

    public List<Carrera> findByNombre(String term);

    public List<Carrera> findByNombreLikeIgnoreCase(String term);

}
