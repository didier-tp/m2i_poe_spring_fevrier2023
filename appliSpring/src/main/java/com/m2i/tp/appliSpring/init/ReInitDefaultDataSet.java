package com.m2i.tp.appliSpring.init;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.m2i.tp.appliSpring.dao.IClientRepository;
import com.m2i.tp.appliSpring.dto.CompteDto;
import com.m2i.tp.appliSpring.service.ICompteServiceV2;

@Component
@Profile("reInit") //cette classe sera prise en compte que si 
                   //le profil "reInit" est activé au démarrage de l'application
public class ReInitDefaultDataSet {

	@Resource
	private ICompteServiceV2 compteService;
	
	@Resource //injection de dépendance
	//private IClientRepository clientRepository;  //pour aider à remplir les tables
	//private IClientRepository compteRepository;  //pour aider à remplir les tables
	
	@PostConstruct
	public void initDataSet() {
		CompteDto cptA = new CompteDto(null,"compte_A",50.0);
		cptA = compteService.create(cptA); 
		CompteDto cptB = new CompteDto(null,"compte_B",150.0);
		cptB= compteService.create(cptB);
	}

}
