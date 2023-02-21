package com.m2i.tp.appliSpring.init;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.m2i.tp.appliSpring.dto.ClientDto;
import com.m2i.tp.appliSpring.dto.ClientDtoFull;
import com.m2i.tp.appliSpring.dto.CompteDto;
import com.m2i.tp.appliSpring.dto.CompteDtoFull;
import com.m2i.tp.appliSpring.service.IClientService;
import com.m2i.tp.appliSpring.service.ICompteServiceV2;

@Component
@Profile("reInit") //cette classe sera prise en compte que si 
                   //le profil "reInit" est activé au démarrage de l'application
public class ReInitDefaultDataSet {

	@Resource
	private ICompteServiceV2 compteService;
	
	@Resource
	private IClientService clientService;
	
	//@Resource //injection de dépendance
	//private IClientRepository clientRepository;  //pour aider à remplir les tables
	//private IClientRepository compteRepository;  //pour aider à remplir les tables
	
	/*
	@PostConstruct
	public void initDataSetV1() {
		
		ClientDto cliX = new ClientDto(null,"alex","Therieur");
		cliX = clientService.create(cliX); 
		
		ClientDto cliY = new ClientDto(null,"axelle","Aire");
		cliY = clientService.create(cliY); 
		
		
		
		CompteDtoFull cptA = new CompteDtoFull(null,"compte_A",50.0,cliX.getId());
		cptA = compteService.createFull(cptA); 
		
		CompteDtoFull cptB = new CompteDtoFull(null,"compte_B",150.0,cliX.getId());
		cptB= compteService.createFull(cptB);
		
		CompteDtoFull cptC = new CompteDtoFull(null,"compte_C",60.0,cliY.getId());
		cptC = compteService.createFull(cptC); 
		
		CompteDtoFull cptD = new CompteDtoFull(null,"compte_D",160.0,cliY.getId());
		cptD= compteService.createFull(cptD);
	}*/
	
	@PostConstruct
	public void initDataSetV2() {
		
		ClientDtoFull cliX = new ClientDtoFull(null,"alex","Therieur");
		cliX.getComptes().add(new CompteDto(null,"compte_A",50.0));
		cliX.getComptes().add(new CompteDto(null,"compte_B",150.0));
		cliX = clientService.createFull(cliX); 
		
		ClientDtoFull cliY = new ClientDtoFull(null,"axelle","Aire");
		cliY.getComptes().add(new CompteDto(null,"compte_C",60.0));
		cliY.getComptes().add(new CompteDto(null,"compte_D",160.0));
		cliY = clientService.createFull(cliY); 
		
	}

}
