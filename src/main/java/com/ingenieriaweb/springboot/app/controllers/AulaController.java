package com.ingenieriaweb.springboot.app.controllers;

import com.ingenieriaweb.springboot.app.models.entity.Aula;
import com.ingenieriaweb.springboot.app.models.service.IProfesorService;
import com.ingenieriaweb.springboot.app.models.service.IUploadFileService;
import com.ingenieriaweb.springboot.app.paginator.PageRender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/aula")
@SessionAttributes("aula")
public class AulaController {

    @Autowired
    private IProfesorService profesorService;

    @Autowired
    private IUploadFileService uploadFileService;


    @GetMapping(value = "/listar")
    public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

        Pageable pageRequest = PageRequest.of(page, 5);

        Page<Aula> aula = profesorService.findAllA(pageRequest);

        PageRender<Aula> pageRender = new PageRender<Aula>("/aula/listar", aula);
        model.addAttribute("titulo", "Listado de aulas");
        model.addAttribute("aulas", aula);
        model.addAttribute("page", pageRender);
        return "aula/listar";
    }

    @GetMapping(value = "/form")
    public String crear(Map<String, Object> model) {

        Aula aula = new Aula();
        model.put("aula", aula);
        model.put("titulo", "Formulario de Aula");
        return "aula/form";
    }

    @GetMapping(value = "/form/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

        Aula aula = null;

        if (id > 0) {
            aula = profesorService.findOneA(id);
            if (aula == null) {
                flash.addFlashAttribute("error", "El ID del aula no existe en la BBDD!");
                return "redirect:/aula/listar";
            }
        } else {
            flash.addFlashAttribute("error", "El ID del aula no puede ser cero!");
            return "redirect:/aula/listar";
        }
        model.put("aula", aula);
        model.put("titulo", "Editar Aula");
        return "aula/form";
    }


    @RequestMapping(value = "/guardar", method = RequestMethod.POST)
    public String guardar(@Valid Aula aula, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Aula");
            return "aula/form";
        }
        String mensajeFlash = (aula.getId() != null) ? "Aula editado con éxito!" : "Aula creado con éxito!";
        profesorService.saveAula(aula);
        status.setComplete();
        flash.addFlashAttribute("success", mensajeFlash);
        return "redirect:listar";
    }

    @RequestMapping(value = "/eliminar")
    public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

        if (id > 0) {
            profesorService.deleteA(id);
            flash.addFlashAttribute("success", "Aula eliminado con éxito!");
        }
        return "redirect:/aula/listar";
    }



}
