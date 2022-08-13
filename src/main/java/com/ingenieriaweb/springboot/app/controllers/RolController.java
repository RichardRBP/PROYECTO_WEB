package com.ingenieriaweb.springboot.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.ingenieriaweb.springboot.app.models.entity.Curso;
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
    
}
