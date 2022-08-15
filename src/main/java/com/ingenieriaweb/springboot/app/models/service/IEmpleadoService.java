package com.ingenieriaweb.springboot.app.models.service;

import java.util.List;
import com.ingenieriaweb.springboot.app.models.entity.Empleado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

 
public interface IEmpleadoService {
    
    public List<Empleado> findAll();

    public Page<Empleado> findAll(Pageable pageable);

    public Empleado findOne(Long id);

    public void save(Empleado empleado);

    public void delete(Long id);


}
