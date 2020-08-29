package com.myprojects.restClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.myprojects.bean.PersonBean;

/**
 * Hello world!
 *
 */
@SpringBootApplication
//@ComponentScan(basePackages = "com.myprojects.bean.*")
public class App {

	
	  public static void main( String[] args ) {
	  
	  ApplicationContext context = SpringApplication.run(App.class, args);
	  
	  PersonBean bean = context.getBean(PersonBean.class); bean.getPersonDetails();
	  
	  
	  
		/*
		 * for(String name: context.getBeanDefinitionNames()) {
		 * System.out.println("Bean Name" + name); }
		 */
	  
	  }
	 
	//@Autowired
	//PersonBean bean;
	
	/*
	 * public static void main(String[] args) { SpringApplication.run(App.class,
	 * args); }
	 * 
	 * public void run(String... arg0) { System.out.println("**********");
	 * bean.getPersonDetails(); }
	 */
	/*
	 * x public static void main(String[] args) { SpringApplication.run(App.class,
	 * args); }
	 * 
	 * @Bean public PersonBean getPersonBean() { return new PersonBean(); }
	 * 
	 * @Override public void run(String... args) throws Exception{
	 * getPersonBean().getPersonDetails(); }
	 */
}
