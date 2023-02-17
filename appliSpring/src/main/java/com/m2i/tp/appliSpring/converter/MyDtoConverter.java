package com.m2i.tp.appliSpring.converter;

import com.m2i.tp.appliSpring.dto.CompteDto;
import com.m2i.tp.appliSpring.entity.Compte;

public class MyDtoConverter {

	public static CompteDto compteToCompteDto(Compte c) {
		return new CompteDto(c.getId(),c.getLabel(),c.getSolde());
		//ou bien
		/*
		CompteDto compteDto = new CompteDto();
		compteDto.setId(c.getId());//ou bien compteDto.setNumero(c.getId());
		compteDto.setLabel(c.getLabel());
		compteDto.setSolde(c.getSolde());
		return compteDto;
		*/
	}

}
