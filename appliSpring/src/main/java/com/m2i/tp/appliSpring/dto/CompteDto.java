package com.m2i.tp.appliSpring.dto;

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
    
    private String label;
    //ou bien private String libelle;
    
    private Double solde;
}
