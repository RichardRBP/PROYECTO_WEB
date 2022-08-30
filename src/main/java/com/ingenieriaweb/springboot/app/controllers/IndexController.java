package com.ingenieriaweb.springboot.app.controllers;

import com.ingenieriaweb.springboot.app.models.entity.Alumno;
import com.ingenieriaweb.springboot.app.models.service.IProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private IProfesorService profesorService;

    @GetMapping({"/login"}) //login
    public String login(Model model) {
        model.addAttribute("login", "BIENVENIDO");
        return "login";
    }

    @GetMapping(value = "/")
    public String listarIngresantes(Model model) {
        String term = "SI";
        List<Alumno> ingresantes = profesorService.findByIngresante(term);
//		print lista de ingresantes in console
//		Verifciacion de ingresantes
        System.out.println("PRUEBA DE SALIDA >>>>>>>>>" + ingresantes);
        model.addAttribute("titulo", "Ingresantes 2022");
        model.addAttribute("ingresantes", ingresantes);
        return "index";
    }

}

