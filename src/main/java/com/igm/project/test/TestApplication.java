package com.igm.project.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
		System.out.print("Number of available processors are: ");
		System.out.println( Runtime.getRuntime().availableProcessors());
	}

}
