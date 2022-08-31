package com.ingenieriaweb.springboot.app.models.dao;
 
import com.ingenieriaweb.springboot.app.models.entity.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface IRolDao  extends PagingAndSortingRepository<Role, Long> {
    
}
