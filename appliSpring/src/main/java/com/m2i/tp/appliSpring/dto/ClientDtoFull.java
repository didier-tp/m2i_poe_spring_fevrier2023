package com.m2i.tp.appliSpring.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @NoArgsConstructor
@AllArgsConstructor
public class ClientDtoFull extends ClientDto {
	private List<CompteDto> comptes;
}
