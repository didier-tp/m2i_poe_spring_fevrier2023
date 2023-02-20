package com.m2i.tp.appliSpring.service;

import java.util.List;

import com.m2i.tp.appliSpring.dao.ICompteRepository;
import com.m2i.tp.appliSpring.dto.CompteDto;
import com.m2i.tp.appliSpring.dto.CompteDtoFull;
import com.m2i.tp.appliSpring.entity.Compte;
import com.m2i.tp.appliSpring.service.generic.IGenericService;

//V2 avec héritage de classe abstraite ET AVEC DTO
public interface ICompteServiceV2 extends IGenericService<Compte,CompteDto,ICompteRepository>{
    List<CompteDto> findBySoldeMini(double soldeMini);
    List<CompteDto> findByClientId(Integer idClient);//....
    void virement(double montant, int numCptDeb, int numCptCred);//...
    
    public CompteDtoFull createFull(CompteDtoFull dto);
}
