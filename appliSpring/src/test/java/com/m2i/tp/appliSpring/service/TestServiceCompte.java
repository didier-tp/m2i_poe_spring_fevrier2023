package com.m2i.tp.appliSpring.service;

import javax.annotation.Resource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.m2i.tp.appliSpring.AppliSpringApplication;
import com.m2i.tp.appliSpring.entity.Compte;

@ExtendWith(SpringExtension.class) //classe interprété par junit5/jupiter + extension spring
@SpringBootTest(classes= {AppliSpringApplication.class})//le test démarre en reprenant
              //la configuration de la classe principale de l'application
public class TestServiceCompte {
	
	@Resource //injection de dépendance
	private ICompteService compteService;  //à tester
	
	
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
		
	}

}
