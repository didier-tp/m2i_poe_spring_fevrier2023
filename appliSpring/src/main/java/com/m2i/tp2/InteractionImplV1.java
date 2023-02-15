package com.m2i.tp2;

import java.util.Scanner;

import org.springframework.stereotype.Component;

@Component() //nom logique par défaut = NomClasse avec minuscule au début 
             //= "interactionImplV1"
//@Component("interactionImplV1") //idem avec nom par défaut explicité
public class InteractionImplV1 implements Interaction {

	@Override
	public void afficher(String message) {
		System.out.println(message);
	}

	@Override
	public String saisir(String message) {
		System.out.println(message);
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}

}
