package com.myspringBoot.App.bean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

@Component("hello")
public class HelloBean {

@Autowired	
RestTemplate restTemplate;

public void sayHello() {
	System.out.println("///////////////////////////");
	
	

}
//http://dummy.restapiexample.com/api/v1/employees

	/*
	 * public String getEmployees() {
	 * 
	 * HttpHeaders headers = new HttpHeaders(); //headers.add("user-key",
	 * "dc6ad99c243cbc435f7a0db1ce6f3a46");
	 * headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	 * HttpEntity<String> entity = new HttpEntity<String>(headers);
	 * 
	 * 
	 * 
	 * //return
	 * restTemplate.exchange("http://dummy.restapiexample.com/api/v1/employees",
	 * HttpMethod.GET, entity, String.class).getBody();
	 * 
	 * return restTemplate.exchange(
	 * "https://api.innodatalabs.com/v1/reference/ilabs.bib/f8328471-c839-4a08-8abf-5532ea1e4e9f.file",
	 * HttpMethod.GET, entity, String.class).getBody(); }
	 */

/*
 * Step 1: POST: https://api.innodatalabs.com/v1/documents/input/ + input file
 * return: Json
 * {
    "input_filename": "627a562f-0461-486c-ae58-8788ff485a14.file",
    "bytes_accepted": 3771
	}
 * read 'input_filename' and return it to step 2
 * 
 * Step 2: GET: https://api.innodatalabs.com/v1/reference/ilabs.bib/ + input_filename
 * return
 * 
 * {
    "task_id": "2a4f09d5-af93-4836-88d7-dd40bfc396b0",
    "output_filename": "627a562f-0461-486c-ae58-8788ff485a14.file",
    "document_output_url": "https://api.innodatalabs.com/v1/documents/output/627a562f-0461-486c-ae58-8788ff485a14.file",
    "task_status_url": "https://api.innodatalabs.com/v1/reference/ilabs.bib/2a4f09d5-af93-4836-88d7-dd40bfc396b0/status",
    "task_cancel_url": "https://api.innodatalabs.com/v1/reference/ilabs.bib/2a4f09d5-af93-4836-88d7-dd40bfc396b0/cancel"
   }
   
   
 *
 *
 *
 *
 */


public void postFile_old() {
//https://api.innodatalabs.com/v1/documents/input/ + input file	
MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<String, Object>();
bodyMap.add("user-file", getUserFileResource("C:/kumar/CAR/Input_CAR.xml"));



HttpHeaders headers = new HttpHeaders();	
headers.setContentType(MediaType.MULTIPART_FORM_DATA);
//headers.setContentType(MediaType.APPLICATION_XML);

headers.add("user-key", "dc6ad99c243cbc435f7a0db1ce6f3a46");
//headers.set(HttpHeaders.CONTENT_TYPE, "application/octet-stream");


HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(bodyMap, headers);

//restTemplate.getMessageConverters().add(new MappingJackson2XmlHttpMessageConverter());


//ResponseEntity<String> response = restTemplate.exchange("https://api.innodatalabs.com/v1/documents/input/", HttpMethod.POST, requestEntity, String.class);

ResponseEntity<String> response = restTemplate.postForEntity("https://api.innodatalabs.com/v1/documents/input/",
		getUserFileResource("C:/kumar/CAR/Input_CAR.xml"), String.class);
System.out.println("Response Status: " + response.getStatusCode());
System.out.println("Response Body: " + response.getBody());
}

public static Resource getUserFileResource(String filePath) {
	
	File file = new File(filePath);
	
	if (file.isFile())
	{
		System.out.println("Valid File");
	}
	
	return new FileSystemResource(file);
}

public void postFile_old_old() {
	
	HttpHeaders headers = new HttpHeaders();
	headers.set("user-key", "dc6ad99c243cbc435f7a0db1ce6f3a46");
	headers.setContentType(MediaType.MULTIPART_FORM_DATA);
	
	MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();
	body.add("file", getUserFileResource("C:/kumar/CAR/Input_CAR.xml"));
	
	HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
	
	try {
		
		ResponseEntity<String> response = restTemplate.exchange("https://api.innodatalabs.com/v1/documents/input/",
				HttpMethod.POST,
				requestEntity,
				String.class);
		
		System.out.println(response);
		
	}catch(HttpClientErrorException e) {
		
		e.printStackTrace();
	}
		
}


public void postFile() {
	
File sourceFile = new File("C:/kumar/CAR/Input_CAR.xml");

HttpHeaders headers = new HttpHeaders();
headers.set("user-key", "dc6ad99c243cbc435f7a0db1ce6f3a46");
//headers.setContentType(MediaType.MULTIPART_FORM_DATA);
//headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//headers.setContentType(MediaType.APPLICATION_XML);

BufferedReader input; 
DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
factory.setNamespaceAware(true);
try
{
	input = new BufferedReader(new InputStreamReader(new FileInputStream(sourceFile), "Utf-8"));
	
	DocumentBuilder builder = factory.newDocumentBuilder();
	//Document document = builder.parse(sourceFile);
	Document document = builder.parse(new InputSource(input));
	document.getDocumentElement().normalize();
	
	Writer out = new StringWriter();
	Transformer transformer = TransformerFactory.newInstance().newTransformer();
	transformer.setOutputProperty(OutputKeys.INDENT, "no");
	transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
	transformer.transform(new DOMSource(document), new StreamResult(out));
	
	//System.out.println(document.getDocumentElement().getNodeName());
	//System.out.println(document.getDocumentElement().getNamespaceURI());
	System.out.println(out.toString());
	
	
	headers.add("header", "value");
	//HttpEntity<Document> requestEntity = new HttpEntity<>(document, headers);
	HttpEntity<String> requestEntity = new HttpEntity<>(out.toString(), headers);
	
	ResponseEntity<String> response =
			 restTemplate.exchange("https://api.innodatalabs.com/v1/documents/input/",
			 HttpMethod.POST, requestEntity, String.class);
	
	 System.out.println(response.getStatusCode());
	 System.out.println(response.getBody());

}catch(Exception e)
{
 e.printStackTrace();	
}

//System.out.println(sourceFile);

//MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
//body.add("file", new FileSystemResource(sourceFile));

//HttpEntity<String> requestEntity = new HttpEntity<>(sourceFile.toString(), headers);


//Create a list for the message converters
//List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
//Add the String Message converter
//messageConverters.add(new StringHttpMessageConverter());
//Add the message converters to the restTemplate
//restTemplate.setMessageConverters(messageConverters);



		/*
		 * try {
		 * 
		 * 
		 * ResponseEntity<String> response =
		 * restTemplate.exchange("https://api.innodatalabs.com/v1/documents/input/",
		 * HttpMethod.POST, requestEntity, String.class);
		 * 
		 * 
		 * System.out.println(response.getStatusCode());
		 * System.out.println(response.getBody());
		 * 
		 * }catch(HttpClientErrorException e) {
		 * 
		 * e.printStackTrace(); }
		 */
	
}

public void getStep1() {
	
	HttpHeaders headers = new HttpHeaders(); 
	headers.add("user-key", "dc6ad99c243cbc435f7a0db1ce6f3a46");
	  headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	  HttpEntity<String> entity = new HttpEntity<String>(headers);
	  
	 //return restTemplate.exchange("https://api.innodatalabs.com/v1/reference/ilabs.bib/f8328471-c839-4a08-8abf-5532ea1e4e9f.file", HttpMethod.GET, entity, String.class).getBody();
	 
	 
	  		ResponseEntity<String> response =  
	  				restTemplate.exchange("https://api.innodatalabs.com/v1/reference/ilabs.bib/f954007f-3821-411e-8fa9-60cf7c563d9b.file"
			 , HttpMethod.GET, entity, String.class);
	  		
		System.out.println("Response Status: " + response.getStatusCode());
		System.out.println("Response Body: " + response.getBody());
}

//Get Status URL (From step 1 get Status URL)
public void getStep2() {
	
	HttpHeaders headers = new HttpHeaders(); 
	headers.add("user-key", "dc6ad99c243cbc435f7a0db1ce6f3a46");
	  headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	  HttpEntity<String> entity = new HttpEntity<String>(headers);
	  
	 //return restTemplate.exchange("https://api.innodatalabs.com/v1/reference/ilabs.bib/f8328471-c839-4a08-8abf-5532ea1e4e9f.file", HttpMethod.GET, entity, String.class).getBody();
	 
	 
	  		ResponseEntity<String> response =  
	  				restTemplate.exchange("https://api.innodatalabs.com/v1/reference/ilabs.bib/b0ba6a55-ec1d-4774-a15a-e6ff5a92c507/status"
			 , HttpMethod.GET, entity, String.class);
	  		
		System.out.println("Response Status: " + response.getStatusCode());
		System.out.println("Response Body: " + response.getBody());
}

//Check completed:true from the response of getStep2 then get output
public void getStep3() {
	
	HttpHeaders headers = new HttpHeaders(); 
	headers.add("user-key", "dc6ad99c243cbc435f7a0db1ce6f3a46");
	  headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	  HttpEntity<String> entity = new HttpEntity<String>(headers);
	  
	 //return restTemplate.exchange("https://api.innodatalabs.com/v1/reference/ilabs.bib/f8328471-c839-4a08-8abf-5532ea1e4e9f.file", HttpMethod.GET, entity, String.class).getBody();
	 
	 
	  		ResponseEntity<String> response =  
	  				restTemplate.exchange("https://api.innodatalabs.com/v1/documents/output/f954007f-3821-411e-8fa9-60cf7c563d9b.file"
			 , HttpMethod.GET, entity, String.class);
	  		
		System.out.println("Response Status: " + response.getStatusCode());
		System.out.println("Response Body: " + response.getBody());
}



}
