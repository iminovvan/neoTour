package com.neo.neoTour;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class NeoTourApplication {

	public static void main(String[] args) {
		SpringApplication.run(NeoTourApplication.class, args);
	}

}
