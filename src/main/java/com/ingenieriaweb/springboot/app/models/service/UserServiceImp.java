package com.ingenieriaweb.springboot.app.models.service;

import org.springframework.stereotype.Service;

import com.ingenieriaweb.springboot.app.models.ChangePasswordForm;
import com.ingenieriaweb.springboot.app.models.UserForm;
import com.ingenieriaweb.springboot.app.models.dao.RoleRepository;
import com.ingenieriaweb.springboot.app.models.dao.UserRepository;
import com.ingenieriaweb.springboot.app.models.entity.Role;
import com.ingenieriaweb.springboot.app.models.entity.User;

import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserServiceImp implements UserService {
    

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImp(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean userExists(String userName) { return userRepository.findByUserName(userName).isPresent(); }

    @Override
    @PreAuthorize("#userId == principal.id")
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }


    @Override
    public User getUserByUsername(String userName) {
        return userRepository.findByUserName(userName).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }

    @Override
    public List<User> getUsersByRoleName(String roleName) { return userRepository.findByRoleName(roleName); }

    @Override
    public List<User> getAllUsers() { return userRepository.findAll(); }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUserById(Long userId) { userRepository.deleteById(userId); }



    @Override
    @PreAuthorize("#passwordChangeForm.id == principal.id")
    public void updateUserPassword(ChangePasswordForm passwordChangeForm) {
        User user = userRepository.getReferenceById(passwordChangeForm.getId());
        user.setPassword(passwordEncoder.encode(passwordChangeForm.getPassword()));
        userRepository.save(user);
    }


    
}
