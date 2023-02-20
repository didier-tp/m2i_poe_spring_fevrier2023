package com.m2i.tp.appliSpring.service;

import javax.annotation.Resource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.m2i.tp.appliSpring.AppliSpringApplication;
import com.m2i.tp.appliSpring.entity.Compte;

@ExtendWith(SpringExtension.class) //classe interprété par junit5/jupiter + extension spring
@SpringBootTest(classes= {AppliSpringApplication.class})//le test démarre en reprenant
              //la configuration de la classe principale de l'application
@ActiveProfiles({"dev" ,"mysql"})
//@ActiveProfiles({"dev" ,"h2"})
public class TestServiceCompteV1 {
	
	@Resource //injection de dépendance
	private ICompteServiceV1 compteService;  //à tester
	
	
	@Test
	public void testAjoutCompte() {
		Compte cptA = new Compte(null,"compte_A",50.0);
		compteService.create(cptA); //créer en base de données (ou en mémoire si simulé)
		Integer numCptA = cptA.getId();
		System.out.println("numero du compte A = " + numCptA);
		
		Compte cptA_relu = compteService.findById(numCptA);
		System.out.println("cptA_relu = " + cptA_relu.toString());
		Assertions.assertEquals("compte_A", cptA_relu.getLabel());
	}
	
	@Test
	public void testBonVirement() {
		Compte cptA = new Compte(null,"compte_A",50.0);
		compteService.create(cptA); 
		Compte cptB = new Compte(null,"compte_B",150.0);
		compteService.create(cptB);
		System.out.println("avant bon virement : " + cptA.getSolde() + " " + cptB.getSolde());
		compteService.virement(20.0, cptA.getId(), cptB.getId());
		Compte cptA_relu_apres = compteService.findById(cptA.getId());
		Compte cptB_relu_apres = compteService.findById(cptB.getId());
		System.out.println("apres bon virement : " + cptA_relu_apres.getSolde() 
		                    + " " + cptB_relu_apres.getSolde());
		Assertions.assertEquals(cptA.getSolde() - 20, cptA_relu_apres.getSolde(),0.0001);
		Assertions.assertEquals(cptB.getSolde() + 20, cptB_relu_apres.getSolde(),0.0001);
	}
	
	@Test
	public void testMauvaisVirement() {
		Compte cptA = new Compte(null,"compte_A",50.0);
		compteService.create(cptA); 
		Compte cptB = new Compte(null,"compte_B",150.0);
		compteService.create(cptB);
		System.out.println("avant mauvais virement : " + cptA.getSolde() + " " + cptB.getSolde());
		try {
			compteService.virement(20.0, cptA.getId(), -5 /* n'existe pas */);
		} catch (Exception e) {
			System.out.println("exception normale , attendue: " + e.getMessage());
			//e.printStackTrace();
		}
		Compte cptA_relu_apres = compteService.findById(cptA.getId());
		Compte cptB_relu_apres = compteService.findById(cptB.getId());
		System.out.println("apres mauvais virement : " + cptA_relu_apres.getSolde() 
		                    + " " + cptB_relu_apres.getSolde());
		Assertions.assertEquals(cptA.getSolde() , cptA_relu_apres.getSolde(),0.0001);
		Assertions.assertEquals(cptB.getSolde() , cptB_relu_apres.getSolde(),0.0001);
	}

}
