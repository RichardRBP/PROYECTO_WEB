package com.ingenieriaweb.springboot.app.models.dao;

import com.ingenieriaweb.springboot.app.models.entity.Curso;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ICursoDao extends PagingAndSortingRepository<Curso, Long> {

    @Query("select p from Curso p where p.etapa like %?1%")
    public List<Curso> findByEtapa(String term);

    public List<Curso> findByEtapaLikeIgnoreCase(String term);

}
