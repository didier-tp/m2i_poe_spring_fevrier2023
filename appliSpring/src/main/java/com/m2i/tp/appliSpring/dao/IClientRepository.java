package com.m2i.tp.appliSpring.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.m2i.tp.appliSpring.entity.Client;

public interface IClientRepository extends JpaRepository<Client,Integer>{
	Client findByIdWithComptes(Integer idClient);//variante du findById()
	//qui renvoie le client demandé + liste des comptes rattachés
	//le code du select sera dans NamedQuery de nom "Client.findByIdWithComptes"
}
