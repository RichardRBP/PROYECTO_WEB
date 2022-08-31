package com.ingenieriaweb.springboot.app.models.dao;

import com.ingenieriaweb.springboot.app.models.entity.MatriculaSimulacro;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IMatriculaSimulacroDao extends PagingAndSortingRepository<MatriculaSimulacro,Long> {
	@Query(value="select * from  simulacro_items as si "
    		+ "inner join matriculas  as m  on si.matricula_id = m.id "
    		+ "inner join alumnos as a on m.alumno_id = a.id  "
    		+ "where a.dni = :dni", nativeQuery=true)
	public List<MatriculaSimulacro> findByAlumno(String dni);
}
