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
    public List<MatriculaSimulacro> findAll() {
        // TODO Auto-generated method stub
        return  (List<MatriculaSimulacro>) MatriculaSimulacroDao.findAll();
    }

    @Override
    public Page<MatriculaSimulacro> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return MatriculaSimulacroDao.findAll(pageable);
    }

    @Override
    public MatriculaSimulacro findOne(Long id) {
        return MatriculaSimulacroDao.findById(id).orElse(null);
    }

    @Override
    public void save(MatriculaSimulacro matriculasimulacro) {
        MatriculaSimulacroDao.save(matriculasimulacro);
    }

    @Override
    public void delete(Long id) {
        MatriculaSimulacroDao.deleteById(id);
    }

}
