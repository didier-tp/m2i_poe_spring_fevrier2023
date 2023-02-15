package com.m2i.tp2;

import org.springframework.stereotype.Component;

//@Component() //nom par defaut= NomClasse avec minuscule au debut = calculateurCarre
@Component(Calculateur.TP_CALCULATEUR_CARRE)
public class CalculateurCarre implements Calculateur {

	@Override
	public double calcul(double x) {
		return x * x;
	}

}
