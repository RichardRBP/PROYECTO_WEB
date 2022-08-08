package com.ingenieriaweb.springboot.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app")
public class IndexController {

	@GetMapping({"/home"})
	public String index(Model model) {
		model.addAttribute("titulo", "PROYECTO INGENIERIA WEB");
		return "index";
	}

	@GetMapping({ "/", "/login"})
	public String login(Model model) {
		model.addAttribute("login", "BIENVENIDO");
		return "login";
	}

}
