package com.m2i.tp3;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component //pour demander à Spring de prendre en charge cette classe (new ... + ...) 
public class Coordinateur {
	
	
	
	//@Resource sans (name="...") se comporte comme @Autowired
	@Resource()
	private Interaction interaction; //valeur par défaut = null;
	

	@Resource()
	private Calculateur calculateur;//=null par defaut
	
	public void enchainement() {
		/*
		//V1 : saisi + affichage
		String valeurSaisieX = interaction.saisir("x:");
		interaction.afficher("valeurSaisieX="+valeurSaisieX);
		*/
		
		//V2 : saisi + calcul + affichage
		String valeurSaisieX = interaction.saisir("x:");
		double x= Double.parseDouble(valeurSaisieX);
		double y = calculateur.calcul(x);
		interaction.afficher("y="+y);
	}
	
	public static void main(String[] args) {
		/*
		//V0 (sans spring)
		Coordinateur coordinateur = new Coordinateur();
		//coordinateur.interaction =  new InteractionImplV1();
		coordinateur.interaction = new InteractionImplV2();
		coordinateur.enchainement();
		*/
		
		//V1 avec spring-framework : Les classes Coordinateur et InteractionImplV1 seront 
		//prises en charge par Spring
		
		//contextSpring = ensemble des objets de traitement pris en charge par spring
		AnnotationConfigApplicationContext contextSpring  
		    = new AnnotationConfigApplicationContext(ConfigSpringExplicite.class);
		
		//Coordinateur coordinateur = (Coordinateur) contextSpring.getBean("coordinateur");
		Coordinateur coordinateur = contextSpring.getBean(Coordinateur.class);
		coordinateur.enchainement();
	}

}
