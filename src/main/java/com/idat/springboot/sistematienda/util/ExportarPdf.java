package com.idat.springboot.sistematienda.util;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import com.idat.springboot.sistematienda.entity.Utiles;
import com.idat.springboot.sistematienda.entity.Venta;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("ventas/ventas")

public class ExportarPdf extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		@SuppressWarnings("unchecked")
		List<Venta> listaVentas = (List<Venta>) model.get("ventas");


		document.setPageSize(PageSize.LETTER.rotate());
		document.open();

		PdfPTable tablaImagen = new PdfPTable(1);
		PdfPCell celda = null;
		
		celda = new PdfPCell(new Phrase("/images/logo.png"));
		tablaImagen.addCell(celda);
		
		PdfPTable tablaTitulo = new PdfPTable(1);

		Font fuenteTitulo = FontFactory.getFont("Helvetica", 16, Color.darkGray);

		celda = new PdfPCell(new Phrase("REPORTE DE VENTAS", fuenteTitulo));
		celda.setBorder(0);
		celda.setBackgroundColor(new Color(94, 80, 249));
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(30);

		tablaTitulo.addCell(celda);
		tablaTitulo.setSpacingAfter(30);

		PdfPTable tablaInfo = new PdfPTable(2);
		celda = new PdfPCell(new Phrase("NOMBRE DE LA EMPRESA: "));
		celda.setBorder(0);
		tablaInfo.addCell(celda);

		celda = new PdfPCell(new Phrase("LOS GEMELOS"));
		celda.setBorder(0);
		tablaInfo.addCell(celda);

		tablaInfo.setSpacingAfter(20);

		PdfPTable tablaInfo2 = new PdfPTable(4);
		celda = new PdfPCell(new Phrase("FECHA: "));
		celda.setBorder(0);
		tablaInfo2.addCell(celda);

		celda = new PdfPCell(new Phrase(Utiles.obtenerFechaYHoraActual()));
		celda.setBorder(0);
		tablaInfo2.addCell(celda);

		celda = new PdfPCell(new Phrase("DUEÑO: "));
		celda.setBorder(0);
		tablaInfo2.addCell(celda);

		celda = new PdfPCell(new Phrase("Jose Clodomiro Arancibia Viera"));
		celda.setBorder(0);
		tablaInfo2.addCell(celda);

		tablaInfo2.setSpacingAfter(20);

		PdfPTable tablaVentas = new PdfPTable(6);

		celda = new PdfPCell(new Phrase("CODIGO"));
		tablaVentas.addCell(celda);

		celda = new PdfPCell(new Phrase("FECHA"));
		tablaVentas.addCell(celda);

		celda = new PdfPCell(new Phrase("EMPLEADO"));
		tablaVentas.addCell(celda);

		celda = new PdfPCell(new Phrase("PAGO"));
		tablaVentas.addCell(celda);

		celda = new PdfPCell(new Phrase("TOTAL"));
		tablaVentas.addCell(celda);

		celda = new PdfPCell(new Phrase("CLIENTE"));
		tablaVentas.addCell(celda);

		for (Venta ventas : listaVentas) {
			celda = new PdfPCell(new Phrase(ventas.getCodigo()));
			tablaVentas.addCell(celda);

			celda = new PdfPCell(new Phrase(ventas.getFecha()));
			tablaVentas.addCell(celda);

			celda = new PdfPCell(new Phrase(ventas.getEmpleado()));
			tablaVentas.addCell(celda);

			celda = new PdfPCell(new Phrase(ventas.getPago()));
			tablaVentas.addCell(celda);

			celda = new PdfPCell(new Phrase(ventas.getTotal().toString()));
			tablaVentas.addCell(celda);

			celda = new PdfPCell(new Phrase(ventas.getCliente()));
			tablaVentas.addCell(celda);

		}
		;

		PdfPTable tablaTotal = new PdfPTable(2);

		celda = new PdfPCell(new Phrase("TOTAL :"));
		tablaTotal.addCell(celda);
		float total2 = 0;
		for (Venta ventas : listaVentas) {
			float total = ventas.getTotal();
			total2 = total + total2;
			celda = new PdfPCell(new Phrase(String.valueOf(total2)));
		}
		tablaTotal.addCell(celda);

		document.add(tablaTitulo);
		document.add(tablaInfo);
		document.add(tablaInfo2);
		document.add(tablaVentas);
		document.add(tablaTotal);

	}
}
