package com.oputechlab.mastering;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MasterSpringBootApplication {

	private static final Logger logger = LoggerFactory.getLogger(MasterSpringBootApplication.class);

	public static void main(String[] args) {
		logger.error("Your machine is starting");
		SpringApplication.run(MasterSpringBootApplication.class, args);
	}

}
