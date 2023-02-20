package com.m2i.tp.appliSpring.dto;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @NoArgsConstructor
@AllArgsConstructor
public class CompteDto {
	
    private Integer id;
    //ou bien private Integer numero;
    //ou bien private String numero;
    
    @Length(min=3, max=64, message = "entre 3 et 64 caracteres")
    private String label;
    //ou bien private String libelle;
    
    @Min(value=0 , message="le solde doit etre positif")  //necessite spring-boot-starter-validation
    private Double solde;
}
