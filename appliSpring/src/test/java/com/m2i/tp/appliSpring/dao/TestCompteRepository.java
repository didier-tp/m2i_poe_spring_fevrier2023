package com.m2i.tp.appliSpring.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.m2i.tp.appliSpring.AppliSpringApplication;
import com.m2i.tp.appliSpring.entity.Client;
import com.m2i.tp.appliSpring.entity.Compte;

@ExtendWith(SpringExtension.class) //classe interprété par junit5/jupiter + extension spring
@SpringBootTest(classes= {AppliSpringApplication.class})//le test démarre en reprenant
              //la configuration de la classe principale de l'application
public class TestCompteRepository {
	
	@Resource //injection de dépendance
	private ICompteRepository compteRepository;  //à tester
	
	
	@Resource //injection de dépendance
	private IClientRepository clientRepository;  //pour aider à tester
	
	
	@Test
	public void testDivers() {
		Client c1 = new Client(null,"luc","Bon");
		c1.setPrenom("jean");//.setPrenom() généré par lombok est ou pas compris par l'IDE
		clientRepository.save(c1);
		
		Compte cptA = new Compte(null,"compte_A",50.0);
		cptA.setClient(c1);
		compteRepository.save(cptA); 
		compteRepository.save( new Compte(null,"my_account_B",150.0) ); 
		Compte cptC =new Compte(null,"compte_C",30.0);
		cptC.setClient(c1);
		compteRepository.save( cptC ); 
		compteRepository.save( new Compte(null,"account_D",40.0) ); 
		compteRepository.save( new Compte(null,"compte_E",60.0) ); 
		
		List<Compte> comptesAvecSoldeSuperieurA50 = compteRepository.findBySoldeGreaterThanEqual(50.0);
		System.out.println("comptes avec solde >= 50 : " + comptesAvecSoldeSuperieurA50);
	    Assertions.assertTrue(comptesAvecSoldeSuperieurA50.size()>=3);
	    
	    List<Compte> comptesAvecLabelComportantAccount = compteRepository.findByLabelContaining("account");
	    System.out.println("comptesAvecLabelComportantAccount: " + comptesAvecLabelComportantAccount);
	    Assertions.assertTrue(comptesAvecLabelComportantAccount.size()>=2);
	    
	    List<Compte> comptesDuClient1 = compteRepository.findByClientId(c1.getId());
	    System.out.println("comptesDuClient1="+comptesDuClient1);
	    Assertions.assertTrue(comptesDuClient1.size()>=2);
	}

}
