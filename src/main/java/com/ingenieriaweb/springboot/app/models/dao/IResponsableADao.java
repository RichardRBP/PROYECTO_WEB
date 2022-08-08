package com.ingenieriaweb.springboot.app.models.dao;

import com.ingenieriaweb.springboot.app.models.entity.ResponsableAlumno;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface IResponsableADao extends PagingAndSortingRepository<ResponsableAlumno, Long> {

    @Query("select p from ResponsableAlumno p where p.apellido like %?1%")
    public List<ResponsableAlumno> findByApellido(String term);

    public List<ResponsableAlumno> findByApellidoLikeIgnoreCase(String term);

}
