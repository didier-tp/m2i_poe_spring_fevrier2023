package com.m2i.tp.appliSpring.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.m2i.tp.appliSpring.entity.Compte;

/*
 * DAO = Data Access Object
 *   appelé "Repository" dans Spring
 *   avec méthodes CRUD
 */

/*
//V1 sans JpaRepository de Spring-data
public interface ICompteRepository {
   Compte save(Compte cpt); //au sens saveOrUpdate (INSERT INTO ou UPDATE SQL)
   Compte findById(Integer id); //recherche par clef primaire
   List<Compte> findAll();
   void deleteById(Integer id);
   //...
}
*/

public interface ICompteRepository extends JpaRepository<Compte,Integer> {
	/*
	 principales méthodes héritées:
	    Compte save(Compte cpt); 
        Optional<Compte> findById(Integer id); 
        Iterable<Compte> findAll();
        void deleteById(Integer id);
	 */
	
	//méthode de recherche qui est conforme à des convention de noms
	//findBy + nomPropriete "solde" dans classe Compte + "GreaterThanEqual"
	//ce code du SELECT sera généré automatiquement
	List<Compte> findBySoldeGreaterThanEqual(double soldeMini);
}

/*
 NB: le framework spring-data va générer automatiquement une classe
 d'implémentation du DAO (de l'interface ICompteRepository 
 qui hérite de JpaRepository .
 */

