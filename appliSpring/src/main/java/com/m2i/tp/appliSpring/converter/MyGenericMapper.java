package com.m2i.tp.appliSpring.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

public class MyGenericMapper {
	

	public static <S,D> D map(S source,Class<D> destinationClass) {
		D destination = null;
		try {
			//without mapStruct
			destination = destinationClass.getDeclaredConstructor().newInstance();
			BeanUtils.copyProperties(source, destination);
			//.copyProperties recopie d'un objet vers un autre toutes
			//les propriétés de mêmes noms.
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return destination;
	}
	
	
	public static <S,D> List<D> map(List<S> sourceList , Class<D> destinationClass){
		return  sourceList.stream()
			   .map((source)->map(source,destinationClass))
			   .collect(Collectors.toList());
	}

}