package com.m2i.tp.appliSpring.dao;

import java.util.List;

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
public class TestCompteRepository {
	
	@Resource //injection de dépendance
	private ICompteRepository compteRepository;  //à tester
	
	
	@Test
	public void testDivers() {
		compteRepository.save( new Compte(null,"compte_A",50.0) ); 
		compteRepository.save( new Compte(null,"compte_B",150.0) ); 
		compteRepository.save( new Compte(null,"compte_C",30.0) ); 
		compteRepository.save( new Compte(null,"compte_D",40.0) ); 
		compteRepository.save( new Compte(null,"compte_E",60.0) ); 
		
		List<Compte> comptes = compteRepository.findBySoldeGreaterThanEqual(50.0);
		System.out.println("comptes avec solde >= 50 : " + comptes);
	    Assertions.assertTrue(comptes.size()>=3);
	}

}
