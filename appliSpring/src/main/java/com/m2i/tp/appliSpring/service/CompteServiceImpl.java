package com.m2i.tp.appliSpring.service;

import java.util.List;

import com.m2i.tp.appliSpring.dao.ICompteRepository;
import com.m2i.tp.appliSpring.entity.Compte;
//V1 sans héritage de classe abstraite ni DTO

//@....
public class CompteServiceImpl implements ICompteService {
	
	//@...
	private ICompteRepository compteRepository;//=null par defaut

	@Override
	public List<Compte> findAll() {
		return compteRepository.findAll();
	}

	@Override
	public Compte findById(Integer id) {
		return compteRepository.findById(id);
	}

	@Override
	public Compte create(Compte cpt) {
		return compteRepository.save(cpt);
	}

	@Override
	public void virement(double montant, int numCptDeb, int numCptCred) {
		// sera codé plus tard

	}

}
