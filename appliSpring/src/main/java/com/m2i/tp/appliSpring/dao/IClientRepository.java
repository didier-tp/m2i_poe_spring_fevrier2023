package com.m2i.tp.appliSpring.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.m2i.tp.appliSpring.entity.Client;

public interface IClientRepository extends JpaRepository<Client,Integer>{

}
