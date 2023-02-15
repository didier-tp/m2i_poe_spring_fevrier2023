package com.m2i.tp2;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component //pour demander à Spring de prendre en charge cette classe (new ... + ...) 
public class CoordinateurAvecInjectionParConstructeur {
	
	private Interaction interaction; //valeur par défaut = null;
	
	private Calculateur calculateur;//=null par defaut
	
	//Constructeur qui gère l'injection de dépendance (Spring , un peu comme Angular)
	//@Autowired implicite si un seule version du constructeur
	public CoordinateurAvecInjectionParConstructeur(@Qualifier("interactionImplV1") Interaction interaction, 
								    @Qualifier(Calculateur.TP_CALCULATEUR_CARRE) Calculateur calculateur) {
		super();
		this.interaction = interaction;
		this.calculateur = calculateur;
	}

	public void enchainement() {
	
		//V2 : saisi + calcul + affichage
		String valeurSaisieX = interaction.saisir("x:");
		double x= Double.parseDouble(valeurSaisieX);
		double y = calculateur.calcul(x);
		interaction.afficher("y="+y);
	}
	
	public static void main(String[] args) {
		
		//contextSpring = ensemble des objets de traitement pris en charge par spring
		AnnotationConfigApplicationContext contextSpring  
		    = new AnnotationConfigApplicationContext(ConfigSpring.class);
		
		//Coordinateur coordinateur = (Coordinateur) contextSpring.getBean("coordinateur");
		CoordinateurAvecInjectionParConstructeur coordinateur = contextSpring.getBean(CoordinateurAvecInjectionParConstructeur.class);
		coordinateur.enchainement();
	}

}
