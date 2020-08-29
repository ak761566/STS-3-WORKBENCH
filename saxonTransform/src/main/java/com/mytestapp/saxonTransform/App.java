package com.mytestapp.saxonTransform;

import java.io.FileNotFoundException;

import javax.xml.transform.TransformerException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws FileNotFoundException, TransformerException
    {
        //System.out.println( "Hello World!" );
    	String dir="C:\\kumar\\CAR-Project\\TEST\\input";
		String src = "C:\\kumar\\CAR-Project\\TEST\\input\\Anesthesiology_13_1_1_2020.xml";
		String xslt = "C:\\kumar\\CAR-Project\\TEST\\xsl\\simple.xsl";
		String dest = "C:\\kumar\\CAR-Project\\TEST\\output\\output.xml";
		String otherSrc = "C:\\kumar\\CAR-Project\\TEST\\input\\otherSRC.xml";
		
		
		xmlTransformation.saxonTransform(dir, src, otherSrc, xslt, dest);
    }
}
