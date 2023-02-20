package com.m2i.tp.appliSpring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor
public class CompteDtoFull extends CompteDto{
	private Integer idClient ;

	public CompteDtoFull(Integer id, String label, Double solde) {
		super(id, label, solde);
	}
	
	public CompteDtoFull(Integer id, String label, Double solde,Integer numCli) {
		super(id, label, solde);
		this.idClient = numCli;
	}

	@Override
	public String toString() {
		return "CompteDtoFull [idClient=" + idClient + ", toString()=" + super.toString() + "]";
	}
	
	
	
}
