package com.ingenieriaweb.springboot.app.models.service;

import java.util.List;
import com.ingenieriaweb.springboot.app.models.entity.MatriculaSimulacro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IMatriculaSimulacroService {

    public List<MatriculaSimulacro> findAll();

    public Page<MatriculaSimulacro> findAll(Pageable pageable);

    public MatriculaSimulacro findOne(Long id);

    public void save(MatriculaSimulacro matriculaSimulacro);

    public void delete(Long id);

}
