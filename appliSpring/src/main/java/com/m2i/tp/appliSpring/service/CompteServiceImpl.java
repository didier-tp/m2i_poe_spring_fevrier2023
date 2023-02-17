package com.m2i.tp.appliSpring.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.m2i.tp.appliSpring.converter.MyDtoConverter;
import com.m2i.tp.appliSpring.converter.MyGenericMapper;
import com.m2i.tp.appliSpring.dao.ICompteRepository;
import com.m2i.tp.appliSpring.dto.CompteDto;
import com.m2i.tp.appliSpring.entity.Compte;
import com.m2i.tp.appliSpring.exception.NotFoundException;


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
	public CompteDto findById(Integer id) throws NotFoundException{
		Compte compte = compteRepository.findById(id).orElse(null);
		if(compte==null)
			throw new NotFoundException("compte not found with id=" +id);
		
		//CompteDto compteDto = MyGenericMapper.map(compte, CompteDto.class);//solution generic/reutilisable mais pas performant 
		CompteDto compteDto = MyDtoConverter.compteToCompteDto(compte);//plus long à coder mais plus performant (il existe MapStruct qui peut aider)
		
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
	public CompteDto update(CompteDto cptDto) throws NotFoundException {
		if(cptDto==null || cptDto.getId()==null) throw new NotFoundException("null not found");
		if(compteRepository.existsById(cptDto.getId())==false)
			throw new NotFoundException("impossible de mettre à jour le compte avec id="
		                                +cptDto.getId()+ " car existe pas");
		Compte compteEntity = MyGenericMapper.map(cptDto,Compte.class);
		compteRepository.save(compteEntity);
		return cptDto;
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

	@Override
	public List<CompteDto> findBySoldeMini(double soldeMini) {
		return MyGenericMapper.map(compteRepository.findBySoldeGreaterThanEqual(soldeMini),
				                   CompteDto.class);
	}

	@Override
	public List<CompteDto> findByClientId(Integer idClient) {
		return MyGenericMapper.map(compteRepository.findByClientId(idClient),
                                   CompteDto.class);
	}

	@Override
	public boolean ifEntityExistById(int id) {
		return compteRepository.existsById(id);
	}

	

	@Override
	public void deleteById(Integer id) throws NotFoundException {
		if(compteRepository.existsById(id)==false)
			throw new NotFoundException("impossible de supprimer le compte avec id="+id+ " car existe pas");
		compteRepository.deleteById(id);
	}

	

}
