package com.ingenieriaweb.springboot.app.models.dao;

import com.ingenieriaweb.springboot.app.models.entity.Aula;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface IAulaDao extends PagingAndSortingRepository<Aula, Long> {

    @Query("select p from Aula p where p.aula like %?1%")
    public List<Aula> findByAula(String aula);
    
    public List<Aula> findByAulaLikeIgnoreCase(String aula);

}
