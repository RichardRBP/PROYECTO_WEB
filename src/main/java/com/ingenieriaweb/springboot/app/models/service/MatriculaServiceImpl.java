package com.ingenieriaweb.springboot.app.models.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ingenieriaweb.springboot.app.models.dao.IMatriculaDao;
import com.ingenieriaweb.springboot.app.models.dao.IMatriculaDao;
import com.ingenieriaweb.springboot.app.models.entity.Matricula;
import com.ingenieriaweb.springboot.app.models.entity.Matricula;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MatriculaServiceImpl implements IMatriculaService {
    @Autowired
    private IMatriculaDao MatriculaDao;

    @Override
    public List<Matricula> findAll() {
        // TODO Auto-generated method stub
        return  (List<Matricula>) MatriculaDao.findAll();
    }

    @Override
    public Page<Matricula> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
    return MatriculaDao.findAll(pageable);
      
    }

    @Override
    public Matricula findOne(Long id) {
        // TODO Auto-generated method stub
         return MatriculaDao.findById(id).orElse(null);
    }

    @Override
    public void save(Matricula matricula) {
        // TODO Auto-generated method stub
        MatriculaDao.save(matricula);
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        MatriculaDao.deleteById(id);
    }

    @Override
    public List<Matricula> findByEstado() {
        // TODO Auto-generated method stub
        return MatriculaDao.findByEstado();
    }

	@Override
	public List<Matricula> findAllAceptadas() {
		// TODO Auto-generated method stub
		 return  (List<Matricula>) MatriculaDao.findAllAceptadas();
	}


    


}
