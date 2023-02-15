package com.m2i.tp.appliSpring.service;

import java.util.List;

import com.m2i.tp.appliSpring.entity.Compte;

//V1 sans héritage de classe abstraite ni DTO
public interface ICompteService {
    public List<Compte> findAll();
    public Compte findById(Integer id);
    public Compte create(Compte cpt);
    public void virement(double montant, int numCptDeb, int numCptCred);
}
