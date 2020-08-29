package com.myproject.spring.extendXsl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class MLApiClient {
	
	//static RestTemplate restTemplate = new RestTemplate();
	
	public static String getClassName() {
		return testClass.class.getCanonicalName();
	}
	
	
	public static int testMe(int i , int y) {
		
		return i + y;
	}
	
	
	/*
	 * public static String postHttpClient() {
	 * 
	 * String xmlString =
	 * "<?xml version=\"1.0\" encoding=\"utf-8\"?><brs:r>Centers for Disease Control and Prevention. Viral Hepatitis-Hepatitis C Information [Internet]. 2016. Available from: http://www.cdc.gov/hepatitis/hcv/hcvfaq.htm. Cited 9 Jun 2016.</brs:r>"
	 * ; HttpClient client = new DefaultHttpClient();
	 * 
	 * HttpPost post = new
	 * HttpPost("https://api.innodatalabs.com/v1/documents/input/");
	 * post.setHeader("user-key", "dc6ad99c243cbc435f7a0db1ce6f3a46");
	 * post.setHeader("content-type", "application/xml"); try { StringEntity input =
	 * new StringEntity(xmlString); //input.setContentType("application/xml");
	 * 
	 * 
	 * post.setEntity(input);
	 * 
	 * HttpResponse response = client.execute(post); BufferedReader br = new
	 * BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	 * 
	 * String strResponse = new String(); //System.out.println(5); for (String line;
	 * (line = br.readLine()) != null; strResponse += line );
	 * 
	 * return strResponse;
	 * 
	 * } catch (Exception e) {
	 * 
	 * 
	 * e.printStackTrace(); return "Error"; }
	 * 
	 * }
	 */
	
	public static String post_badBibData(String inputString) {
	//public static String postNet_badData() {
		//String xmlString = "<?xml version=\"1.0\" encoding=\"utf-8\"?><brs:b xmlns:brs=\"http://innodatalabs.com/brs\"><brs:r>Centers for Disease Control and Prevention. Viral Hepatitis-Hepatitis C Information [Internet]. 2016. Available from: http://www.cdc.gov/hepatitis/hcv/hcvfaq.htm. Cited 9 Jun 2016.</brs:r></brs:b>";
		String xmlString = "<?xml version=\"1.0\" encoding=\"utf-8\"?><brs:b xmlns:brs=\"http://innodatalabs.com/brs\"><brs:r>"+ inputString +"</brs:r></brs:b>";	
		
		//System.out.println(xmlString);
		
		try {
		
		URL url  = new URL("https://api.innodatalabs.com/v1/documents/input/");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
		// Set DoOutput to true if you want to use URLConnection for output.
		// Default is false
		conn.setDoOutput(true);
		conn.setUseCaches(true);
		
		conn.setRequestMethod("POST");
		
		// Set Headers
		conn.setRequestProperty("Content-Type", "application/xml");
		conn.setRequestProperty("user-key", "dc6ad99c243cbc435f7a0db1ce6f3a46");
		
		//System.out.println(1);
		// Write XML
		OutputStream os = conn.getOutputStream();
		byte[] b = xmlString.getBytes("UTF-8");
		os.write(b);
		os.flush();
		os.close();
		
		//System.out.println(2);
			/*
			 * if(conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
			 * 
			 * System.out.println(3); throw new RuntimeException("Failed: HTTPError code: "
			 * + conn.getResponseCode()); }
			 */
		
		//JSONObject jsonObject; 
		
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		
		//System.out.println(4);
		String strResponse = new String();
		//System.out.println(5);
		for (String line; (line = br.readLine()) != null; strResponse += line );
		
		//jsonObject = new JSONObject(strResponse);
		
		String json = strResponse.toString();
		Pattern codePattern = Pattern.compile("\"input_filename\": \"(.*\\.file)\"");
		Matcher inputFile_matcher = codePattern.matcher(json);
		String fileName = null;
		if(inputFile_matcher.find()) {
			fileName = inputFile_matcher.group(1); 
		}

		
		//String fileName = (String) jsonObject.get("input_filename");
		
		//Step 1
		//System.out.println("FileName: " + fileName);
		String taskStatusUrl = getReq_ProcessBib(fileName);
		
		//Step 2
		//System.out.println("Task Status URL: " + taskStatusUrl);
		String statusResponse = getReq_APIConversionStatus(taskStatusUrl);
		
		//JSONObject jsonTaskStatusObj = new JSONObject(statusResponse);
		
			/*
			 * { "completed": true, "progress": 4, "steps": 4, "message": null, "result":
			 * null }
			 */
		
		String jsonTaskStatusObj = statusResponse;
		//System.out.println("jsonTaskStatusObj: " + jsonTaskStatusObj);
		Pattern stausPattern = Pattern.compile("\"completed\": (true|false)");
		Matcher status_matcher = stausPattern.matcher(jsonTaskStatusObj);
		String status = null;
		if(status_matcher.find()) {
			status = status_matcher.group(1); 
			//System.out.println(status + ": status");
		}
			
			
			
			  while(!(status.equalsIgnoreCase("true")))
			  {
			  
			  statusResponse = getReq_APIConversionStatus(taskStatusUrl);
			  //jsonTaskStatusObj = new JSONObject(statusResponse);
			  
			  jsonTaskStatusObj = statusResponse; 
			  stausPattern = Pattern.compile("\"completed\": (true|false)");
			  status_matcher = stausPattern.matcher(jsonTaskStatusObj);
			  status = null;
			  if(status_matcher.find())
			  { 
				  status = status_matcher.group(1);
				  //System.out.println("in While status " + status);
			   } 
			  }
			 			 
			 
	    //Step 3
		String out = getReq_bibOuput(fileName);
		
		//return fileName;
		//return taskStatusUrl;
		//return status + " " + taskStatusUrl;
		return  out;
 		
		}catch(Exception e) {
			
			e.printStackTrace();
			return "<?xml version=\"1.0\" encoding=\"utf-8\"?><Error><message>Request hasn't got processed..Please research</message><input>" + inputString + "</input></Error>";
		}
	}
	
	
	public static String post_badNoteData(String inputString) {
		//public static String postNet_badData() {
			//String xmlString = "<?xml version=\"1.0\" encoding=\"utf-8\"?><brs:b xmlns:brs=\"http://innodatalabs.com/brs\"><brs:r>Centers for Disease Control and Prevention. Viral Hepatitis-Hepatitis C Information [Internet]. 2016. Available from: http://www.cdc.gov/hepatitis/hcv/hcvfaq.htm. Cited 9 Jun 2016.</brs:r></brs:b>";
		  String xmlString = "<?xml version=\"1.0\" encoding=\"utf-8\"?><brs:b xmlns:brs=\"http://innodatalabs.com/brs\"><brs:r>"+ inputString +"</brs:r></brs:b>";	
			
			//System.out.println(xmlString);
			
			try {
			
			URL url  = new URL("https://api.innodatalabs.com/v1/documents/input/");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			// Set DoOutput to true if you want to use URLConnection for output.
			// Default is false
			conn.setDoOutput(true);
			conn.setUseCaches(true);
			
			conn.setRequestMethod("POST");
			
			// Set Headers
			conn.setRequestProperty("Content-Type", "application/xml");
			conn.setRequestProperty("user-key", "dc6ad99c243cbc435f7a0db1ce6f3a46");
			
			//System.out.println(1);
			// Write XML
			OutputStream os = conn.getOutputStream();
			byte[] b = xmlString.getBytes("UTF-8");
			os.write(b);
			os.flush();
			os.close();
			
			//System.out.println(2);
				/*
				 * if(conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
				 * 
				 * System.out.println(3); throw new RuntimeException("Failed: HTTPError code: "
				 * + conn.getResponseCode()); }
				 */
			
			//JSONObject jsonObject; 
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			//System.out.println(4);
			String strResponse = new String();
			//System.out.println(5);
			for (String line; (line = br.readLine()) != null; strResponse += line );
			
			//jsonObject = new JSONObject(strResponse);
			
			String json = strResponse.toString();
			Pattern codePattern = Pattern.compile("\"input_filename\": \"(.*\\.file)\"");
			Matcher inputFile_matcher = codePattern.matcher(json);
			String fileName = null;
			if(inputFile_matcher.find()) {
				fileName = inputFile_matcher.group(1); 
			}

			
			//String fileName = (String) jsonObject.get("input_filename");
			
			//Step 1
			
			String taskStatusUrl = getReq_ProcessAff(fileName);
			
			//Step 2
			
			String statusResponse = getReq_APIConversionStatus(taskStatusUrl);
			
			String jsonTaskStatusObj = statusResponse;
			//System.out.println("jsonTaskStatusObj: " + jsonTaskStatusObj);
			Pattern stausPattern = Pattern.compile("\"completed\": (true|false)");
			Matcher status_matcher = stausPattern.matcher(jsonTaskStatusObj);
			String status = null;
			if(status_matcher.find()) {
				status = status_matcher.group(1); 
				//System.out.println(status + ": status");
			}
				
				
				
				  while(!(status.equalsIgnoreCase("true")))
				  {
				  
				  statusResponse = getReq_APIConversionStatus(taskStatusUrl);
				  //jsonTaskStatusObj = new JSONObject(statusResponse);
				  
				  jsonTaskStatusObj = statusResponse; 
				  stausPattern = Pattern.compile("\"completed\": (true|false)");
				  status_matcher = stausPattern.matcher(jsonTaskStatusObj);
				  status = null;
				  if(status_matcher.find())
				  { 
					  status = status_matcher.group(1);
					  //System.out.println("in While status " + status);
				   } 
				  }
				 			 
				 
		    //Step 3
			String out = getReq_bibOuput(fileName);
			
			//return fileName;
			//return taskStatusUrl;
			//return status + " " + taskStatusUrl;
			return  out;
	 		
			}catch(Exception e) {
				
				e.printStackTrace();
				return "Error in Post post_badNoteData Request";
			}
		}
	
		
	public static String getReq_ProcessBib(String fileName) {
		
		String urlString = "https://api.innodatalabs.com/v1/reference/ilabs.bib/" + fileName;
		
		//System.out.println("urlString: " + urlString);
		
		try {
			URL url  = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			
			// Set Headers
			conn.setRequestProperty("Content-Type", "application/xml");
			conn.setRequestProperty("user-key", "dc6ad99c243cbc435f7a0db1ce6f3a46");
			
			int responseCode = conn.getResponseCode();
			//System.out.println("Get Response Code: " + responseCode);
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String line;
			
			StringBuffer response = new StringBuffer();
			
			if (responseCode == HttpURLConnection.HTTP_ACCEPTED) {
				while((line = br.readLine()) != null){
				response.append(line);	
				}
				
				br.close();
				
				
				/*
				 * 
				 *  String json = str1;  // sample json string
        			Pattern codePattern = Pattern.compile("\"code\"\\s*:\\s*\"([^,]*)\",");
        			Pattern messagePattern = Pattern.compile("\"message\"\\s*:\\s*\"([^,]*)\",");
        			Pattern statusPattern = Pattern.compile("\"status\"\\s*:\\s*\"(FAILURE)\"");

        			Matcher code_matcher = codePattern.matcher(json);
        			Matcher message_matcher = messagePattern.matcher(json);
        			Matcher status_matcher = statusPattern.matcher(json);

        			if (code_matcher.find() && message_matcher.find() && status_matcher.find()) {

            		System.out.println("\nException found!");

            		System.out.println("\n" + code_matcher.group(1));
            		System.out.println("\n" + message_matcher.group(1));
            		System.out.println("\n" + status_matcher.group(1));
                     }

				 */
				
				//JSONObject jsonObject = new JSONObject(response.toString());
				
				//String taskStatusUrl = (String) jsonObject.get("task_status_url");
				//"task_status_url": "https://api.innodatalabs.com/v1/reference/ilabs.bib/ca91f415-087f-499f-ae8f-c387073c5f10/status",
				String JsontaskStatusUrl = response.toString();
				Pattern codePattern = Pattern.compile("\"task_status_url\": \"(http.*[\\/]status)\"");
				Matcher inputFile_matcher = codePattern.matcher(JsontaskStatusUrl);
				String taskStatusUrl = null;
				if(inputFile_matcher.find()) {
					taskStatusUrl = inputFile_matcher.group(1); 
				}
				
				
				//System.out.println("Get Response: " + response.toString());
				return taskStatusUrl;
			}else {
				
				return response.append("Error in Get getReq_ProcessBib Request").toString();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Error in Get getReq_ProcessBib 1 Request";
		}
		
		
		
	}
	
	
public static String getReq_ProcessAff(String fileName) {
		
		String urlString = "https://api.innodatalabs.com/v1/reference/ilabs.note/" + fileName;
		
		//System.out.println("urlString: " + urlString);
		
		try {
			URL url  = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			
			// Set Headers
			conn.setRequestProperty("Content-Type", "application/xml");
			conn.setRequestProperty("user-key", "dc6ad99c243cbc435f7a0db1ce6f3a46");
			
			int responseCode = conn.getResponseCode();
			//System.out.println("Get Response Code: " + responseCode);
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String line;
			
			StringBuffer response = new StringBuffer();
			
			if (responseCode == HttpURLConnection.HTTP_ACCEPTED) {
				while((line = br.readLine()) != null){
				response.append(line);	
				}
				
				br.close();
			
				String JsontaskStatusUrl = response.toString();
				Pattern codePattern = Pattern.compile("\"task_status_url\": \"(http.*[\\/]status)\"");
				Matcher inputFile_matcher = codePattern.matcher(JsontaskStatusUrl);
				String taskStatusUrl = null;
				if(inputFile_matcher.find()) {
					taskStatusUrl = inputFile_matcher.group(1); 
				}
				
				
				//System.out.println("Get Response: " + response.toString());
				return taskStatusUrl;
			}else {
				
				return response.append("Error in Get getReq_ProcessAff Request").toString();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Error in Get getReq_ProcessAff 1 Request";
		}
		
		
		
	}
	
	
	public static String getReq_APIConversionStatus(String statusUrl) {
		
		String urlString = statusUrl;
		
		try {
			URL url  = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			
			// Set Headers
			conn.setRequestProperty("Content-Type", "application/xml");
			conn.setRequestProperty("user-key", "dc6ad99c243cbc435f7a0db1ce6f3a46");
			
			int responseCode = conn.getResponseCode();
			//System.out.println("Get Response Code: " + responseCode);
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String line;
			
			StringBuffer response = new StringBuffer();
			
			if (responseCode == HttpURLConnection.HTTP_OK) {
				while((line = br.readLine()) != null){
				response.append(line);	
				}
				
				br.close();
				
				//JSONObject jsonObject = new JSONObject(response.toString());
				
				//String taskStatusUrl = (String) jsonObject.get("completed");
				
				//System.out.println("Get Response: " + response.toString());
				return response.toString();
			}else {
				
				return response.append("Error in Get getReq_APIConversionStatus Request").toString();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Error in Get getReq_APIConversionStatus_1 Request";
		}
		
		//return null;
	}
	
	
	public static String getReq_bibOuput(String fileName) {
			String urlString = "https://api.innodatalabs.com/v1/documents/output/" + fileName;
		
		//System.out.println("urlString: " + urlString);
		
		try {
			URL url  = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			
			// Set Headers
			conn.setRequestProperty("Content-Type", "application/xml");
			conn.setRequestProperty("user-key", "dc6ad99c243cbc435f7a0db1ce6f3a46");
			
			int responseCode = conn.getResponseCode();
			//System.out.println("Get Response Code: " + responseCode);
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String line;
			
			StringBuffer response = new StringBuffer();
			
			if (responseCode == HttpURLConnection.HTTP_OK) {
				while((line = br.readLine()) != null){
				response.append(line);	
				}
				
				br.close();
				
				//JSONObject jsonObject = new JSONObject(response.toString());
				
				//String taskStatusUrl = (String) jsonObject.get("task_status_url");
				
				//System.out.println("Get Response: " + response.toString());
				return response.toString();
			}else {
				
				return response.append("Error in Get getReq_bibOuput Request").toString();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Error in Get getReq_bibOuput 1 Request";
		}
		
	}


}
