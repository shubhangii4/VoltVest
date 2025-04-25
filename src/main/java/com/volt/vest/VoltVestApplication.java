package com.volt.vest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.volt.vest")
public class VoltVestApplication {

	private static final Logger logger = LoggerFactory.getLogger(VoltVestApplication.class);

	public static void main(String[] args) {
		logger.info("Starting VoltVest application...");
		SpringApplication.run(VoltVestApplication.class, args);
		logger.info("VoltVest application started successfully");
	}

}
