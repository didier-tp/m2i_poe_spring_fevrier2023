package com.m2i.tp.appliSpring.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="Compte.findAll", query = "SELECT c FROM Compte c")
public class Compte /* implements IEntity */ {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; 
	
	private String label;
	
	private Double solde;
	
	@ManyToOne // Many "Compte" toOne "client"
	@JoinColumn(name="id_client") //nom de la colonne "fk/clef etrangere" 
	                             //qui référence le client rattaché au compte
	private Client client; //avec get/set
	

	public Compte() {
		super();
	}
	

	public Compte(Integer id, String label, Double solde) {
		super();
		this.id = id;
		this.label = label;
		this.solde = solde;
	}



	@Override
	public String toString() {
		return "Compte [id=" + id + ", label=" + label + ", solde=" + solde + "]";
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Double getSolde() {
		return solde;
	}

	public void setSolde(Double solde) {
		this.solde = solde;
	}


	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}
	
	

}
