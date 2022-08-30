package com.ingenieriaweb.springboot.app.controllers;

import com.ingenieriaweb.springboot.app.models.entity.Alumno;
import com.ingenieriaweb.springboot.app.models.service.IProfesorService;
import com.ingenieriaweb.springboot.app.models.service.IUploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.MalformedURLException;
import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private IProfesorService profesorService;

    @Autowired
    private IUploadFileService uploadFileService;

    @GetMapping({"/login"}) //login
    public String login(Model model) {
        model.addAttribute("login", "BIENVENIDO");
        return "login";
    }

    @GetMapping(value = "/uploads/{filename:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String filename) {

        Resource recurso = null;

        try {
            recurso = uploadFileService.load(filename);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
                .body(recurso);
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

