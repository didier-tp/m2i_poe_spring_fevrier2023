package com.m2i.tp3;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class CoordinateurV2 {
	
	private Interaction interaction; //valeur par défaut = null;

	private Calculateur calculateur;//=null par defaut
	

	public void setInteraction(Interaction interaction) {
		this.interaction = interaction;
	}

	public void setCalculateur(Calculateur calculateur) {
		this.calculateur = calculateur;
	}

	public void enchainement() {
		String valeurSaisieX = interaction.saisir("x:");
		double x= Double.parseDouble(valeurSaisieX);
		double y = calculateur.calcul(x);
		interaction.afficher("y="+y);
	}
	
	public static void main(String[] args) {
				
		//contextSpring = ensemble des objets de traitement pris en charge par spring
		AnnotationConfigApplicationContext contextSpring  
		    = new AnnotationConfigApplicationContext(ConfigSpringExplicite.class);
		
		//Coordinateur coordinateur = (Coordinateur) contextSpring.getBean("coordinateur");
		CoordinateurV2 coordinateur = contextSpring.getBean(CoordinateurV2.class);
		coordinateur.enchainement();
	}

}
