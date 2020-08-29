package com.myspringBoot.App.restApiTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import com.myspringBoot.App.bean.HelloBean;

@SpringBootApplication
@ComponentScan(basePackages = {"com.myspringBoot.App"})
public class Application {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
		HelloBean bean = (HelloBean) ctx.getBean("hello");
		//bean.sayHello();
		//bean.postFile();
		bean.getStep1();
		bean.getStep2();
		bean.getStep3();
		
	}
	
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	
}
