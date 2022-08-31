package com.ingenieriaweb.springboot.app.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import com.ingenieriaweb.springboot.app.models.entity.*;
import com.ingenieriaweb.springboot.app.models.service.ISimulacroService;
import com.ingenieriaweb.springboot.app.models.service.IMatriculaService;
import com.ingenieriaweb.springboot.app.models.service.IMatriculaSimulacroService;
import com.ingenieriaweb.springboot.app.models.service.IUploadFileService;
import com.ingenieriaweb.springboot.app.paginator.PageRender;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/matriculasimulacro")
@SessionAttributes("matriculasimulacro")
public class MatriculaSimulacroController {

    @Autowired
    private IMatriculaSimulacroService matriculasimulacroService;
    @Autowired
    private IMatriculaService matriculaService;
    @Autowired
    private ISimulacroService simulacroService;

    @GetMapping(value ="/listar")
    public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        Pageable pageRequest = PageRequest.of(page, 4);
        Page<MatriculaSimulacro> matriculasimulacros = matriculasimulacroService.findAll(pageRequest);
        PageRender<MatriculaSimulacro> pageRender = new PageRender<MatriculaSimulacro>("/matriculasimulacro/listar", matriculasimulacros);
        model.addAttribute("titulo", "Listado de Matriculas Simulacro");
        model.addAttribute("matriculasimulacros", matriculasimulacros);
        model.addAttribute("page", pageRender);
        return "matriculasimulacro/listar";
    }

    @GetMapping(value = "/form")
    public String crear(Map<String, Object> model) {
        List<Matricula> matriculas = matriculaService.findAllAceptadas();
        List<Simulacro> simulacros = simulacroService.findAll();

        MatriculaSimulacro matriculasimulacros = new MatriculaSimulacro();
        model.put("matriculasimulacros", matriculasimulacros);
        model.put("matriculas", matriculas);
        model.put("simulacros", simulacros);
        model.put("titulo", "Formulario de Matricula Simulacro");
        return "matriculasimulacro/form";
    }

    @GetMapping(value ="/form/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
        List<Matricula> matriculas = matriculaService.findAllAceptadas();
        List<Simulacro> simulacros = simulacroService.findAll();
        MatriculaSimulacro matriculasimulacro = null;

        if (id > 0) {
            matriculasimulacro = matriculasimulacroService.findOne(id);
            if (matriculasimulacro == null) {
                flash.addFlashAttribute("error", "El ID del Matricula Simulacro no existe en la BBDD!");
                return "redirect:/matriculasimulacro/listar";
            }
        } else {
            flash.addFlashAttribute("error", "El ID del Matricula Simulacro no puede ser cero!");
            return "redirect:/matriculasimulacro/listar";
        }
        model.put("matriculasimulacro", matriculasimulacro);
        model.put("matriculas", matriculas);
        model.put("simulacros", simulacros);
        model.put("titulo", "Editar Matricula Simulacro");
        return "matriculasimulacro/form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String guardar(@Valid MatriculaSimulacro matriculasimulacro, BindingResult result, Model model,RedirectAttributes flash, SessionStatus status) {
        List<Matricula> matriculas = matriculaService.findAllAceptadas();
        List<Simulacro> simulacros = simulacroService.findAll();
        if (result.hasErrors()) {
            model.addAttribute("matriculas", matriculas);
            model.addAttribute("simulacros", simulacros);
            model.addAttribute("titulo", "Formulario de Matricula Simulacro");
            return "/matriculasimulacro/form";
        }

        String mensajeFlash = (matriculasimulacro.getId() != null) ? "Matricula Simulacro editado con éxito!" : "Matricula Simulacro creado con éxito!";
        matriculasimulacroService.save(matriculasimulacro);
        status.setComplete();
        flash.addFlashAttribute("success", "Matricula Simulacro guardado con exito!");
        return "redirect:/matriculasimulacro/listar";
    }

    @RequestMapping(value = "/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
        if (id > 0) {
            matriculasimulacroService.delete(id);
            flash.addFlashAttribute("success", "Matricula Simulacro eliminado con exito!");
        }
        return "redirect:/matriculasimulacro/listar";
    }

}