package com.m2i.tp.appliSpring.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.m2i.tp.appliSpring.entity.Compte;

@Repository //ou @component
@Transactional //pour commit/rollback automatiques
public class CompteRepositoryJpaImpl implements ICompteRepositoryV1 {
	
	@PersistenceContext //pour initialiser le entityManager
	//à partir du fichier META-INF/persistence.xml et de EntityManagerFactory
	//ou bien d'une config spring équivalente dans application.properties
	private EntityManager entityManager;

	@Override
	public Compte save(Compte cpt) {
		if(cpt.getId()==null)
		    entityManager.persist(cpt); //INSERT INTO
		     //si dans la classe entity.Compte il y a 
		     //@GeneratedValue(strategy = GenerationType.IDENTITY) 
		     //à coté de @Id , la valeur de l'id auto incrémentée par H2 ou MySql
		     //va automatiquement remonter en mémoire dans l'objet persistant
		else
			entityManager.merge(cpt); //UPDATE
		return cpt; //on retourne le compte sauvegardé avec la clef primaire quelquefois auto incrémentée
	}

	@Override
	public Compte findById(Integer id) {
		return entityManager.find(Compte.class, id);
	}

	@Override
	public List<Compte> findAll() {
		//return entityManager.createQuery("SELECT c FROM Compte c", Compte.class)
		return entityManager.createNamedQuery("Compte.findAll", Compte.class)
				.getResultList();
	}

	@Override
	public void deleteById(Integer id) {
		Compte compte = entityManager.find(Compte.class, id);
		entityManager.remove(compte);
	}

}
