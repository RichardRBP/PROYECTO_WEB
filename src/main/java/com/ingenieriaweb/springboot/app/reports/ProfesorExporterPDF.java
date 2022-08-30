package com.ingenieriaweb.springboot.app.reports;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.ingenieriaweb.springboot.app.models.entity.*;
import com.lowagie.text.pdf.*;
import com.lowagie.text.*;

public class ProfesorExporterPDF {
	
	private List<Profesor> listaProfesores;

	public ProfesorExporterPDF(List<Profesor> listaProfesores) {
		super();
		this.listaProfesores = listaProfesores;
	}

	private void escribirCabeceraTabla(PdfPTable tabla) {
		PdfPCell celda = new PdfPCell();
		
		celda.setBackgroundColor(Color.BLUE);
		celda.setPadding(5);
		
		Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
		fuente.setColor(Color.WHITE);
		
		celda.setPhrase(new Phrase("ID",fuente));
		tabla.addCell(celda); 
		
		celda.setPhrase(new Phrase("NIF",fuente));
		tabla.addCell(celda);
		
		celda.setPhrase(new Phrase("Nombre",fuente));
		tabla.addCell(celda);
		
		celda.setPhrase(new Phrase("Apellido ",fuente));
		tabla.addCell(celda); 
		
		celda.setPhrase(new Phrase("Telefono",fuente));
		tabla.addCell(celda); 
		
		celda.setPhrase(new Phrase("Email",fuente));
		tabla.addCell(celda); 
		
		celda.setPhrase(new Phrase("Titulacion",fuente));
		tabla.addCell(celda); 
		
		
		
		
	}
	
	private void escribirDatosTabla(PdfPTable tabla) {
		for(Profesor profesor : listaProfesores) {
			tabla.addCell(String.valueOf(profesor.getId()));
			tabla.addCell(profesor.getNif());
			tabla.addCell(profesor.getNombre());
			tabla.addCell(profesor.getApellido());
			tabla.addCell(profesor.getTelefono());
			tabla.addCell(profesor.getEmail());
			tabla.addCell(profesor.getTitulacion()); 
		}
	}
	
	public void exportar(HttpServletResponse response) throws DocumentException, IOException {
		Document documento = new Document(PageSize.A4);
		PdfWriter.getInstance(documento, response.getOutputStream());
		
		documento.open();
		
		Font fuente = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		fuente.setColor(Color.BLUE);
		fuente.setSize(18);
		
		Paragraph titulo = new Paragraph("Lista de Profesores", fuente);
		titulo.setAlignment(Paragraph.ALIGN_CENTER);
		documento.add(titulo);
		
		PdfPTable tabla = new PdfPTable(7);
		tabla.setWidthPercentage(100);
		tabla.setSpacingBefore(15);
		tabla.setWidths(new float[] {1.1f, 2f, 2.8f, 3f, 2.5f, 3f, 2f});
		tabla.setWidthPercentage(110);
		
		escribirCabeceraTabla(tabla);
		escribirDatosTabla(tabla);
		
		documento.add(tabla);
		documento.close();
	}
}
