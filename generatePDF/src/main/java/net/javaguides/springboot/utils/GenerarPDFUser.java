package net.javaguides.springboot.utils;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import net.javaguides.springboot.model.User;

@Component("/{id}")
public class GenerarPDFUser extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		@SuppressWarnings("unchecked")
		User user = (User) model.get("user");
		
		PdfPTable datosUser = new PdfPTable(7);
		
		datosUser.addCell(String.valueOf( user.getId()));
		datosUser.addCell(user.getCentro());
		datosUser.addCell(user.getDni());
		datosUser.addCell(user.getProvincia());
		datosUser.addCell(user.getIdiomas());
		datosUser.addCell(user.getNombre());
		
		document.add(datosUser);
		
	}

}
