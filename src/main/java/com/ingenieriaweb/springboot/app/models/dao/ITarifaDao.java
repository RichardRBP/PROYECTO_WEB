package com.ingenieriaweb.springboot.app.models.dao;

import com.ingenieriaweb.springboot.app.models.entity.Tarifa;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ITarifaDao extends PagingAndSortingRepository<Tarifa, Long> {

    @Query("select p from Tarifa p where p.nombre like %?1%")
    public List<Tarifa> findByNombre(String term);

    public List<Tarifa> findByNombreLikeIgnoreCase(String term);

}
