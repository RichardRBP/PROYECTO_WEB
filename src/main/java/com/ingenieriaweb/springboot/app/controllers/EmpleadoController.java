package com.ingenieriaweb.springboot.app.controllers;

import java.io.IOException;
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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.ingenieriaweb.springboot.app.models.entity.Empleado;
import com.ingenieriaweb.springboot.app.models.service.IEmpleadoService;
import com.ingenieriaweb.springboot.app.models.service.IUploadFileService;
import com.ingenieriaweb.springboot.app.paginator.PageRender;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import java.net.MalformedURLException;
///////////////
//importaciones para los reportes
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/empleado")
@SessionAttributes("empleado")
public class EmpleadoController {
    
    @Autowired
    private IEmpleadoService EmpleadoService;

    @Autowired
    private IUploadFileService uploadFileService;
    /////////////////////
    
    //metodos para los reportes en jasper reports
    final Logger log = LoggerFactory.getLogger(this.getClass());
    final ModelAndView model = new ModelAndView();
    
    
 // Method to display the index page of the application.
    @GetMapping(value= "/welcome")
    public ModelAndView index() {
         log.info("Showing the welcome page.");
         model.setViewName("welcome");
        return model;
    }
    
 // Method to create the pdf report via jasper framework.
    @GetMapping(value = "/view")
    public ModelAndView viewReport() {
        log.info("Preparing the pdf report via jasper.");
        try {
            createPdfReport(EmpleadoService.findAll());
            log.info("File successfully saved at the given path.");
        } catch (final Exception e) {
            log.error("Some error has occurred while preparing the employee pdf report.");
            e.printStackTrace();
        }
        // Returning the view name as the index page for ease.
        model.setViewName("welcome");
        return model;
    }
 
    // Method to create the pdf file using the employee list datasource.
    private void createPdfReport(final List<Empleado> empleados) throws JRException {
        // Fetching the .jrxml file from the resources folder.
        final InputStream stream = this.getClass().getResourceAsStream("/report.jrxml");
 
        // Compile the Jasper report from .jrxml to .japser
        final JasperReport report = JasperCompileManager.compileReport(stream);
 
        // Fetching the employees from the data source.
        final JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(empleados);
 
        // Adding the additional parameters to the pdf.
        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Grupo 8 Ing Web-TPF");
 
        // Filling the report with the employee data and additional parameters information.
        final JasperPrint print = JasperFillManager.fillReport(report, parameters, source);
 
        // Users can change as per their project requrirements or can take it as request input requirement.
        // For simplicity, this tutorial will automatically place the file under the "c:" drive.
        // If users want to download the pdf file on the browser, then they need to use the "Content-Disposition" technique.
        final String filePath = "d://";
        // Export the report to a PDF file.
        //JasperExportManager.exportReportToPdfFile(print, filePath + "Empleado_reporte.pdf");
        JRPdfExporter exporter = new JRPdfExporter();
        
///---------------------------------------
exporter.setExporterInput(new SimpleExporterInput(print));
exporter.setExporterOutput(
  new SimpleOutputStreamExporterOutput("employeeReport.pdf"));

SimplePdfReportConfiguration reportConfig
  = new SimplePdfReportConfiguration();
reportConfig.setSizePageToContent(true);
reportConfig.setForceLineBreakPolicy(false);

SimplePdfExporterConfiguration exportConfig
  = new SimplePdfExporterConfiguration();
exportConfig.setMetadataAuthor("baeldung");
exportConfig.setEncrypted(true);
exportConfig.setAllowedPermissionsHint("PRINTING");

exporter.setConfiguration(reportConfig);
exporter.setConfiguration(exportConfig);

exporter.exportReport();

    }
    ////////////////////////////////////////////////////
    //metodo del empleado

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

        Pageable pageRequest = PageRequest.of(page, 5);

        Page<Empleado> empleados = EmpleadoService.findAll(pageRequest);

        PageRender<Empleado> pageRender = new PageRender<Empleado>("/empleado/listar", empleados);
        model.addAttribute("titulo", "Listado de Empleados");
        model.addAttribute("empleados", empleados);
        model.addAttribute("page", pageRender);
        return "empleado/listar";
    }
    
    @GetMapping(value = "/form")
    public String crear(Map<String, Object> model) {

        Empleado empleado = new Empleado();
        model.put("empleado", empleado);
        model.put("titulo", "Formulario de Empleado");
        return "empleado/form";
    }

    @GetMapping(value = "/form/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

        Empleado empleado = null;

        if (id > 0) {
            empleado = EmpleadoService.findOne(id);
            if (empleado == null) {
                flash.addFlashAttribute("error", "El ID del Empleado no existe en la BBDD!");
                return "redirect:/empleado/listar";
            }
        } else {
            flash.addFlashAttribute("error", "El ID del Empleado no puede ser cero!");
            return "redirect:/empleado/listar";
        }
        model.put("empleado", empleado);
        model.put("titulo", "Editar Empleado");
        return "empleado/form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String guardar(@Valid Empleado empleado, BindingResult result, Model model, @RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Empleado");
            return "/empleado/form";
        }

        if (!foto.isEmpty()) {

            if (empleado.getId() != null && empleado.getId() > 0 && empleado.getFoto() != null
                    && empleado.getFoto().length() > 0) {

                uploadFileService.delete(empleado.getFoto());
            }

            String uniqueFilename = null;
            try {
                uniqueFilename = uploadFileService.copy(foto);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            //flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");

            empleado.setFoto(uniqueFilename);
        }

        String mensajeFlash = (empleado.getId() != null) ? "Empleado editado con éxito!" : "Empleado creado con éxito!";
        EmpleadoService.save(empleado);
        status.setComplete();
        flash.addFlashAttribute("success", mensajeFlash);
        return "redirect:/empleado/listar";
    }


    @RequestMapping(value = "/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
        if (id > 0) {
            EmpleadoService.delete(id);
            flash.addFlashAttribute("success", "Empleado eliminado con éxito!");
        }
        return "redirect:/empleado/listar";
    }
}

