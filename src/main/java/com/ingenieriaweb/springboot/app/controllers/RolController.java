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
import com.ingenieriaweb.springboot.app.models.entity.Rol;
import com.ingenieriaweb.springboot.app.models.service.IRolService;
import com.ingenieriaweb.springboot.app.models.service.IUploadFileService;
import com.ingenieriaweb.springboot.app.paginator.PageRender;

@Controller
@RequestMapping("/rol")
@SessionAttributes("rol")
public class RolController {
    
    @Autowired
    private IRolService RolService;

    @Autowired
    private IUploadFileService uploadFileService;

    @GetMapping(value = "/listar")
    public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

        Pageable pageRequest = PageRequest.of(page, 5);

        Page<Rol> roles = RolService.findAll(pageRequest);

        PageRender<Rol> pageRender = new PageRender<Rol>("/rol/listar", roles);
        model.addAttribute("titulo", "Listado de Roles");
        model.addAttribute("roles", roles);
        model.addAttribute("page", pageRender);
        return "rol/listar";
    }
    
    @GetMapping(value = "/form")
    public String crear(Map<String, Object> model) {

        Rol rol = new Rol();
        model.put("rol", rol);
        model.put("titulo", "Formulario de Rol");
        return "rol/form";
    }

    @GetMapping(value = "/form/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

        Rol rol = null;

        if (id > 0) {
            rol = RolService.findOne(id);
            if (rol == null) {
                flash.addFlashAttribute("error", "El ID del rol no existe en la BBDD!");
                return "redirect:/curso/listar";
            }
        } else {
            flash.addFlashAttribute("error", "El ID del rol no puede ser cero!");
            return "redirect:/curso/listar";
        }
        model.put("rol", rol);
        model.put("titulo", "Editar Rol");
        return "rol/form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String guardar(@Valid Rol rol, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Rol");
            return "/rol/form";
        }
        String mensajeFlash = (rol.getId() != null) ? "Rol editado con éxito!" : "Rol creado con éxito!";
        RolService.save(rol);
        status.setComplete();
        flash.addFlashAttribute("success", mensajeFlash);
        return "redirect:/rol/listar";
    }


    @RequestMapping(value = "/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
        if (id > 0) {
            RolService.delete(id);
            flash.addFlashAttribute("success", "Rol eliminado con éxito!");
        }
        return "redirect:/rol/listar";
    }
}
