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
import com.ingenieriaweb.springboot.app.models.entity.Ciclo;
import com.ingenieriaweb.springboot.app.models.service.ICicloService;
import com.ingenieriaweb.springboot.app.models.service.IUploadFileService;
import com.ingenieriaweb.springboot.app.paginator.PageRender;

@Controller
@RequestMapping("/ciclo")
@SessionAttributes("ciclo")
public class CicloController {
    
    @Autowired
    private ICicloService cicloService;

     
    @GetMapping(value = "/listar")
    public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

        Pageable pageRequest = PageRequest.of(page, 5);

        Page<Ciclo> ciclos = cicloService.findAll(pageRequest);

        PageRender<Ciclo> pageRender = new PageRender<Ciclo>("/ciclo/listar", ciclos);
        model.addAttribute("titulo", "Listado de Ciclos");
        model.addAttribute("ciclos", ciclos);
        model.addAttribute("page", pageRender);
        return "ciclo/listar";
    }
    
    @GetMapping(value = "/form")
    public String crear(Map<String, Object> model) {

        Ciclo ciclo = new Ciclo();
        model.put("ciclo", ciclo);
        model.put("titulo", "Formulario de Ciclo");
        return "ciclo/form";
    }

    @GetMapping(value = "/form/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

        Ciclo ciclo = null;

        if (id > 0) {
            ciclo = cicloService.findOne(id);
            if (ciclo == null) {
                flash.addFlashAttribute("error", "El ID del ciclo no existe en la BBDD!");
                return "redirect:/curso/listar";
            }
        } else {
            flash.addFlashAttribute("error", "El ID del ciclo no puede ser cero!");
            return "redirect:/curso/listar";
        }
        model.put("ciclo", ciclo);
        model.put("titulo", "Editar Ciclo");
        return "ciclo/form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String guardar(@Valid Ciclo ciclo, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Ciclo");
            return "/ciclo/form";
        }
        String mensajeFlash = (ciclo.getId() != null) ? "Ciclo editado con éxito!" : "Ciclo creado con éxito!";
        cicloService.save(ciclo);
        status.setComplete();
        flash.addFlashAttribute("success", mensajeFlash);
        return "redirect:/ciclo/listar";
    }


    @RequestMapping(value = "/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
        if (id > 0) {
            cicloService.delete(id);
            flash.addFlashAttribute("success", "ciclo eliminado con éxito!");
        }
        return "redirect:/ciclo/listar";
    }
}
