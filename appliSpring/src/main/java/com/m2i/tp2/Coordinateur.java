package com.m2i.tp2;

public class Coordinateur {
	
	private Interaction interaction; 
	//interaction est une référence sur un composant implémentant
	//l'interface Interaction , classes possibles: InteractionImplV1, InteractionImplV2
	
	public void enchainement() {
		//V1 : saisi + affichage
		String valeurSaisieX = interaction.saisir("x:");
		interaction.afficher("valeurSaisieX="+valeurSaisieX);
		
		//V2 : saisi + calcul + affichage
	}
	
	public static void main(String[] args) {
		//V0 (sans spring)
		Coordinateur coordinateur = new Coordinateur();
		//coordinateur.interaction = new InteractionImplV1();
		coordinateur.interaction = new InteractionImplV2();
		coordinateur.enchainement();
	}

}
