package com.ingenieriaweb.springboot.app.models.service;

import java.util.List;
import com.ingenieriaweb.springboot.app.models.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IRolService {
    
    public List<Role> findAll();

    public Page<Role> findAll(Pageable pageable);

    public Role findOne(Long id);

    public void save(Role name);

    public void delete(Long id);

}
