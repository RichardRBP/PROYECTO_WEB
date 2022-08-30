package com.ingenieriaweb.springboot.app.reports;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.ingenieriaweb.springboot.app.models.entity.*;
import com.lowagie.text.pdf.*;
import com.lowagie.text.*;

public class EmpleadoExporterPDF {
	
	private List<Empleado> listaEmpleados;

	public EmpleadoExporterPDF(List<Empleado> listaEmpleados) {
		super();
		this.listaEmpleados = listaEmpleados;
	}

	private void escribirCabeceraTabla(PdfPTable tabla) {
		PdfPCell celda = new PdfPCell();
		
		celda.setBackgroundColor(Color.BLUE);
		celda.setPadding(5);
		
		Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
		fuente.setColor(Color.WHITE);
		
		celda.setPhrase(new Phrase("ID",fuente));
		tabla.addCell(celda); 
		
		celda.setPhrase(new Phrase("Cargo",fuente));
		tabla.addCell(celda);
		
		celda.setPhrase(new Phrase("Nombres",fuente));
		tabla.addCell(celda);
		
		celda.setPhrase(new Phrase("Apellidos ",fuente));
		tabla.addCell(celda); 
		
		celda.setPhrase(new Phrase("Telefono",fuente));
		tabla.addCell(celda); 
		
		celda.setPhrase(new Phrase("Email",fuente));
		tabla.addCell(celda); 
		
		celda.setPhrase(new Phrase("DNI",fuente));
		tabla.addCell(celda); 
		
		celda.setPhrase(new Phrase("Direccion",fuente));
		tabla.addCell(celda); 
		
		
		
		
	}
	
	private void escribirDatosTabla(PdfPTable tabla) {
		for(Empleado empleado : listaEmpleados) {
			tabla.addCell(String.valueOf(empleado.getId()));
			tabla.addCell(empleado.getCargo());
			tabla.addCell(empleado.getNombres());
			tabla.addCell(empleado.getApellidos());
			tabla.addCell(empleado.getTelefono());
			tabla.addCell(empleado.getEmail());
			tabla.addCell(empleado.getDni());
			tabla.addCell(empleado.getDireccion());
		}
	}
	
	public void exportar(HttpServletResponse response) throws DocumentException, IOException {
		Document documento = new Document(PageSize.A4);
		PdfWriter.getInstance(documento, response.getOutputStream());
		
		documento.open();
		
		Font fuente = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		fuente.setColor(Color.BLUE);
		fuente.setSize(18);
		
		Paragraph titulo = new Paragraph("Lista de Empleados", fuente);
		titulo.setAlignment(Paragraph.ALIGN_CENTER);
		documento.add(titulo);
		
		PdfPTable tabla = new PdfPTable(8);
		tabla.setWidthPercentage(100);
		tabla.setSpacingBefore(15);
		tabla.setWidths(new float[] {1.1f, 2f, 2.8f, 3f, 2.5f, 3f, 2f, 2f});
		tabla.setWidthPercentage(110);
		
		escribirCabeceraTabla(tabla);
		escribirDatosTabla(tabla);
		
		documento.add(tabla);
		documento.close();
	}
}
