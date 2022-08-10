package com.ingenieriaweb.springboot.app.controllers;


import com.ingenieriaweb.springboot.app.models.entity.Profesor;
import com.ingenieriaweb.springboot.app.models.service.IProfesorService;
import com.ingenieriaweb.springboot.app.models.service.IUploadFileService;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

@Controller
@RequestMapping("/profesor")
@SessionAttributes("profesor")
public class ProfesorController {

    @Autowired
    private IProfesorService profesorService;

    @Autowired
    private IUploadFileService uploadFileService;

    @GetMapping(value = "/uploads/{filename:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String filename) {

        Resource recurso = null;

        try {
            recurso = uploadFileService.load(filename);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
                .body(recurso);
    }

    @GetMapping(value = "/ver/{id}")
    public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

        Profesor profesor = profesorService.findOneP(id);
        if (profesor == null) {
            flash.addFlashAttribute("error", "El profesor no existe en la base de datos");
            return "redirect:/profesor/listar";
        }

        model.put("profesor", profesor);
        model.put("titulo", "Detalle profesor: " + profesor.getNombre());
        return "profesor/ver";
    }

    @GetMapping(value = "/listar")
    public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

        Pageable pageRequest = PageRequest.of(page, 5);

        Page<Profesor> profesor = profesorService.findAllP(pageRequest);

        PageRender<Profesor> pageRender = new PageRender<Profesor>("/profesor/listar", profesor);
        model.addAttribute("titulo", "Listado de profesores");
        model.addAttribute("profesores", profesor);
        model.addAttribute("page", pageRender);
        return "profesor/listar";
    }

    @GetMapping(value = "/form")
    public String crear(Map<String, Object> model) {

        Profesor profesor = new Profesor();
        model.put("profesor", profesor);
        model.put("titulo", "Formulario de Profesor");
        return "profesor/form";
    }

    @GetMapping(value = "/form/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

        Profesor profesor = null;

        if (id > 0) {
            profesor = profesorService.findOneP(id);
            if (profesor == null) {
                flash.addFlashAttribute("error", "El ID del profesor no existe en la BBDD!");
                return "redirect:/profesor/listar";
            }
        } else {
            flash.addFlashAttribute("error", "El ID del profesor no puede ser cero!");
            return "redirect:/profesor/listar";
        }
        model.put("profesor", profesor);
        model.put("titulo", "Editar Profesor");
        return "profesor/form";
    }

    @PostMapping("/form")
    public String guardar(@Valid Profesor profesor, BindingResult result, Model model, @RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Profesor");
            return "/profesor/form";
        }

        if (!foto.isEmpty()) {

            if (profesor.getId() != null && profesor.getId() > 0 && profesor.getFoto() != null
                    && profesor.getFoto().length() > 0) {

                uploadFileService.delete(profesor.getFoto());
            }

            String uniqueFilename = null;
            try {
                uniqueFilename = uploadFileService.copy(foto);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            //flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");

            profesor.setFoto(uniqueFilename);
        }

        String mensajeFlash = (profesor.getId() != null) ? "Profesor editado con éxito!" : "Profesor creado con éxito!";
        profesorService.saveProfesor(profesor);
        status.setComplete();
        flash.addFlashAttribute("success", mensajeFlash);
        return "redirect:/profesor/listar";
    }

    @RequestMapping(value = "/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
        if (id > 0) {
            profesorService.deleteP(id);
            flash.addFlashAttribute("success", "Profesor eliminado con éxito!");
        }
        return "redirect:/profesor/listar";
    }

}
