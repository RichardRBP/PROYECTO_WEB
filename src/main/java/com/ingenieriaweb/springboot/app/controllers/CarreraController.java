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

import com.ingenieriaweb.springboot.app.models.entity.Area;
import com.ingenieriaweb.springboot.app.models.entity.Carrera;
import com.ingenieriaweb.springboot.app.models.service.IProfesorService;
import com.ingenieriaweb.springboot.app.paginator.PageRender;

@Controller
@RequestMapping("/carrera")
@SessionAttributes("carrera")
public class CarreraController {
	  @Autowired
	    private IProfesorService profesorService;
	  
	  @GetMapping(value = "/listar")
	    public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
	        Pageable pageRequest = PageRequest.of(page, 4);
	        Page<Carrera> carreras = profesorService.findAllCar(pageRequest);
	        PageRender<Carrera> pageRender = new PageRender<Carrera>("/carrera/listar", carreras);
	        model.addAttribute("titulo", "Listado de Areas");
	        model.addAttribute("carreras", carreras);
	        model.addAttribute("page", pageRender);
	        return "carrera/listar";
	    }
	  
	  @GetMapping(value = "/form")
	    public String crear(Model model) {
	        Carrera carrera = new Carrera();
	        model.addAttribute("carrera", carrera);
	        model.addAttribute("titulo", "Formulario de Carrera");
	        return "carrera/form";
	    }
	  @GetMapping(value = "/form/{id}")
	    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

	    	Carrera carrera = null;

	        if (id > 0) {
	        	carrera = profesorService.findOneCar(id);
	            if (carrera == null) {
	                flash.addFlashAttribute("error", "El ID de la carrera no existe en la BBDD!");
	                return "redirect:/carrera/listar";
	            }
	        } else {
	            flash.addFlashAttribute("error", "El ID de la carrera no puede ser cero!");
	            return "redirect:/carrera/listar";
	        }
	        model.put("carrera", carrera);
	        model.put("titulo", "Editar Carrera");
	        return "carrera/form";
	    }    
	  @RequestMapping(value = "/form", method = RequestMethod.POST)
	    public String guardar(@Valid Carrera carrera, BindingResult result, Model model, SessionStatus status) {
	        if (result.hasErrors()) {
	            model.addAttribute("titulo", "Formulario de Carrera");
	            return "carrera/form";
	        }
	        profesorService.saveCarrera(carrera);
	        status.setComplete();
	        return "redirect:/carrera/listar";
	    }

	    @RequestMapping(value = "/eliminar/{id}")
	    public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
	        if (id > 0) {
	            profesorService.deleteCar(id);
	            flash.addFlashAttribute("success", "La carrera fue eliminada con exito");
	        }
	        return "redirect:/carrera/listar";

	    }
}
