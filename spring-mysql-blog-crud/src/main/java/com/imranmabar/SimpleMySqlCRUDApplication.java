package com.imranmabar;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SimpleMySqlCRUDApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SimpleMySqlCRUDApplication.class, args);
		System.out.println("Simple MySql CRUD Application Run Successfully Done !");
	}

}
