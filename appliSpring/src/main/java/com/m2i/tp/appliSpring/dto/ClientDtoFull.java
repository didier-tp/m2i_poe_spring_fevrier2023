package com.m2i.tp.appliSpring.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter  @NoArgsConstructor
public class ClientDtoFull extends ClientDto {
	private List<CompteDto> comptes;

	public ClientDtoFull(Integer id, String prenom, String nom) {
		super(id, prenom, nom);
		this.comptes=new ArrayList<>();
	}
	
	public ClientDtoFull(Integer id, String prenom, String nom , List<CompteDto> comptes) {
		super(id, prenom, nom);
		this.comptes= comptes;
	}

	@Override
	public String toString() {
		return "ClientDtoFull [comptes=" + comptes + ", toString()=" + super.toString() + "]";
	}

	
}
