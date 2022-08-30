package com.ingenieriaweb.springboot.app.reports;

import java.awt.Color; 
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.ingenieriaweb.springboot.app.models.entity.*;
import com.lowagie.text.pdf.*;
import com.lowagie.text.*;

public class AlumnoExporterPDF {
	
	private List<Alumno> listaAlumnos;

	public AlumnoExporterPDF(List<Alumno> listaAlumnos) {
		super();
		this.listaAlumnos = listaAlumnos;
	}

	private void escribirCabeceraTabla(PdfPTable tabla) {
		PdfPCell celda = new PdfPCell();
		
		celda.setBackgroundColor(Color.BLUE);
		celda.setPadding(5);
		
		Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
		fuente.setColor(Color.WHITE);
		
		celda.setPhrase(new Phrase("ID",fuente));
		tabla.addCell(celda); 
		
		celda.setPhrase(new Phrase("Nombres",fuente));
		tabla.addCell(celda);
		
		celda.setPhrase(new Phrase("Apellidos",fuente));
		tabla.addCell(celda);
		
		celda.setPhrase(new Phrase("Direccion ",fuente));
		tabla.addCell(celda); 
		
		celda.setPhrase(new Phrase("Telefono",fuente));
		tabla.addCell(celda); 
		
		celda.setPhrase(new Phrase("Email",fuente));
		tabla.addCell(celda); 
		
		celda.setPhrase(new Phrase("Dni",fuente));
		tabla.addCell(celda); 
		
		celda.setPhrase(new Phrase("Carrera",fuente));
		tabla.addCell(celda); 
		
		celda.setPhrase(new Phrase("Ciclo",fuente));
		tabla.addCell(celda); 
		
		
		
	}
	
	private void escribirDatosTabla(PdfPTable tabla) {
		
		for(Alumno alumno : listaAlumnos) {
			tabla.addCell(String.valueOf(alumno.getId()));
			tabla.addCell(alumno.getNombres());
			tabla.addCell(alumno.getApellidos());
			tabla.addCell(alumno.getDireccion());
			tabla.addCell(alumno.getTelefono());
			tabla.addCell(alumno.getEmail());
			tabla.addCell(alumno.getDni());
			tabla.addCell(alumno.getCarrera().getNombre());
			tabla.addCell(alumno.getTipoAlumno().getTarifas().get(0).getCiclo().getCiclo());
		}
	}
	
	public void exportar(HttpServletResponse response) throws DocumentException, IOException {
		Document documento = new Document(PageSize.A4);
		PdfWriter.getInstance(documento, response.getOutputStream());
		
		documento.open();
		
		Font fuente = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		fuente.setColor(Color.BLUE);
		fuente.setSize(18);
		 
		Paragraph titulo = new Paragraph("Lista de Alumnos", fuente);
		titulo.setAlignment(Paragraph.ALIGN_CENTER);
		documento.add(titulo);
		
		PdfPTable tabla = new PdfPTable(9);
		tabla.setWidthPercentage(100);
		tabla.setSpacingBefore(15);
		tabla.setWidths(new float[] {1.1f, 3f, 3f, 2.8f, 3f, 3.1f, 1.8f, 2f, 2f});
		tabla.setWidthPercentage(110); 
		 
		escribirCabeceraTabla(tabla);
		escribirDatosTabla(tabla );
		
		documento.add(tabla);
		documento.close();
	}
}
