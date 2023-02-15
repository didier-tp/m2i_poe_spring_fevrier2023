package com.m2i.tp.appliSpring.dao;

import java.util.List;

import com.m2i.tp.appliSpring.entity.Compte;

/*
 * DAO = Data Access Object
 *   appelé "Repository" dans Spring
 *   avec méthodes CRUD
 */
public interface ICompteRepository /* extends JpaRepository<Compte,Integer> */{
   Compte save(Compte cpt); //au sens saveOrUpdate (INSERT INTO ou UPDATE SQL)
   Compte findById(Integer id); //recherche par clef primaire
   List<Compte> findAll();
   void deleteById(Integer id);
   //...
}
