package com.ingenieriaweb.springboot.app.models.dao;

import com.ingenieriaweb.springboot.app.models.entity.Area;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface IAreaDao extends PagingAndSortingRepository<Area, Long> {
   
    @Query("select p from Area p where p.area like %?1%")
    public List<Area> findByArea(String term);
    
    public List<Area> findByAreaLikeIgnoreCase(String term);
}