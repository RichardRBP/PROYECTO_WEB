package com.ingenieriaweb.springboot.app.models.service;
import java.util.List;

import com.ingenieriaweb.springboot.app.models.entity.MatriculaSimulacro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.ingenieriaweb.springboot.app.models.dao.IMatriculaSimulacroDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class MatriculaSimulacroServiceImpl implements IMatriculaSimulacroService {
    @Autowired
    private IMatriculaService matriculaService;

    @Override
    public List<MatriculaSimulacro> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Page<MatriculaSimulacro> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public MatriculaSimulacro findOne(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void save(MatriculaSimulacro matriculaSimulacro) {
        // TODO Auto-generated method stub
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
    }

}
