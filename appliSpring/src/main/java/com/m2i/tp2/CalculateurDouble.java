package com.m2i.tp2;

import org.springframework.stereotype.Component;

@Component(Calculateur.TP_CALCULATEUR_DOUBLE)
public class CalculateurDouble implements Calculateur {

	@Override
	public double calcul(double x) {
		return 2 * x;
	}

}
