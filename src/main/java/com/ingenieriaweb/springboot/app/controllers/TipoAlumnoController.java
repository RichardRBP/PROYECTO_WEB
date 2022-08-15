package com.ingenieriaweb.springboot.app.controllers;

import java.util.Map;

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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.ingenieriaweb.springboot.app.models.entity.TipoAlumno;
import com.ingenieriaweb.springboot.app.models.service.ITipoAlumnoService;
import com.ingenieriaweb.springboot.app.paginator.PageRender;

@Controller
@RequestMapping("/tipoAlumno")
@SessionAttributes("tipoAlumno")
public class TipoAlumnoController {

    @Autowired
    private ITipoAlumnoService tipoAlumnoService;
    
    @GetMapping(value = "/listar")
    public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        Pageable pageRequest = PageRequest.of(page, 4);
        Page<TipoAlumno> tipoAlumnos = tipoAlumnoService.findAll(pageRequest);
        PageRender<TipoAlumno> pageRender = new PageRender<TipoAlumno>("/tipoAlumno/listar", tipoAlumnos);
        model.addAttribute("titulo", "Listado de TipoAlumnos");
        model.addAttribute("tipoAlumnos", tipoAlumnos);
        model.addAttribute("page", pageRender);
        return "tipoAlumno/listar";
    }
    
    @GetMapping(value = "/form")
    public String crear(Map<String, Object> model) {
        TipoAlumno tipoAlumno = new TipoAlumno();
        model.put("tipoAlumno", tipoAlumno);
        model.put("titulo", "Formulario de TipoAlumno");
        return "tipoAlumno/form";
    }
    
    @PostMapping(value = "/form", method = RequestMethod.POST)
    public String guardar(@Valid TipoAlumno tipoAlumno, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de TipoAlumno");
            return "/tipoAlumno/form";
        }
        String mensajeFlash = (tipoAlumno.getId() != null) ? "TipoAlumno editado con éxito" : "TipoAlumno creado con éxito";
        tipoAlumnoService.save(tipoAlumno);
        status.setComplete();
        flash.addFlashAttribute("success", "Tipo de alumno guardado con éxito");
        return "redirect:/tipoAlumno/listar";
    }
    
    @GetMapping(value = "/form/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
        TipoAlumno tipoAlumno = null;
        if (id > 0) {
            tipoAlumno = tipoAlumnoService.findOne(id);
            if (tipoAlumno == null) {
                flash.addFlashAttribute("error", "El ID del tipo de alumno no existe en la base de datos");
                return "redirect:/tipoAlumno/listar";
            }
        } else {
            flash.addFlashAttribute("error", "El ID del tipo de alumno no puede ser cero");
            return "redirect:/tipoAlumno/listar";
        }
        model.put("tipoAlumno", tipoAlumno);
        model.put("titulo", "Editar TipoAlumno");
        return "tipoAlumno/form";
    }

    @GetMapping(value = "/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
        if (id > 0) {
            tipoAlumnoService.delete(id);
            flash.addFlashAttribute("success", "Tipo de alumno eliminado con éxito");
        }
        return "redirect:/tipoAlumno/listar";
    }
}
