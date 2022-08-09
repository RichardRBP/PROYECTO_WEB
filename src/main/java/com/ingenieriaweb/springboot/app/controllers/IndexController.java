package com.ingenieriaweb.springboot.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app")
public class IndexController {
	
	@GetMapping({ "/", "/login"}) //login
	public String login(Model model) {
		model.addAttribute("login", "BIENVENIDO");
		return "login";
	}
	
	@GetMapping({"/home"}) //central-alumno
	public String index(Model model) {
		model.addAttribute("titulo", "PROYECTO INGENIERIA WEB");
		return "index";
	}
	
	
	@GetMapping({ "/", "/1-Aulas"}) //aulas
	public String aula(Model model) {
		model.addAttribute("aula", "BIENVENIDO");
		return "1-Aulas";
	}
	@GetMapping({ "/", "/1-AulasR"}) //aulas-registro
	public String aular(Model model) {
		model.addAttribute("aula", "BIENVENIDO");
		return "1-AulasR";
	}
	
	@GetMapping({ "/", "/2-Tarifas"}) //tarifa
	public String tarifa(Model model) {
		model.addAttribute("tarifas", "BIENVENIDO");
		return "2-Tarifas";
	}
	
	@GetMapping({ "/", "/2-TarifasR"}) //tarifa-registro
	public String tarifar(Model model) {
		model.addAttribute("profesor", "BIENVENIDO");
		return "2-TarifasR";
	}
	
	@GetMapping({ "/", "/3-Profesores"}) //Profesores
	public String profesores(Model model) {
		model.addAttribute("profesores", "BIENVENIDO");
		return "3-Profesores";
	}
	
	@GetMapping({ "/", "/3-ProfesoresR"}) //profesor-registro
	public String profesorr(Model model) {
		model.addAttribute("profesorr", "BIENVENIDO");
		return "3-ProfesoresR";
	}
	
	@GetMapping({ "/", "/4-Cursos"}) //cursos
	public String cursos(Model model) {
		model.addAttribute("cursos", "BIENVENIDO");
		return "4-Cursos";
	}
	
	@GetMapping({ "/", "/4-CursosR"}) //cursos-registro
	public String cursosr(Model model) {
		model.addAttribute("cursosr", "BIENVENIDO");
		return "4-CursosR";
	}
	
	@GetMapping({ "/", "/5-Alumnos"}) //alumno
	public String alumno(Model model) {
		model.addAttribute("alumno", "BIENVENIDO");
		return "5-Alumnos";
	}
	
	@GetMapping({ "/", "/5-AlumnosR"}) //alumno-registro
	public String alumnor(Model model) {
		model.addAttribute("alumnor", "BIENVENIDO");
		return "5-AlumnosR";
	}
	
	@GetMapping({ "/", "/6-Area"}) //area
	public String area(Model model) {
		model.addAttribute("area", "BIENVENIDO");
		return "6-Area";
	}
	
	@GetMapping({ "/", "/6-AreaR"}) //area-registro
	public String arear(Model model) {
		model.addAttribute("arear", "BIENVENIDO");
		return "6-AreaR";
	}
	
	@GetMapping({ "/", "/7-Carreras"}) //carrera
	public String carrera(Model model) {
		model.addAttribute("carrera", "BIENVENIDO");
		return "7-Carreras";
	}
	
	@GetMapping({ "/", "/7-CarrerasR"}) //carrera-registro
	public String carrerasr(Model model) {
		model.addAttribute("carrerasr", "BIENVENIDO");
		return "7-CarrerasR";
	}
	
	@GetMapping({ "/", "/8-Usuarios"}) //usuario
	public String usuario(Model model) {
		model.addAttribute("usuario", "BIENVENIDO");
		return "8-Usuarios";
	}
	
	@GetMapping({ "/", "/8-UsuariosR"}) //usuarior-registro
	public String usuarior(Model model) {
		model.addAttribute("usuarior", "BIENVENIDO");
		return "8-UsuariosR";
	}

	}

