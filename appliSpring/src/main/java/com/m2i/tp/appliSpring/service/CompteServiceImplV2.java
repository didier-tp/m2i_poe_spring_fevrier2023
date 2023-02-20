package com.m2i.tp.appliSpring.service;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.m2i.tp.appliSpring.converter.MyGenericMapper;
import com.m2i.tp.appliSpring.dao.IClientRepository;
import com.m2i.tp.appliSpring.dao.ICompteRepository;
import com.m2i.tp.appliSpring.dto.CompteDto;
import com.m2i.tp.appliSpring.dto.CompteDtoFull;
import com.m2i.tp.appliSpring.entity.Compte;
import com.m2i.tp.appliSpring.service.generic.AbstractGenericServiceImpl;


//@Component
@Service //@Service signifie @Component de type service métier 
@Transactional(/* propagation = Propagation.REQUIRED par defaut */)
public class CompteServiceImplV2
              extends AbstractGenericServiceImpl<Compte,CompteDto,ICompteRepository> 
              implements ICompteServiceV2{
	
	private Logger logger = LoggerFactory.getLogger(CompteServiceImplV2.class);//slf4j
	
	
	public CompteServiceImplV2() {
		super(Compte.class , CompteDto.class);
	}
	
	@Resource
	private ICompteRepository compteRepository;//=null par defaut
	
	@Resource
	private IClientRepository clientRepository;//=null par defaut
	

	@Override
	public ICompteRepository getDAO() {
		return this.compteRepository;
	}

	@Override
	public Integer getIdOfDto(CompteDto dto) {
		return dto.getId();
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
			logger.error("echec virement",e);
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
	
	public CompteDtoFull createFull(CompteDtoFull compteDtoFull) {
		Compte entity = MyGenericMapper.map(compteDtoFull,Compte.class);
		entity.setClient(clientRepository.findById(compteDtoFull.getIdClient()).orElse(null));
		compteRepository.save(entity); //auto_incr
		compteDtoFull.setId(entity.getId());
		return compteDtoFull;
	}


}
