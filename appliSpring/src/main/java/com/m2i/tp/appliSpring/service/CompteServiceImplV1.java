package com.m2i.tp.appliSpring.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.m2i.tp.appliSpring.dao.IClientRepository;
import com.m2i.tp.appliSpring.dao.ICompteRepository;
import com.m2i.tp.appliSpring.entity.Client;
import com.m2i.tp.appliSpring.entity.Compte;
//V1 sans héritage de classe abstraite ni DTO

//@Component
@Service //@Service signifie @Component de type service métier 
@Transactional(/* propagation = Propagation.REQUIRED par defaut */)
public class CompteServiceImplV1 implements ICompteServiceV1 {
	
	//@Resource(name="compteRepositorySimuImpl") //injection de dépendance
	//@Resource(name="compteRepositoryJpaImpl")
	//private ICompteRepositoryV1 compteRepository;//=null par defaut
	
	@Resource
	private ICompteRepository compteRepository;//=null par defaut
	
	@Resource
	private IClientRepository clientRepository;
	
	@Override
	public Client clientAvecSesComptes(Integer idClient) {
		Client cli = clientRepository.findById(idClient).orElse(null);
		/*
		for(Compte cpt: cli.getComptes()) {
			//boucle for à vide pour remonter les comptes en mémoire (petits select ..)
			//tout de suite (en cli.getComptes()mode lazy) avant que ce soit trop tard
			//avant fin de @Transactional et du entityManager;
		}*/
		cli.getComptes().size();//l'appel à .size() déclenche en interne boucle for
		//TOUT CA , ça fonctionne , mais c'est un peu de la bidouille
		//il vaut mieux appeler clientRepository.findByIdWithComptes()
		return cli;
	}

	@Override
	public List<Compte> findAll() {
		return compteRepository.findAll();
	}

	@Override
	public Compte findById(Integer id) {
		//return compteRepository.findById(id);//sans Optional<>
		return compteRepository.findById(id).orElse(null);
	}

	@Override
	public Compte create(Compte cpt) {
		return compteRepository.save(cpt);
	}

	@Override
	//@Transactional maintenant au dessus de la classe entière
	public void virement(double montant, int numCptDeb, int numCptCred) {
		try {
			Compte cptDeb = compteRepository.findById(numCptDeb).orElse(null);
			cptDeb.setSolde(cptDeb.getSolde() - montant);
			compteRepository.save(cptDeb);//.save() pourra être automatique si @Transactional 
			                              //et donc cptDeb à l'état persistant .
			Compte cptCred = compteRepository.findById(numCptCred).orElse(null);
			cptCred.setSolde(cptCred.getSolde() + montant);
			compteRepository.save(cptCred);//.save() pourra être automatique si @Transactional 
			                              //et donc cptCred à l'état persistant .
			//on peut faire mieux : try/catch , garder une trace du virement , regle de gestion ...
		} catch (Exception e) {
			//e.printStackTrace();
			throw new RuntimeException("echec virement",e);
		}
	}

	

}
