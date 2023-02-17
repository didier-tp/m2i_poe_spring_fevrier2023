package com.m2i.tp.appliSpring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @NoArgsConstructor
@AllArgsConstructor
public class ClientDto {
	private Integer id;
	private String prenom;
	private String nom;
}
