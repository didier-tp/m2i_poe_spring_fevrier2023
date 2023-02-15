package com.m2i.tp2;

import javax.swing.JOptionPane;

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
