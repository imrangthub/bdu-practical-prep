package com.imranmabar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	Logger logger = LoggerFactory.getLogger(HomeController.class);

	@GetMapping("/")
	public String index() {
		logger.info("Hello from SpringMysqlApplication");
		return "Hello from SpringMysqlApplication";
	}
	
	
	@GetMapping("/home")
	public String home() {
		logger.info("Welcome to Home, SpringMysqlApplication");
		return "Welcome to Home, SpringMysqlApplication";
	}
	
	
	
}
