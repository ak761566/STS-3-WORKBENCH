package org.myprojects.springRestClient.boot;

import org.myprojects.springRestClient.bean.testBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class mainApp {
	
	public static void main(String[] arg) {
		ApplicationContext ctx = SpringApplication.run(mainApp.class, arg);
		
		testBean testObj = ctx.getBean(testBean.class);
		testObj.test();
	}

}
