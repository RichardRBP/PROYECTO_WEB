package com.ingenieriaweb.springboot.app.controllers;

import com.ingenieriaweb.springboot.app.models.entity.Area;
import com.ingenieriaweb.springboot.app.models.service.IProfesorService;

import com.ingenieriaweb.springboot.app.paginator.PageRender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.validation.Valid;
import java.net.MalformedURLException;
import java.util.Map;

@Controller
@RequestMapping("/area")
@SessionAttributes("area")
public class AreaController {
    
    @Autowired
    private IProfesorService profesorService;
    
    @GetMapping(value = "/listar")
    public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        Pageable pageRequest = PageRequest.of(page, 4);
        Page<Area> areas = profesorService.findAllAr(pageRequest);
        PageRender<Area> pageRender = new PageRender<Area>("/area/listar", areas);
        model.addAttribute("titulo", "Listado de Areas");
        model.addAttribute("areas", areas);
        model.addAttribute("page", pageRender);
        return "area/listar";
    }
    
    @GetMapping(value = "/form")
    public String crear(Model model) {
        Area area = new Area();
        model.addAttribute("area", area);
        model.addAttribute("titulo", "Formulario de Area");
        return "area/form";
    }
    
    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String guardar(@Valid Area area, BindingResult result, Model model, SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Area");
            return "area/form";
        }
        profesorService.saveArea(area);
        status.setComplete();
        return "redirect:/area/listar";
    }

    @RequestMapping(value = "/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
        if (id > 0) {
            profesorService.deleteAr(id);
            flash.addFlashAttribute("success", "El area fue eliminado con exito");
        }
        return "redirect:/area/listar";

    }

}