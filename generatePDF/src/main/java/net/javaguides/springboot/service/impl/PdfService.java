package net.javaguides.springboot.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.w3c.dom.Document;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextRenderer;

import net.javaguides.springboot.model.User;
import net.javaguides.springboot.service.UserService;

@Service
public class PdfService {
	private static  final String  PDF_RESOURCE = "/pdf-resources/";
	
	private SpringTemplateEngine springTemplateEngine;
	private UserService userService;
	
	@Autowired
	public PdfService(SpringTemplateEngine springTemplateEngine, UserService userService) {
		this.springTemplateEngine = springTemplateEngine;
		this.userService = userService;
	}
	
	public File generateUsuarioPerfilPdf(long id) throws IOException {
		Context context = getContextdataPdf(id);
		String html  = loadAndFillTemplate(context);
		String xhtml = convertToXhtml(html);
		return renderUsuarioDatosPdf(xhtml);
	}
	
	private String convertToXhtml(String html) {
		Tidy tidy = new Tidy();
		tidy.setXHTML(true);
		tidy.setIndentContent(true);
		tidy.setInputEncoding("UTF-8");
		tidy.setOutputEncoding("UTF-8");
		tidy.setSmartIndent(true);
		tidy.setShowWarnings(false);
		tidy.setQuiet(true);
		
		Document htmlDOM  = tidy.parseDOM(new ByteArrayInputStream(html.getBytes()), null);
		
		ByteArrayOutputStream out = new  ByteArrayOutputStream();
		
		tidy.pprint(htmlDOM, out);
		return out.toString();
	}
	
	private File renderUsuarioDatosPdf(String html) throws IOException {
		File  file = File.createTempFile("usuario", ".pdf");
		OutputStream outputStream = new FileOutputStream(file);
		ITextRenderer renderer = new ITextRenderer(20f * 4f / 3f, 20);
		renderer.setDocumentFromString(html, new ClassPathResource(PDF_RESOURCE).getURL().toExternalForm());
		renderer.layout();
		renderer.createPDF(outputStream);
		outputStream.close();
		file.deleteOnExit();
		return file;
	}
	
	private Context getContextdataPdf(long id) {
		User datos = this.userService.getUserById(id);
		Context context  = new Context();
		context.setVariable("user", datos);
		return context;
	}
	
	private String loadAndFillTemplate(Context  context) {
		return springTemplateEngine.process("usuarioPerfil", context);
	}
	
	
}
