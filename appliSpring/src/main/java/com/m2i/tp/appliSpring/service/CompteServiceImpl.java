package com.m2i.tp.appliSpring.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.m2i.tp.appliSpring.dao.ICompteRepository;
import com.m2i.tp.appliSpring.entity.Compte;
//V1 sans héritage de classe abstraite ni DTO

//@Component
@Service //@Service signifie @Component de type service métier 
public class CompteServiceImpl implements ICompteService {
	
	//@Resource(name="compteRepositorySimuImpl") //injection de dépendance
	//@Resource(name="compteRepositoryJpaImpl")
	//private ICompteRepositoryV1 compteRepository;//=null par defaut
	
	@Resource
	private ICompteRepository compteRepository;//=null par defaut

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
	public void virement(double montant, int numCptDeb, int numCptCred) {
		Compte cptDeb = compteRepository.findById(numCptDeb).orElse(null);
		cptDeb.setSolde(cptDeb.getSolde() - montant);
		compteRepository.save(cptDeb);//.save() pourra être automatique si @Transactional 
		                              //et donc cptDeb à l'état persistant .
		Compte cptCred = compteRepository.findById(numCptCred).orElse(null);
		cptCred.setSolde(cptCred.getSolde() + montant);
		compteRepository.save(cptCred);//.save() pourra être automatique si @Transactional 
		                              //et donc cptCred à l'état persistant .
		//on peut faire mieux : try/catch , garder une trace du virement , regle de gestion ...
	}

}
