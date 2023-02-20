package com.m2i.tp.appliSpring.service;

import javax.annotation.Resource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.m2i.tp.appliSpring.AppliSpringApplication;
import com.m2i.tp.appliSpring.dto.CompteDto;

@ExtendWith(SpringExtension.class) //classe interprété par junit5/jupiter + extension spring
@SpringBootTest(classes= {AppliSpringApplication.class})//le test démarre en reprenant
              //la configuration de la classe principale de l'application
@ActiveProfiles({"dev" ,"mysql"})
//@ActiveProfiles({"dev" ,"h2"})
public class TestServiceCompte {
	
	@Resource //injection de dépendance
	private ICompteService compteService;  //à tester
	
	
	@Test
	public void testCRUDCompte() {
		
		//AJOUT
		CompteDto cptA = new CompteDto(null,"compte_A",50.0);
		cptA = compteService.create(cptA); //créer en base de données (ou en mémoire si simulé)
		Integer numCptA = cptA.getId();
		System.out.println("numero du compte A = " + numCptA);
		
		//VERIE AJOUT
		CompteDto cptA_relu = compteService.findById(numCptA);
		System.out.println("cptA_relu = " + cptA_relu.toString());
		Assertions.assertEquals("compte_A", cptA_relu.getLabel());
		
		//UPDATE
		cptA_relu.setLabel("compte_A_Modifie");
		cptA_relu.setSolde(60.0);
		
		cptA_relu = compteService.update(cptA_relu);
		//VERIFIE UPDATE
		CompteDto cptA_relu2 = compteService.findById(numCptA);
		System.out.println("cptA_relu2 = " + cptA_relu2.toString());
		Assertions.assertEquals("compte_A_Modifie", cptA_relu2.getLabel());
		
		try {
			compteService.update(new CompteDto(-6,"compte inexistant",89.9));
			Assertions.fail("probleme : execption attendue pas remotee");
		} catch (Exception e) {
			System.out.println("e="+e.getMessage());
			Assertions.assertTrue(e.getClass().getSimpleName().equals("NotFoundException"));
		}
		
		//DELETE:
		compteService.deleteById(numCptA);
		
		//VERIFIER SUPPRESSION:
		Assertions.assertTrue(compteService.ifEntityExistById(numCptA)==false);
		
		
	}
	
	@Test
	public void testBonVirement() {
		CompteDto cptA = new CompteDto(null,"compte_A",50.0);
		cptA = compteService.create(cptA); 
		CompteDto cptB = new CompteDto(null,"compte_B",150.0);
		cptB= compteService.create(cptB);
		System.out.println("avant bon virement : " + cptA.getSolde() + " " + cptB.getSolde());
		compteService.virement(20.0, cptA.getId(), cptB.getId());
		CompteDto cptA_relu_apres = compteService.findById(cptA.getId());
		CompteDto cptB_relu_apres = compteService.findById(cptB.getId());
		System.out.println("apres bon virement : " + cptA_relu_apres.getSolde() 
		                    + " " + cptB_relu_apres.getSolde());
		Assertions.assertEquals(cptA.getSolde() - 20, cptA_relu_apres.getSolde(),0.0001);
		Assertions.assertEquals(cptB.getSolde() + 20, cptB_relu_apres.getSolde(),0.0001);
	}
	
	@Test
	public void testMauvaisVirement() {
		CompteDto cptA = new CompteDto(null,"compte_A",50.0);
		cptA= compteService.create(cptA); 
		CompteDto cptB = new CompteDto(null,"compte_B",150.0);
		cptB = compteService.create(cptB);
		System.out.println("avant mauvais virement : " + cptA.getSolde() + " " + cptB.getSolde());
		try {
			compteService.virement(20.0, cptA.getId(), -5 /* n'existe pas */);
		} catch (Exception e) {
			System.out.println("exception normale , attendue: " + e.getMessage());
			//e.printStackTrace();
		}
		CompteDto cptA_relu_apres = compteService.findById(cptA.getId());
		CompteDto cptB_relu_apres = compteService.findById(cptB.getId());
		System.out.println("apres mauvais virement : " + cptA_relu_apres.getSolde() 
		                    + " " + cptB_relu_apres.getSolde());
		Assertions.assertEquals(cptA.getSolde() , cptA_relu_apres.getSolde(),0.0001);
		Assertions.assertEquals(cptB.getSolde() , cptB_relu_apres.getSolde(),0.0001);
	}

}
