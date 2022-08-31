package com.ingenieriaweb.springboot.app.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ingenieriaweb.springboot.app.models.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
    
    Role findByName(String roleName);

}
