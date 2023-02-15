package com.m2i.tp2;

import org.springframework.stereotype.Component;

@Component
public class CalculateurCarre implements Calculateur {

	@Override
	public double calcul(double x) {
		return x * x;
	}

}
