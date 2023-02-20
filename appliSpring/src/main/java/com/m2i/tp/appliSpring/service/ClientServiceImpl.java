package com.m2i.tp.appliSpring.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.m2i.tp.appliSpring.dao.IClientRepository;
import com.m2i.tp.appliSpring.dto.ClientDto;
import com.m2i.tp.appliSpring.entity.Client;
import com.m2i.tp.appliSpring.service.generic.AbstractGenericServiceImpl;


//@Component
@Service //@Service signifie @Component de type service métier 
@Transactional(/* propagation = Propagation.REQUIRED par defaut */)
public class ClientServiceImpl
              extends AbstractGenericServiceImpl<Client,ClientDto,IClientRepository> 
              implements IClientService{
	
	private Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);//slf4j
	
	
	public ClientServiceImpl() {
		super(Client.class , ClientDto.class);
	}
	
	@Resource
	private IClientRepository clientRepository;//=null par defaut
	

	@Override
	public IClientRepository getDAO() {
		return this.clientRepository;
	}

	@Override
	public Integer getIdOfDto(ClientDto dto) {
		return dto.getId();
	}
	

}
