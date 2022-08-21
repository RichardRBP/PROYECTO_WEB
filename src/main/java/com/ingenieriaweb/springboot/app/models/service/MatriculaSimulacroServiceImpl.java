package com.ingenieriaweb.springboot.app.models.service;
import java.util.List;

import com.ingenieriaweb.springboot.app.models.entity.MatriculaSimulacro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.ingenieriaweb.springboot.app.models.dao.IMatriculaSimulacroDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MatriculaSimulacroServiceImpl implements IMatriculaSimulacroService {

    @Autowired
    private IMatriculaSimulacroDao MatriculaSimulacroDao;

    @Override
    @Transactional(readOnly = true)
    public MatriculaSimulacro findOne(Long id) {
        return MatriculaSimulacroDao.findById(id).orElse(null);
    }

}
