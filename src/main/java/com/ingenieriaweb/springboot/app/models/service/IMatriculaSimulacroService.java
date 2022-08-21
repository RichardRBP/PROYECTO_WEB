package com.ingenieriaweb.springboot.app.models.service;

import java.util.List;
import com.ingenieriaweb.springboot.app.models.entity.MatriculaSimulacro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IMatriculaSimulacroService {

    public MatriculaSimulacro findOne(Long id);

}
