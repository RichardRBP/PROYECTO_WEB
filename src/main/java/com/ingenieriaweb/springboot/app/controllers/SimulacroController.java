package com.ingenieriaweb.springboot.app.controllers;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



import com.ingenieriaweb.springboot.app.models.entity.Simulacro;

import com.ingenieriaweb.springboot.app.models.service.ISimulacroService;
import com.ingenieriaweb.springboot.app.paginator.PageRender;

@Controller
@RequestMapping("/simulacro")
@SessionAttributes("simulacro")
public class SimulacroController {
	@Autowired
    private ISimulacroService simulacroService;
  
  @GetMapping(value = "/listar")
    public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        Pageable pageRequest = PageRequest.of(page, 4);
        Page<Simulacro> simulacros = simulacroService.findAll(pageRequest);
        PageRender<Simulacro> pageRender = new PageRender<Simulacro>("/simulacro/listar", simulacros);
        model.addAttribute("titulo", "Listado de Simulacros");
        model.addAttribute("simulacros", simulacros);
        model.addAttribute("page", pageRender);
        return "simulacro/listar";
    }
  
  @GetMapping(value = "/form")
    public String crear(Model model) {
	    Simulacro simulacro = new Simulacro();
        model.addAttribute("simulacro", simulacro);
        model.addAttribute("titulo", "Formulario de Simulacro");
        return "simulacro/form";
    }
  @GetMapping(value = "/form/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

	   Simulacro simulacro = null;

        if (id > 0) {
        	simulacro = simulacroService.findOne(id);
            if (simulacro == null) {
                flash.addFlashAttribute("error", "El ID del simulacro no existe en la BBDD!");
                return "redirect:/simulacro/listar";
            }
        } else {
            flash.addFlashAttribute("error", "El ID del simulacro no puede ser cero!");
            return "redirect:/simulacro/listar";
        }
        model.put("simulacro",simulacro);
        model.put("titulo", "Editar Simulacro");
        return "simulacro/form";
    }    
  @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String guardar(@Valid Simulacro simulacro, BindingResult result, Model model, SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Simulacro");
            return "simulacro/form";
        }
        simulacroService.save(simulacro);
        status.setComplete();
        return "redirect:/simulacro/listar";
    }

    @RequestMapping(value = "/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
        if (id > 0) {
        	simulacroService.delete(id);
            flash.addFlashAttribute("success", "El simulacro fue eliminado con exito");
        }
        return "redirect:/simulacro/listar";
}
}
