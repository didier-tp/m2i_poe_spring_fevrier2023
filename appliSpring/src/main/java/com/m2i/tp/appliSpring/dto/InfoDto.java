package com.m2i.tp.appliSpring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @NoArgsConstructor
@AllArgsConstructor
public class InfoDto {
	protected String message;
	protected String detail;
}
