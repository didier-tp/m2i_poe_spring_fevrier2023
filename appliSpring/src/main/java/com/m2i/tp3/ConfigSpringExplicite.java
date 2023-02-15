package com.m2i.tp3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties") 
@ComponentScan(basePackages = "com.m2i.tp3") 
public class ConfigSpringExplicite {
	
	@Value("${tp3.calculateur}")//valeur de tp3.calculateur= dans le .properties
	private String calculateurClassName;
	
	@Value("${tp3.interaction}")
	private String interactionClassName;

	//@Bean fait que la chose construite via le new
	//est vu comme un composant Spring que l'on peut injecter ailleurs
	@Bean
	public Interaction interaction() {
		
		/*
		if(this.interactionClassName.equals("com.m2i.tp3.InteractionImplV1"))
		    return new InteractionImplV1();
		if(this.interactionClassName.equals("com.m2i.tp3.InteractionImplV2"))
		    return new InteractionImplV2();
		return null;
		*/
		Interaction interaction=null;
		try {
			Class<?> c=Class.forName(interactionClassName);
			interaction = (Interaction )c.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}  
		return interaction;
	}
	
	@Bean
	public Calculateur calculateur() {
		if(this.calculateurClassName.equals("com.m2i.tp3.CalculateurCarre"))
		   return new CalculateurCarre();
		if(this.calculateurClassName.equals("com.m2i.tp3.CalculateurDouble"))
			   return new CalculateurDouble();
		return null;
	}
	
	@Bean
	public CoordinateurV2 coordinateurV2(Calculateur calculateur, Interaction interaction) {
		CoordinateurV2 coordinateurV2=new CoordinateurV2();
		coordinateurV2.setInteraction(interaction);
		coordinateurV2.setCalculateur(calculateur);
		return coordinateurV2;
	}
}
