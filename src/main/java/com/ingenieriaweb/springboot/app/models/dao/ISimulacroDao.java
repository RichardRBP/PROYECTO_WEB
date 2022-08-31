package com.ingenieriaweb.springboot.app.models.dao;

import com.ingenieriaweb.springboot.app.models.entity.Simulacro;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ISimulacroDao extends PagingAndSortingRepository<Simulacro, Long>{
    
}
