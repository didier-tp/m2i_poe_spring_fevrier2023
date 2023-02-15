package com.m2i.tp2;

import javax.swing.JOptionPane;

import org.springframework.stereotype.Component;

//@Component // "interactionImplV2" = nom par défaut
public class InteractionImplV2 implements Interaction {

	@Override
	public void afficher(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	@Override
	public String saisir(String message) {
		return JOptionPane.showInputDialog(null, message);
	}

}
