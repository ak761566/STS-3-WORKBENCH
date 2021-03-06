package com.mytestapp.saxonTransform;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import javax.xml.transform.ErrorListener;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.URIResolver;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import net.sf.saxon.Controller;
import net.sf.saxon.TransformerFactoryImpl;
import net.sf.saxon.event.MessageWarner;

public class xmlTransformation {

	/*
	 * Basic XSLT Transformation implemetation application
	 * It has implementation of additional input while xsl transform
	 * And capturing of xsl message in log file
	 * */
	
	public static void saxonTransform(String xsltDir, String source, String otherSRC, String xslt, String dest) throws FileNotFoundException, TransformerException {
		
		final OutputStream os = new FileOutputStream(dest + "log.txt");
		
		TransformerFactoryImpl f = new net.sf.saxon.TransformerFactoryImpl();
		f.setAttribute("http://saxon.sf.net/feature/version-warning", Boolean.FALSE);
		
		StreamSource xslSrc = new StreamSource(new FileInputStream(xslt));
		
		Transformer t = f.newTransformer(xslSrc);
		t.setParameter("otherSRC", new StreamSource(new FileInputStream(otherSRC)));
		t.setErrorListener(new ErrorListener() {
			
			public void warning(TransformerException exception) throws TransformerException {
				String message;
				message ="Captured: " + exception.getLocalizedMessage();
				byte[] logbyte = message.getBytes();
				try {
					os.write(logbyte);
					
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				
			}
			
			public void fatalError(TransformerException exception) throws TransformerException {
				// TODO Auto-generated method stub
				warning(exception);
				
			}
			
			public void error(TransformerException exception) throws TransformerException {
				// TODO Auto-generated method stub
				warning(exception);
				
			}
		});			
		
		Controller controller = (Controller) t;
		controller.setMessageEmitter(new MessageWarner());
		
		StreamSource src = new StreamSource(new FileInputStream(source));
		StreamResult res = new StreamResult(new FileOutputStream(dest));
		
		t.transform(src, res);
		
	}
	
	
	
}
