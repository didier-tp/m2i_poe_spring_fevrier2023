package com.m2i.tp.appliSpring.service;

import java.util.List;

import com.m2i.tp.appliSpring.dto.CompteDto;

//V2 sans héritage de classe abstraite MAIS AVEC DTO
public interface ICompteService {
    List<CompteDto> findAll();
    CompteDto findById(Integer id);
    CompteDto create(CompteDto cpt);
    void virement(double montant, int numCptDeb, int numCptCred);
    
}
