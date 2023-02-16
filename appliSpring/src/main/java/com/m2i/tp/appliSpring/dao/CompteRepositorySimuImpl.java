package com.m2i.tp.appliSpring.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.m2i.tp.appliSpring.entity.Compte;
//@...
public class CompteRepositorySimuImpl implements ICompteRepository {
	
	private Map<Integer,Compte> mapComptes = new HashMap<>();
	private int maxId=0;//pour simuler auto_incr

	@Override
	public Compte save(Compte cpt) {
		if(cpt.getId()==null) {
			maxId++;
			cpt.setId(maxId);
		}
		mapComptes.put(cpt.getId(), cpt);
		return cpt;
	}

	@Override
	public Compte findById(Integer id) {
          return mapComptes.get(id);
	}

	@Override
	public List<Compte> findAll() {
		return (List<Compte> )mapComptes.values();
	}

	@Override
	public void deleteById(Integer id) {
		mapComptes.remove(id);
	}

}
