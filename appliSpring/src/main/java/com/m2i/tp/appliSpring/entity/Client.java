package com.m2i.tp.appliSpring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//les annotations @Getter , @Setter seront interprétées lors de la compilation
//effectuée par maven.
//Selon l'IDE (eclipse ou IntelliJ, ...) une manip sera nécessaire en plus
@Getter @Setter @NoArgsConstructor
//@ToString existe mais piege de boucle infinie sur les @Entity
//@AllArgsConstructor avec le piege nombre d'argument sui peut evoluer

@Entity
public class Client {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 64)
	private String prenom;
	
	@Column(length = 64)
	private String nom;
	
	@Transient //en mémoire seulement (jamais sctocké en base)
	private String commentaireUltraSecret;
	
	//email , telephone , ...
	
	
	public Client(Integer id, String prenom, String nom) {
		super();
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", prenom=" + prenom + ", nom=" + nom + "]";
	}


}
