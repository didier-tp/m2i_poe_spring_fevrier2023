package com.m2i.tp.appliSpring.init;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.m2i.tp.appliSpring.dto.CompteDto;
import com.m2i.tp.appliSpring.service.ICompteServiceV2;

@Component
//@Profile("reInit")
public class ReInitDefaultDataSet {

	@Resource
	private ICompteServiceV2 compteService;
	
	@PostConstruct
	public void initDataSet() {
		CompteDto cptA = new CompteDto(null,"compte_A",50.0);
		cptA = compteService.create(cptA); 
		CompteDto cptB = new CompteDto(null,"compte_B",150.0);
		cptB= compteService.create(cptB);
	}

}
