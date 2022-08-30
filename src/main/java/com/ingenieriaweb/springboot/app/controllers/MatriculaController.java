package com.ingenieriaweb.springboot.app.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.ingenieriaweb.springboot.app.models.entity.Alumno;
import com.ingenieriaweb.springboot.app.models.entity.Matricula;
import com.ingenieriaweb.springboot.app.models.entity.Tarifa;
import com.ingenieriaweb.springboot.app.models.service.IAlumnoService;
import com.ingenieriaweb.springboot.app.models.service.IMatriculaService;
import com.ingenieriaweb.springboot.app.models.service.IProfesorService;
import com.ingenieriaweb.springboot.app.models.service.IUploadFileService;
import com.ingenieriaweb.springboot.app.paginator.PageRender;
import com.ingenieriaweb.springboot.app.reports.MatriculaExporterPDF;
import com.lowagie.text.DocumentException;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Controller
@RequestMapping("/matricula")
@SessionAttributes("matricula")
public class MatriculaController {
     
    @Autowired
    private IMatriculaService MatriculaService;
    @Autowired
    private IProfesorService profesorService;
    @Autowired
    private IAlumnoService alumnoService;

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

     
    @GetMapping(value = "/listar")
    public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

        Pageable pageRequest = PageRequest.of(page, 4);

        Page<Matricula> matriculas = MatriculaService.findAll(pageRequest);

        PageRender<Matricula> pageRender = new PageRender<Matricula>("/matricula/listar", matriculas);
        model.addAttribute("titulo", "Listado de Matriculas");
        model.addAttribute("matriculas", matriculas);
        model.addAttribute("page", pageRender);
        return "matricula/listar";
    }

    @GetMapping(value = "/aceptar")
    public String aceptar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

      //  Pageable pageRequest = PageRequest.of(page, 5);

        List<Matricula> matriculas = MatriculaService.findByEstado();

        //PageRender<Matricula> pageRender = new PageRender<Matricula>("/matricula/listar", matriculas);
        model.addAttribute("titulo", "Listado de Matriculas");
        model.addAttribute("matriculas", matriculas);
      //  model.addAttribute("page", pageRender);
        return "matricula/aceptar";
    }
   
    @GetMapping(value = "/aceptar/{id}")
    public String aceptada(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
        List<Tarifa> tarifas = profesorService.findAllT();
        List<Alumno> alumnos = profesorService.findAllAlu();
        Matricula matricula = null;

        if (id > 0) {
            matricula = MatriculaService.findOne(id);
            if (matricula == null) {
                flash.addFlashAttribute("error", "El ID del Matricula no existe en la BBDD!");
                return "redirect:/matricula/listar";
            }
        } else {
            flash.addFlashAttribute("error", "El ID del Matricula no puede ser cero!");
            return "redirect:/matricula/listar";
        }
        model.put("matricula", matricula);
        model.put("tarifas", tarifas);
        model.put("alumnos", alumnos);
        model.put("titulo", "Aceptar Matricula");
        return "matricula/verificar";
    }

    @RequestMapping(value = "/form2", method = RequestMethod.POST)
    public String actualizar(@Valid Matricula matricula, BindingResult result, Model model,  RedirectAttributes flash, SessionStatus status) {
        Matricula matricula2 = null; 
        matricula2 = MatriculaService.findOne(matricula.getId());
       
       
        String mensajeFlash = (matricula.getId() != null) ? "Matricula Aceptada!" : "Matricula Aceptada!";
        matricula2.setEstado("ACEPTADA")   ;
        MatriculaService.save(matricula2);
        status.setComplete();
        flash.addFlashAttribute("success", mensajeFlash);
        return "redirect:/matricula/aceptar";
    }
    
    @GetMapping(value = "/form")
    public String crear(Map<String, Object> model) {

        List<Tarifa> tarifas = profesorService.findAllT();
        List<Alumno> alumnos = alumnoService.findAll();
        

        Matricula matricula = new Matricula();
        model.put("matricula", matricula);
        model.put("alumnos", alumnos);
        model.put("tarifas", tarifas);
        model.put("titulo", "Formulario de Matricula");
        return "matricula/form";
    }

    @GetMapping(value = "/form/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
        List<Tarifa> tarifas = profesorService.findAllT();
        List<Alumno> alumnos = profesorService.findAllAlu();
        Matricula matricula = null;

        if (id > 0) {
            matricula = MatriculaService.findOne(id);
            if (matricula == null) {
                flash.addFlashAttribute("error", "El ID del Matricula no existe en la BBDD!");
                return "redirect:/matricula/listar";
            }
        } else {
            flash.addFlashAttribute("error", "El ID del Matricula no puede ser cero!");
            return "redirect:/matricula/listar";
        }
        model.put("matricula", matricula);
        model.put("tarifas", tarifas);
        model.put("alumnos", alumnos);
        model.put("titulo", "Editar Matricula");
        return "matricula/form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String guardar(@Valid Matricula matricula, BindingResult result, Model model, @RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) {
        List<Tarifa> tarifas = profesorService.findAllT();
        List<Alumno> alumnos = profesorService.findAllAlu();
        if (result.hasErrors()) {
            model.addAttribute("tarifas", tarifas);
            model.addAttribute("alumnos", alumnos);
            model.addAttribute("titulo", "Formulario de Matricula");
            return "/matricula/form";
        }

        if (!foto.isEmpty()) {

            if (matricula.getId() != null && matricula.getId() > 0 && matricula.getFoto() != null
                    && matricula.getFoto().length() > 0) {

                uploadFileService.delete(matricula.getFoto());
            }

            String uniqueFilename = null;
            try {
                uniqueFilename = uploadFileService.copy(foto);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            //flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");

            matricula.setFoto(uniqueFilename);
        }

        String mensajeFlash = (matricula.getId() != null) ? "Matricula editado con éxito!" : "Matricula creado con éxito!";
        MatriculaService.save(matricula);
        status.setComplete();
        flash.addFlashAttribute("success", mensajeFlash);
        return "redirect:/matricula/listar";
    }


    @RequestMapping(value = "/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
        if (id > 0) {
            MatriculaService.delete(id);
            flash.addFlashAttribute("success", "Matricula eliminado con éxito!");
        }
        return "redirect:/matricula/listar";
    }
    
    @GetMapping("/exportarPDF")
    public void exportarListaMatriculaPDF(HttpServletResponse response) throws DocumentException, IOException {
    	response.setContentType("aplication/pdf");
    	
    	DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
    	String fechaActual = dateFormatter.format(new Date());
    	
    	String cabecera = "Content-Disposition";
    	String valor = "attachment; filename=Matriculas_"+ fechaActual + ".pdf";
    	
    	response.setHeader(cabecera, valor);
    	
    	List<Matricula> matriculas = MatriculaService.findAllAceptadas();
    	
    	MatriculaExporterPDF exporter = new MatriculaExporterPDF(matriculas);
    	exporter.exportar(response);
    }
    
    
}
