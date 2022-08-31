package com.ingenieriaweb.springboot.app.security;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ingenieriaweb.springboot.app.models.dao.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService{
    
    private  UserRepository userRepository;


	@Override
    public CustomUserDetails loadUserByUsername(String userName) {
        return userRepository.findByUserName(userName)
                .map(CustomUserDetails::create)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid username or password!"));
    }

}
