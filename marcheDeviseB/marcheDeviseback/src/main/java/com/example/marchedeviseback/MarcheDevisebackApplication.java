package com.example.marchedeviseback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling  // <-- Make sure this is present
public class MarcheDevisebackApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarcheDevisebackApplication.class, args);
	}

}
