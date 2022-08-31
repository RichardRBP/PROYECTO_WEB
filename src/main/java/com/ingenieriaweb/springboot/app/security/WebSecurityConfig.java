package com.ingenieriaweb.springboot.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    
    private final CustomUserDetailsService customUserDetailsService;

    private final PasswordEncoder passwordEncoder;

    public WebSecurityConfig(CustomUserDetailsService customUserDetailsService,  PasswordEncoder passwordEncoder) {
        this.customUserDetailsService = customUserDetailsService;
        
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/index").hasAnyRole("ADMIN", "ALUMNO")
                .antMatchers("//**").hasAnyRole("CUSTOMER", "DENTIST", "ADMIN")
                .antMatchers("/customers/all").hasRole("ADMIN")
                .antMatchers("/dentists/new").hasRole("ADMIN")
                //.antMatchers("/invoices/all").hasRole("ADMIN")
                .antMatchers("/dentists/all").hasRole("ADMIN")
                .antMatchers("/customers/**").hasAnyRole("CUSTOMER", "ADMIN")
                .antMatchers("/dentists/availability/**").hasRole("DENTIST")
                .antMatchers("/dentists/**").hasAnyRole("DENTIST", "ADMIN")
                .antMatchers("/works/**").hasRole("ADMIN")
                //.antMatchers("/exchange/**").hasRole("CUSTOMER")
                .antMatchers("/appointment/new/**").hasRole("CUSTOMER")
                .antMatchers("/appointment/**").hasAnyRole("CUSTOMER", "DENTIST", "ADMIN")
                //.antMatchers("/invoices/**").hasAnyRole("CUSTOMER", "DENTIST", "ADMIN")
                .and()
                .formLogin()
                .loginPage("/login") // nuestro login
                .loginProcessingUrl("/admin/perform_login") // si quiero lo borro
           
                .defaultSuccessUrl("/index/")// a donde chucha quiere que vaya
                .permitAll()
                .and()
                .logout().logoutUrl("/admin/perform_logout")// a donde se sale
                .and()
                .exceptionHandling().accessDeniedPage("/admin/access-denied");
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(customUserDetailsService);
        auth.setPasswordEncoder(passwordEncoder);
        return auth;
    }

}
