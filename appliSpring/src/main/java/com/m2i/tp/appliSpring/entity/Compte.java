package com.m2i.tp.appliSpring.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="Compte.findAll", query = "SELECT c FROM Compte c")
public class Compte /* implements IEntity */ {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; 
	
	private String label;
	
	private Double solde;
	

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
	
	

}
