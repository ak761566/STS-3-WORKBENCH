package com.myproject.dataAnalytics;






import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App 
{
	
	private static Logger logger = LogManager.getLogger(App.class);
	
    public static void main( String[] args )
    {
    			
		  //logger.debug("This is debug message"); 
    	   
		 
    	ApplicationContext context = SpringApplication.run(App.class, args);
    	
    	logger.info("This is info message");
		  logger.warn("This is warn message");
		  logger.error("This is error message");
    	
        System.out.println( "Hello World!" );
    }
}
