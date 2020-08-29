package com.myproject.xsl.xslDriver;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.xml.resolver.tools.CatalogResolver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import net.sf.saxon.TransformerFactoryImpl;
 

public class transformDriver {
	
 private final Logger applogger = LoggerFactory.getLogger(transformDriver.class);
 
 public static String saxonTransform(String XML, String XSLT) throws TransformerException, ParserConfigurationException, SAXException, IOException{
	 
	 	 
	 TransformerFactoryImpl f = new TransformerFactoryImpl();
	 f.setAttribute("http://saxon.sf.net/feature/version-warning", Boolean.FALSE);
	 System.out.println(1);
	 Document document = null;
	 DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	 
	 dbf.setValidating(true);
	 dbf.setNamespaceAware(true);
	 dbf.setFeature("http://xml.org/sax/features/namespaces", false);
     dbf.setFeature("http://xml.org/sax/features/validation", false);
     dbf.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
     dbf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
	 
     
     System.out.println(2);
     
     File srcFile = new File(XML);
     System.out.println(srcFile);
     System.err.println(srcFile.getName());
     
     DocumentBuilder db = dbf.newDocumentBuilder();
     db.setEntityResolver(new CatalogResolver());
     
     
	 StreamSource xslSource = new StreamSource(new FileInputStream(XSLT));
	 //StreamSource xmlSource = new StreamSource(new FileInputStream(XML));
	 
	 
	 document = db.parse(XML);
	 System.out.println(3);
	 DOMSource domsource = new DOMSource(document);
	 
	 //File resultFile = File.createTempFile("", ".xml")
	 StreamResult output = new StreamResult(new ByteArrayOutputStream());
	 
	 Transformer t = f.newTransformer(xslSource);
	 
	 System.out.println(4);
	 t.transform(domsource, output);
	 System.out.println(5);
	 return output.getOutputStream().toString();
	 
 }
 
 
}
