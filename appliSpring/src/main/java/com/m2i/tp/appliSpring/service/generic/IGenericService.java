package com.m2i.tp.appliSpring.service.generic;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.m2i.tp.appliSpring.exception.NotFoundException;

public interface IGenericService<E,DTO,DAO extends JpaRepository<E,Integer>> {
   List<DTO> findAll();
   public DTO findById(Integer id) throws NotFoundException;
   public DTO create(DTO dto);
   public DTO update(DTO dto) throws NotFoundException;
   public boolean ifEntityExistById(int id);
   public void deleteById(Integer id) throws NotFoundException ;
}
