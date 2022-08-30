package com.ingenieriaweb.springboot.app.reports;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.ingenieriaweb.springboot.app.models.entity.*;
import com.lowagie.text.pdf.*;
import com.lowagie.text.*;

public class MatriculaExporterPDF {
	
	private List<Matricula> listaMatriculas;

	public MatriculaExporterPDF(List<Matricula> listaMatriculas) {
		super();
		this.listaMatriculas = listaMatriculas;
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
		
		celda.setPhrase(new Phrase("Codigo ",fuente));
		tabla.addCell(celda); 
		
		celda.setPhrase(new Phrase("Fecha",fuente));
		tabla.addCell(celda); 
		
		celda.setPhrase(new Phrase("Banco",fuente));
		tabla.addCell(celda); 
		
		celda.setPhrase(new Phrase("Monto",fuente));
		tabla.addCell(celda); 
		
		celda.setPhrase(new Phrase("Turno",fuente));
		tabla.addCell(celda); 
		
		celda.setPhrase(new Phrase("Tarifa",fuente));
		tabla.addCell(celda); 
		
		
		
	}
	
	private void escribirDatosTabla(PdfPTable tabla) {
		for(Matricula matricula : listaMatriculas) {
			tabla.addCell(String.valueOf(matricula.getId()));
			tabla.addCell(matricula.getAlumno().getNombres());
			tabla.addCell(matricula.getAlumno().getApellidos());
			tabla.addCell(String.valueOf(matricula.getCodigoOperacion()));
			tabla.addCell(matricula.getFechaOperacion());
			tabla.addCell(matricula.getBanco());
			tabla.addCell(String.valueOf(matricula.getMonto()));
			tabla.addCell(matricula.getTurno());
			tabla.addCell(String.valueOf(matricula.getTarifa().getPrecio()));
		}
	}
	
	public void exportar(HttpServletResponse response) throws DocumentException, IOException {
		Document documento = new Document(PageSize.A4);
		PdfWriter.getInstance(documento, response.getOutputStream());
		
		documento.open();
		
		Font fuente = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		fuente.setColor(Color.BLUE);
		fuente.setSize(18);
		
		Paragraph titulo = new Paragraph("Lista de Matriculas", fuente);
		titulo.setAlignment(Paragraph.ALIGN_CENTER);
		documento.add(titulo);
		
		PdfPTable tabla = new PdfPTable(9);
		tabla.setWidthPercentage(100);
		tabla.setSpacingBefore(15);
		tabla.setWidths(new float[] {1.1f, 3f, 3f, 2.8f, 3f, 3.1f, 1.8f, 2f, 2f});
		tabla.setWidthPercentage(110);
		
		escribirCabeceraTabla(tabla);
		escribirDatosTabla(tabla);
		
		documento.add(tabla);
		documento.close();
	}
}
