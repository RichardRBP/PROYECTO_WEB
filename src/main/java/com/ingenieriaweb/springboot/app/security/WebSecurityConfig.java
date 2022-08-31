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
    String[] resources = new String[]{
    		"/index","/","/images/**","/css/**","/csss/**","/js/**","/login/**","/**/uploads/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(resources).permitAll()
                .antMatchers("/index").hasAnyRole("ADMIN", "ALUMNO")
                .antMatchers("/admin").hasAnyRole("ADMIN")
                .antMatchers("/alumno/**").hasAnyRole("ADMIN","ALUMNO")
                .antMatchers("/area/**").hasAnyRole("ADMIN")
                .antMatchers("/aula/**").hasAnyRole("ADMIN")
                .antMatchers("/carrera/**").hasAnyRole("ADMIN")
                .antMatchers("/ciclo/**").hasAnyRole("ADMIN")
                .antMatchers("/curso/**").hasAnyRole("ADMIN")
                .antMatchers("/empleado/**").hasAnyRole("ADMIN")
                .antMatchers("/matricula/**").hasAnyRole("ADMIN","ALUMNO")
                .antMatchers("/profesor/**").hasAnyRole("ADMIN")
                .antMatchers("/tarifa/**").hasAnyRole("ADMIN")
                .antMatchers("/tipoAlumno/**").hasAnyRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login") // nuestro login
                .loginProcessingUrl("/admin/perform_login") // si quiero lo borro
                .defaultSuccessUrl("/admin/")// a donde chucha quiere que vaya
                .failureUrl("/login?error=true")
                .permitAll()
                .and()
                .logout().logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .permitAll()
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
