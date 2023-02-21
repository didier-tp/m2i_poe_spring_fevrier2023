package com.m2i.tp.appliSpring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//soit DemandeVirementDto et StatusVirementDto
//ou bien VirementDto enrichi du coté serveur

@Getter @Setter @ToString @NoArgsConstructor
@AllArgsConstructor
public class VirementDto {

	private double montant;
	private int numCptDeb;
	private int numCptCred;
	private Boolean ok = null;
	private String message = null;
}
