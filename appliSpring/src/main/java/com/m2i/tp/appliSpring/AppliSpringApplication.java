package com.m2i.tp.appliSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication //@Configuration + @EnableAutoConfiguration + @ComponentScan(package courant)
//@ComponentScan(basePackages = {"com.m2i.tp.appliSpring" , "com.m2i.tp.acote"})
@EnableAspectJAutoProxy
public class AppliSpringApplication {

	public static void main(String[] args) {
		//SpringApplication.run(AppliSpringApplication.class, args);
		SpringApplication app = new SpringApplication(AppliSpringApplication.class);
		app.setAdditionalProfiles("reInit","dev" ,"mysql","eventuel_autre_profil_complementaire");
		app.run(args);
		System.out.println("http://localhost:8080/appliSpring");
	}

}
