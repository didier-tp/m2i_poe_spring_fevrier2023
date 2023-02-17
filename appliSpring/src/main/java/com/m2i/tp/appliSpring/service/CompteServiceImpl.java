package com.m2i.tp.appliSpring.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.m2i.tp.appliSpring.converter.MyGenericMapper;
import com.m2i.tp.appliSpring.dao.ICompteRepository;
import com.m2i.tp.appliSpring.dto.CompteDto;
import com.m2i.tp.appliSpring.entity.Compte;


//@Component
@Service //@Service signifie @Component de type service métier 
@Transactional(/* propagation = Propagation.REQUIRED par defaut */)
public class CompteServiceImpl implements ICompteService {
	
	
	@Resource
	private ICompteRepository compteRepository;//=null par defaut
	
	

	@Override
	public List<CompteDto> findAll() {
		return MyGenericMapper.map(compteRepository.findAll(),CompteDto.class);
	}

	@Override
	public CompteDto findById(Integer id) {
		Compte compte = compteRepository.findById(id).orElse(null);
		CompteDto compteDto = MyGenericMapper.map(compte, CompteDto.class);
		//compteDto.setNumero(compte.getId()); //faisable ici ou ailleurs si nécessaire
		return compteDto;
	}

	@Override
	public CompteDto create(CompteDto cptDto) {
		Compte compteEntity = MyGenericMapper.map(cptDto,Compte.class);
		compteRepository.save(compteEntity);
		return MyGenericMapper.map(compteEntity,CompteDto.class);
		//cptDto.setId(compteEntity.getId());
		//return cptDto;
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
