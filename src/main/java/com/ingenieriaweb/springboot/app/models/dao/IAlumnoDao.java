package com.ingenieriaweb.springboot.app.models.dao;

import com.ingenieriaweb.springboot.app.models.entity.Alumno;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface IAlumnoDao extends PagingAndSortingRepository<Alumno, Long> {

    @Query("select p from Alumno p where p.nombres like %?1%")
    public List<Alumno> findByNombres(String term);

    public List<Alumno> findByNombresLikeIgnoreCase(String term);

    public List<Alumno> findByIngresanteLikeIgnoreCase(String term);

   // @Query("select p from Alumno p where p.dni = ?1")  
	public Optional<Alumno> findByDni(String dni);

}
