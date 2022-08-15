package com.ingenieriaweb.springboot.app.controllers;


import com.ingenieriaweb.springboot.app.models.entity.Curso;
import com.ingenieriaweb.springboot.app.models.service.IProfesorService;
import com.ingenieriaweb.springboot.app.models.service.IUploadFileService;
import com.ingenieriaweb.springboot.app.paginator.PageRender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/curso")
@SessionAttributes("curso")
public class CursoController {

    @Autowired
    private IProfesorService profesorService;

    @Autowired
    private IUploadFileService uploadFileService;

    @GetMapping(value = "/listar")
    public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

        Pageable pageRequest = PageRequest.of(page, 5);

        Page<Curso> curso = profesorService.findAllC(pageRequest);

        PageRender<Curso> pageRender = new PageRender<Curso>("/curso/listar", curso);
        model.addAttribute("titulo", "Listado de cursos");
        model.addAttribute("cursos", curso);
        model.addAttribute("page", pageRender);
        return "curso/listar";
    }

    @GetMapping(value = "/form")
    public String crear(Map<String, Object> model) {

        Curso curso = new Curso();
        model.put("curso", curso);
        model.put("profesores", profesorService.findAllP());
        model.put("titulo", "Formulario de Curso");
        return "curso/form";
    }

    @GetMapping(value = "/form/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

        Curso curso = null;

        if (id > 0) {
            curso = profesorService.findOneC(id);
            if (curso == null) {
                flash.addFlashAttribute("error", "El ID del curso no existe en la BBDD!");
                return "redirect:/curso/listar";
            }
        } else {
            flash.addFlashAttribute("error", "El ID del curso no puede ser cero!");
            return "redirect:/curso/listar";
        }
        model.put("curso", curso);
        model.put("profesores", profesorService.findAllP());
        model.put("titulo", "Editar curso");
        return "curso/form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String guardar(@Valid Curso curso, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Curso");
            model.addAttribute("profesores", profesorService.findAllP());
            return "/curso/form";
        }
        String mensajeFlash = (curso.getId() != null) ? "Curso editado con éxito!" : "Curso creado con éxito!";
        profesorService.saveCurso(curso);
        status.setComplete();
        flash.addFlashAttribute("success", mensajeFlash);
        return "redirect:/curso/listar";
    }

    @RequestMapping(value = "/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
        if (id > 0) {
            profesorService.deleteC(id);
            flash.addFlashAttribute("success", "Curso eliminado con éxito!");
        }
        return "redirect:/curso/listar";
    }


}
