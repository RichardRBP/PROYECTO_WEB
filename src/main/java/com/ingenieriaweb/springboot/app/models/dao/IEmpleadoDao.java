package com.ingenieriaweb.springboot.app.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ingenieriaweb.springboot.app.models.entity.Empleado;

public interface IEmpleadoDao extends PagingAndSortingRepository<Empleado, Long>{
    
}
