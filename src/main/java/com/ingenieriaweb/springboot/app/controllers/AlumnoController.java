package com.ingenieriaweb.springboot.app.controllers;

import com.ingenieriaweb.springboot.app.models.entity.Alumno;
import com.ingenieriaweb.springboot.app.models.entity.Profesor;
import com.ingenieriaweb.springboot.app.models.service.IAlumnoService;
import com.ingenieriaweb.springboot.app.models.service.IProfesorService;
import com.ingenieriaweb.springboot.app.models.service.ITipoAlumnoService;
import com.ingenieriaweb.springboot.app.models.service.IUploadFileService;
import com.ingenieriaweb.springboot.app.paginator.PageRender;
import com.ingenieriaweb.springboot.app.reports.AlumnoExporterPDF;
import com.ingenieriaweb.springboot.app.reports.ProfesorExporterPDF;
import com.lowagie.text.DocumentException;

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

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/alumno")
@SessionAttributes("alumno")
public class AlumnoController {

    @Autowired
    private ITipoAlumnoService tipoAlumnoService;

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

//    make function ver of the class Alumno
    @GetMapping(value = "/ver/{id}")
    public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

        Alumno alumno = profesorService.findOneAlu(id);
        if (alumno == null) {
            flash.addFlashAttribute("error", "El alumno no existe en la base de datos");
            return "redirect:/alumno/listar";
        }

        model.put("alumno", alumno);
        model.put("tipoAlumno", tipoAlumnoService.findAll());
        model.put("carrera", profesorService.findAllCar());
        return "alumno/ver";
    }


    @GetMapping(value = "/listar")
    public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

        Pageable pageRequest = PageRequest.of(page, 5);

        Page<Alumno> alumno = profesorService.findAllAlu(pageRequest);
        PageRender<Alumno> pageRender = new PageRender<Alumno>("/alumno/listar", alumno);
        model.addAttribute("titulo", "Listado de alumnos");
        model.addAttribute("alumnos", alumno);
        model.addAttribute("page", pageRender);
        return "alumno/listar";
    }

    @GetMapping(value = "/form")
    public String crear(Map<String, Object> model) {
        Alumno alumno = new Alumno();
        model.put("alumno", alumno);
        model.put("titulo", "Formulario de Alumno");
        model.put("tipoAlumno", tipoAlumnoService.findAll());
        model.put("carrera", profesorService.findAllCar());
        return "alumno/form";
    }

    @GetMapping(value = "/form/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
        Alumno alumno = null;

        if (id > 0) {
            alumno = profesorService.findOneAlu(id);
            if (alumno == null) {
                flash.addFlashAttribute("error", "El ID del alumno no existe en la BBDD!");
                return "redirect:/alumno/listar";
            }
        } else {
            flash.addFlashAttribute("error", "El ID del alumno no puede ser cero!");
            return "redirect:/alumno/listar";
        }
        model.put("alumno", alumno);
        model.put("tipoAlumno", tipoAlumnoService.findAll());
        model.put("carrera", profesorService.findAllCar());
        model.put("titulo", "Editar Alumno");
        return "alumno/form";
    }


    @RequestMapping(value="/form" , method = RequestMethod.POST)
    public String guardar(@Valid Alumno alumno, BindingResult result, Model model, @RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Alumno");
            model.addAttribute("tipoAlumno", tipoAlumnoService.findAll());
            model.addAttribute("carrera", profesorService.findAllCar());
            return "/alumno/form";
        }

        if (!foto.isEmpty()) {

            if (alumno.getId() != null && alumno.getId() > 0 && alumno.getFoto() != null
                    && alumno.getFoto().length() > 0) {

                uploadFileService.delete(alumno.getFoto());
            }

            String uniqueFilename = null;
            try {
                uniqueFilename = uploadFileService.copy(foto);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            //flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");

            alumno.setFoto(uniqueFilename);
        }

        String mensajeFlash = (alumno.getId() != null) ? "Alumno editado con éxito!" : "Alumno creado con éxito!";
        profesorService.saveAlumno(alumno);
        status.setComplete();
        flash.addFlashAttribute("success", mensajeFlash);
        return "redirect:/alumno/listar";
    }

    @RequestMapping(value = "/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
        if (id > 0) {
            profesorService.deleteAlu(id);
            flash.addFlashAttribute("success", "Alumno eliminado con éxito!");
        }
        return "redirect:/alumno/listar";
    }

    @GetMapping("/exportarPDF")
    public void exportarListaAlumnoPDF(HttpServletResponse response) throws DocumentException, IOException {
    	response.setContentType("aplication/pdf");
    	
    	DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
    	String fechaActual = dateFormatter.format(new Date());
    	
    	String cabecera = "Content-Disposition";
    	String valor = "attachment; filename=Alumno_"+ fechaActual + ".pdf";
    	
    	response.setHeader(cabecera, valor);
    	
    	List<Alumno> alumnos = alumnoService.findAll();
    	
    	AlumnoExporterPDF exporter = new AlumnoExporterPDF(alumnos);
    	exporter.exportar(response);
    }
    
}
