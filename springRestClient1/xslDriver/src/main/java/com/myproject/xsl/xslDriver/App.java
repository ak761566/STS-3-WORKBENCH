package com.myproject.xsl.xslDriver;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.xml.sax.SAXException;

@SpringBootApplication
@ComponentScan

public class App 
{
	private static final Logger applogger = LoggerFactory.getLogger(App.class);
	
    public static void main( String[] args )
    {
        //System.out.println( "Hello World!" );
    	//applogger.info("applogger info message");
    	//applogger.warn("applogger warn message");
    	//applogger.error("applogger error message");
    	//applogger.debug("applogger debug message");
    	System.setProperty("USER_HOME", "C:\\logback-log\\");
    	
    	String msg = "logging test";
    	
    	applogger.info("Parameter test {}", msg);
    	applogger.info("USER HOME {}", System.getProperty("USER_HOME"));
    	applogger.debug("debug message");
    	applogger.warn("warnning message");
    	//ApplicationContext context = SpringApplication.run(App.class, args);
    	//transformDriver driver = context.getBean(transformDriver.class);
		
		/*
		 * try {
		 * 
		 * String outXML = transformDriver.saxonTransform(
		 * "C:\\kumar\\CAR\\25-11-2019\\Data\\CDINPUT\\30898887_1\\ItemFile\\ARTICLE_77264599_21-33_A5_c9cp01874c.xml",
		 * "C:\\kumar\\CAR\\25-11-2019\\XSLT\\RSC-conversion.xsl");
		 * //applogger.info(outXML); //System.out.println(outXML); } catch
		 * (FileNotFoundException e){ e.printStackTrace(); } catch (TransformerException
		 * e) { e.printStackTrace(); } catch (ParserConfigurationException e) {
		 * e.printStackTrace(); } catch (SAXException e){ e.printStackTrace(); } catch
		 * (IOException e){ e.printStackTrace(); }
		 */

    }
}
