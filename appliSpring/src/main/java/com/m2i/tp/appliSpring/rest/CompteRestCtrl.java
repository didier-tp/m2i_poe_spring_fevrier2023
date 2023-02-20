package com.m2i.tp.appliSpring.rest;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.m2i.tp.appliSpring.dto.CompteDto;
import com.m2i.tp.appliSpring.service.ICompteServiceV2;

@RestController 
@RequestMapping(value="/api-bank/compte" , headers="Accept=application/json")
public class CompteRestCtrl {
	
	@Resource //ou @Autowired
	private ICompteServiceV2 compteService;
	
	//exemple URL: http://localhost:8080/appliSpring/api-bank/compte/1
	@GetMapping("/{idCpt}")
	public CompteDto getCompteById(@PathVariable("idCpt") Integer id) {
		return compteService.findById(id);
	}
	
	//exemple URL: http://localhost:8080/appliSpring/api-bank/compte
	//ou bien      http://localhost:8080/appliSpring/api-bank/compte?soldeMini=100
	@GetMapping("")
	public List<CompteDto> getCompteByCriteria(
			@RequestParam(value="soldeMini",required=false)  Double soldeMini) {
		if(soldeMini==null)
		    return compteService.findAll();
		else
			return compteService.findBySoldeMini(soldeMini);
	}

	

}
