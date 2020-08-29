package com.myproject.xsl.xslDriver;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.xml.sax.SAXException;

@SpringBootApplication
@ComponentScan

public class App 
{
    public static void main( String[] args )
    {
        //System.out.println( "Hello World!" );
    	
    	ApplicationContext context = SpringApplication.run(App.class, args);
    	//transformDriver driver = context.getBean(transformDriver.class);
    	try {
    		String outXML = transformDriver.saxonTransform("C:\\kumar\\CAR\\25-11-2019\\Data\\CDINPUT\\30898887_1\\ItemFile\\ARTICLE_77264599_21-33_A5_c9cp01874c.xml", "C:\\kumar\\CAR\\25-11-2019\\XSLT\\RSC-conversion.xsl");
    		
    		//System.out.println(outXML);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
