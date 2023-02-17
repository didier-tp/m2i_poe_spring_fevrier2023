package com.m2i.tp.appliSpring.service.generic;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.m2i.tp.appliSpring.converter.MyGenericMapper;
import com.m2i.tp.appliSpring.exception.NotFoundException;

public abstract class AbstractGenericServiceImpl<E,DTO,DAO extends JpaRepository<E,Integer>> 
       implements IGenericService<E, DTO, DAO > {
	
	protected Class<DTO> DTOClass ;
	protected Class<E> EntityClass ;
	
	public abstract DAO getDAO();
	public abstract Integer getIdOfDto(DTO dto);

	public AbstractGenericServiceImpl(Class<E> EntityClass , Class<DTO> DTOClass ) {
		this.EntityClass=EntityClass;
		this.DTOClass = DTOClass;
	}

	@Override
	public List<DTO> findAll() {
		List<E> entityList = this.getDAO().findAll();
		return MyGenericMapper.map(entityList,DTOClass);
	}

	@Override
	public DTO findById(Integer id) throws NotFoundException {
		E entity = this.getDAO().findById(id).orElse(null);
		if(entity==null)
			throw new NotFoundException("entity not found with id=" +id);
		return MyGenericMapper.map(entity,DTOClass);
	}

	@Override
	public DTO create(DTO dto) {
		E entity = MyGenericMapper.map(dto,EntityClass);
		this.getDAO().save(entity); //auto_incr
		return MyGenericMapper.map(entity,DTOClass);
	}

	@Override
	public DTO update(DTO dto) throws NotFoundException {
		if(dto==null || getIdOfDto(dto)==null) throw new NotFoundException("null not found");
		if(this.getDAO().existsById(getIdOfDto(dto))==false)
			throw new NotFoundException("impossible de mettre à jour entity avec id="
		                                +getIdOfDto(dto)+ " car existe pas");
		E entity = MyGenericMapper.map(dto,EntityClass);
		this.getDAO().save(entity); //auto_incr
		return MyGenericMapper.map(entity,DTOClass);
	}

	@Override
	public boolean ifEntityExistById(int id) {
		return this.getDAO().existsById(id);
	}

	@Override
	public void deleteById(Integer id) throws NotFoundException {
		if(this.getDAO().existsById(id)==false)
			throw new NotFoundException("impossible de supprimer entity avec id="+id+ " car existe pas");
		this.getDAO().deleteById(id);
		
	}

}
