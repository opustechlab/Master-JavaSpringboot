package com.oputechlab.belajarcrud3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Belajarcrud3Application {

	private static final Logger logger = LoggerFactory.getLogger(Belajarcrud3Application.class);

	public static void main(String[] args) {

		logger.info("Coba log ke Google Cloud Logging!");
		logger.error("Ada error di aplikasi!");
		SpringApplication.run(Belajarcrud3Application.class, args);
	}

}
