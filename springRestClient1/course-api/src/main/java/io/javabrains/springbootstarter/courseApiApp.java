package io.javabrains.springbootstarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class courseApiApp {

	public static void main(String[] args) {
		
		SpringApplication.run(courseApiApp.class, args);

	}

}
