package com.m2i.tp2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component //pour demander à Spring de prendre en charge cette classe (new ... + ...) 
public class Coordinateur {
	
	@Autowired //injection de dépendance 
	//pour demander à spring de remplacer la valeur null
	//par une référence pointant vers un autre composant spring existant
	//qui est compatible avec celui indiqué : ici l'interface Interaction
	private Interaction interaction; //valeur par défaut = null;
	//interaction est une référence sur un composant implémentant
	//l'interface Interaction , classes possibles: InteractionImplV1, InteractionImplV2
	
	public void enchainement() {
		//V1 : saisi + affichage
		String valeurSaisieX = interaction.saisir("x:");
		interaction.afficher("valeurSaisieX="+valeurSaisieX);
		
		//V2 : saisi + calcul + affichage
	}
	
	public static void main(String[] args) {
		/*
		//V0 (sans spring)
		Coordinateur coordinateur = new Coordinateur();
		//coordinateur.interaction = new InteractionImplV1();
		coordinateur.interaction = new InteractionImplV2();
		coordinateur.enchainement();
		*/
		
		//V1 avec spring-framework : Les classes Coordinateur et InteractionImplV1 seront 
		//prises en charge par Spring
		
		//contextSpring = ensemble des objets de traitement pris en charge par spring
		AnnotationConfigApplicationContext contextSpring  
		    = new AnnotationConfigApplicationContext(ConfigSpring.class);
		
		//Coordinateur coordinateur = (Coordinateur) contextSpring.getBean("coordinateur");
		Coordinateur coordinateur = contextSpring.getBean(Coordinateur.class);
		coordinateur.enchainement();
	}

}
