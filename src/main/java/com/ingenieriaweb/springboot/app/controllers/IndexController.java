package com.ingenieriaweb.springboot.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app")
public class IndexController {

    @GetMapping({"/", "/login"}) //login
    public String login(Model model) {
        model.addAttribute("login", "BIENVENIDO");
        return "login";
    }

}

