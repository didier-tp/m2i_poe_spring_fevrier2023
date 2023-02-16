package com.m2i.tp.appliSpring.service;

import java.util.List;

import com.m2i.tp.appliSpring.entity.Compte;

//V1 sans héritage de classe abstraite ni DTO
public interface ICompteService {
    List<Compte> findAll();
    Compte findById(Integer id);
    Compte create(Compte cpt);
    void virement(double montant, int numCptDeb, int numCptCred);
}
