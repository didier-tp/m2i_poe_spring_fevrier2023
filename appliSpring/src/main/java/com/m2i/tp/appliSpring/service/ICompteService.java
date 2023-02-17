package com.m2i.tp.appliSpring.service;

import java.util.List;

import com.m2i.tp.appliSpring.dto.CompteDto;
import com.m2i.tp.appliSpring.exception.NotFoundException;

//V2 sans héritage de classe abstraite MAIS AVEC DTO
public interface ICompteService {
    List<CompteDto> findAll();
    CompteDto findById(Integer idCompte) throws NotFoundException;
    List<CompteDto> findBySoldeMini(double soldeMini);
    List<CompteDto> findByClientId(Integer idClient);//....
    CompteDto create(CompteDto cpt);//insert into
    boolean ifEntityExistById(int id);
    CompteDto update(CompteDto cpt) throws NotFoundException;
    void deleteById(Integer id) throws NotFoundException;
    void virement(double montant, int numCptDeb, int numCptCred);//...
    
}
