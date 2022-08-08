package com.ingenieriaweb.springboot.app.controllers;


import com.ingenieriaweb.springboot.app.models.entity.Tarifa;
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
@RequestMapping("/tarifa")
@SessionAttributes("tarifa")
public class TarifaController {

    @Autowired
    private IProfesorService profesorService;

    @GetMapping(value = "/listar")
    public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

        Pageable pageRequest = PageRequest.of(page, 5);

        Page<Tarifa> tarifa = profesorService.findAllT(pageRequest);

        PageRender<Tarifa> pageRender = new PageRender<Tarifa>("/tarifa/listar", tarifa);
        model.addAttribute("titulo", "Listado de tarifas");
        model.addAttribute("tarifas", tarifa);
        model.addAttribute("page", pageRender);
        return "tarifa/listar";
    }

    @GetMapping(value = "/form")
    public String crear(Map<String, Object> model) {

        Tarifa tarifa = new Tarifa();
        model.put("tarifa", tarifa);
        model.put("titulo", "Formulario de Tarifa");
        return "tarifa/form";
    }

    @GetMapping(value = "/form/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

        Tarifa tarifa = null;

        if (id > 0) {
            tarifa = profesorService.findOneT(id);
            if (tarifa == null) {
                flash.addFlashAttribute("error", "El ID del tarifa no existe en la BBDD!");
                return "redirect:/tarifa/listar";
            }
        } else {
            flash.addFlashAttribute("error", "El ID del tarifa no puede ser cero!");
            return "redirect:/tarifa/listar";
        }
        model.put("tarifa", tarifa);
        model.put("titulo", "Editar Tarifa");
        return "tarifa/form";
    }

    @RequestMapping(value = "/guardar", method = RequestMethod.POST)
    public String guardar(@Valid Tarifa tarifa, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Tarifa");
            return "/tarifa/form";
        }
        String mensajeFlash = (tarifa.getId() != null) ? "Tarifa editado con éxito!" : "Tarifa creado con éxito!";
        profesorService.saveTarifa(tarifa);
        status.setComplete();
        flash.addFlashAttribute("success", mensajeFlash);
        return "redirect:/tarifa/listar";
    }

    @RequestMapping(value = "/eliminar", method = RequestMethod.GET)
    public String eliminar(@RequestParam(value = "id") Long id, RedirectAttributes flash) {
        if (id > 0) {
            profesorService.deleteT(id);
            flash.addFlashAttribute("success", "Tarifa eliminado con éxito!");
        }
        return "redirect:/tarifa/listar";
    }
    
    
    
    
    
}
