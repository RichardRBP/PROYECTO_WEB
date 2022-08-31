package com.ingenieriaweb.springboot.app.models.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import com.ingenieriaweb.springboot.app.models.entity.User;

@NoRepositoryBean
public interface CommonUserRepository<T extends User> extends JpaRepository<T, Long> {
    
    Optional<T> findByUserName(String userName);

    @Query("select t from #{#entityName} t inner join t.roles r where r.name in :roleName")
    List<T> findByRoleName(@Param("roleName") String roleName);
    
}
