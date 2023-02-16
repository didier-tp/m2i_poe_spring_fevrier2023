package com.m2i.tp.appliSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //@Configuration + @EnableAutoConfiguration + @ComponentScan(packege courant)
public class AppliSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppliSpringApplication.class, args);
		System.out.println("http://localhost:8080/appliSpring");
	}

}
