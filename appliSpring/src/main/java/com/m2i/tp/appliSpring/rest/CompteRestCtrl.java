package com.m2i.tp.appliSpring.rest;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.m2i.tp.appliSpring.dto.CompteDto;
import com.m2i.tp.appliSpring.dto.ErrorDto;
import com.m2i.tp.appliSpring.dto.InfoDto;
import com.m2i.tp.appliSpring.exception.NotFoundException;
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
	
	/*
	@GetMapping("/{idCpt}")
	public ResponseEntity<?> getCompteById(@PathVariable("idCpt") Integer id) {
		//quelquefois findById() retourne null si pas trouvé
		//aujourd'hui findById() renvoie exception si pas trouvé
		try {
			CompteDto compteDto = compteService.findById(id);
			return new ResponseEntity<CompteDto>(compteDto,HttpStatus.OK);
		} catch (NotFoundException e) {
			return new ResponseEntity<ErrorDto>( new ErrorDto("compte not found" ,e.getMessage()) ,
					                            HttpStatus.NOT_FOUND);//404_NOT_FOUND
		}
		
	}*/
	
	//exemple URL: http://localhost:8080/appliSpring/api-bank/compte
	//ou bien      http://localhost:8080/appliSpring/api-bank/compte?soldeMini=100
	//ou bien      http://localhost:8080/appliSpring/api-bank/compte?soldeMini=99&idClient=1 possible
	@GetMapping("")
	public List<CompteDto> getCompteByCriteria(
			@RequestParam(name="soldeMini",required=false)  Double soldeMini) {
		if(soldeMini==null)
		    return compteService.findAll();
		else
			return compteService.findBySoldeMini(soldeMini);
	}

	// URL: http://localhost:8080/appliSpring/api-bank/compte
	//appelé en mode POST avec dans le corps de la requete:
	//    { "id" : null , "label" : "compteXy" , "solde" : 66.66 }
	// ou {  "label" : "compteXy" , "solde" : 66.66 }
	@PostMapping("")
	public CompteDto postCompte(@RequestBody @Valid CompteDto compteDto) {
		return compteService.create(compteDto);//id auto incrémenté 
	}
	
	// URL: http://localhost:8080/appliSpring/api-bank/compte
	//appelé en mode PUT avec dans le corps de la requete:
	//    { "id" : 3 , "label" : "compte_XY" , "solde" : 77.77 }
	@PutMapping("")
	public CompteDto putCompte(@RequestBody CompteDto compteDto) {
		return compteService.update(compteDto);
	}
	
	// URL: http://localhost:8080/appliSpring/api-bank/compte/3
	//appelé en mode DELETE
	@DeleteMapping("/{idCpt}")
    public ResponseEntity<?> deleteCompte(@PathVariable("idCpt") Integer id) {
		try {
			compteService.deleteById(id);
			//return new ResponseEntity<CompteDto>(HttpStatus.NO_CONTENT);//HttpStatus.NO_CONTENT=204
			return new ResponseEntity<InfoDto>( new InfoDto("compte bien supprimé" , "id="+id) ,
					                             HttpStatus.OK);//HttpStatus.OK=200
		} catch (NotFoundException e) {
			return new ResponseEntity<ErrorDto>( new ErrorDto("compte not found" ,e.getMessage()) ,
					                            HttpStatus.NOT_FOUND);//404_NOT_FOUND
		}
	}
	

}
