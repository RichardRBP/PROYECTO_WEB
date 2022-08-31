package com.ingenieriaweb.springboot.app.models.service;

import java.util.Collection;
import java.util.List;

import com.ingenieriaweb.springboot.app.models.ChangePasswordForm;
import com.ingenieriaweb.springboot.app.models.UserForm;
import com.ingenieriaweb.springboot.app.models.entity.Role;
import com.ingenieriaweb.springboot.app.models.entity.User;


public interface UserService {
    
     /** User **/
    boolean userExists(String userName);

    User getUserById(Long userId);

    User getUserByUsername(String userName);

    List<User> getUsersByRoleName(String roleName);

    List<User> getAllUsers();

    void deleteUserById(Long userId);

    void updateUserPassword(ChangePasswordForm passwordChangeForm);
    
    // guiarme de dentista para el alumno 


    
}
