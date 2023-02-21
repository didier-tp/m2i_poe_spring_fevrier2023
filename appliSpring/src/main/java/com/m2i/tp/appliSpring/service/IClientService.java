package com.m2i.tp.appliSpring.service;

import com.m2i.tp.appliSpring.dao.IClientRepository;
import com.m2i.tp.appliSpring.dto.ClientDto;
import com.m2i.tp.appliSpring.dto.ClientDtoFull;
import com.m2i.tp.appliSpring.entity.Client;
import com.m2i.tp.appliSpring.service.generic.IGenericService;

//avec héritage de classe abstraite ET AVEC DTO
public interface IClientService extends IGenericService<Client,ClientDto,IClientRepository>{
	
	public ClientDtoFull createFull(ClientDtoFull dto);
   
}
