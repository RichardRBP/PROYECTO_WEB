package com.ingenieriaweb.springboot.app.controllers;


import com.ingenieriaweb.springboot.app.models.entity.ResponsableAlumno;
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
@RequestMapping("/responsable")
@SessionAttributes("responsable")
public class ResponsableAController {

    @Autowired
    private IProfesorService profesorService;

    @Autowired
    private IUploadFileService uploadFileService;

    @GetMapping(value = "/listar")
    public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

        Pageable pageRequest = PageRequest.of(page, 5);

        Page<ResponsableAlumno> responsable = profesorService.findAllR(pageRequest);

        PageRender<ResponsableAlumno> pageRender = new PageRender<ResponsableAlumno>("/responsable/listar", responsable);
        model.addAttribute("titulo", "Listado de Responsables");
        model.addAttribute("responsable_alumnos", responsable);
        model.addAttribute("page", pageRender);
        return "responsable/listar";
    }

    @GetMapping(value = "/form")
    public String crear(Map<String, Object> model) {

        ResponsableAlumno responsable = new ResponsableAlumno();
        model.put("responsable", responsable);
        model.put("titulo", "Formulario de Responsable Alumno");
        return "responsable/form";
    }

    @GetMapping(value = "/form/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

        ResponsableAlumno responsable = null;

        if (id > 0) {
            responsable = profesorService.findOneR(id);
            if (responsable == null) {
                flash.addFlashAttribute("error", "El ID del Responsable Alumno no existe en la BBDD!");
                return "redirect:/responsable/listar";
            }
        } else {
            flash.addFlashAttribute("error", "El ID del Responsable Alumno no puede ser cero!");
            return "redirect:/responsable/listar";
        }
        model.put("responsable", responsable);
        model.put("titulo", "Editar Responsable Alumno");
        return "responsable/form";
    }

    @RequestMapping(value = "/guardar", method = RequestMethod.POST)
    public String guardar(@Valid ResponsableAlumno responsable, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Responsable Alumno");
            return "/responsable/form";
        }
        String mensajeFlash = (responsable.getId() != null) ? "Responsable Alumno editado con éxito!" : "Responsable Alumno creado con éxito!";
        profesorService.saveResponsable(responsable);
        status.setComplete();
        flash.addFlashAttribute("success", mensajeFlash);
        return "redirect:/responsable/listar";
    }

    @RequestMapping(value = "/eliminar", method = RequestMethod.GET)
    public String eliminar(@RequestParam(value = "id") Long id, RedirectAttributes flash) {
        if (id > 0) {
            profesorService.deleteR(id);
            flash.addFlashAttribute("success", "Responsable Alumno eliminado con éxito!");
        }
        return "redirect:/responsable/listar";
    }
}
