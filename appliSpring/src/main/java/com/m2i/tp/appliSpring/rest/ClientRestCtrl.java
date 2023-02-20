package com.m2i.tp.appliSpring.rest;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.m2i.tp.appliSpring.dto.ClientDto;
import com.m2i.tp.appliSpring.dto.ErrorDto;
import com.m2i.tp.appliSpring.dto.InfoDto;
import com.m2i.tp.appliSpring.exception.NotFoundException;
import com.m2i.tp.appliSpring.service.IClientService;

@RestController 
@CrossOrigin(origins = "*" , methods = { RequestMethod.GET , RequestMethod.POST , 
		                                 RequestMethod.PUT , RequestMethod.DELETE })
@RequestMapping(value="/api-bank/client" , headers="Accept=application/json")
public class ClientRestCtrl {
	
	@Resource //ou @Autowired
	private IClientService clientService;
	
	//exemple URL: http://localhost:8080/appliSpring/api-bank/client/1
	
	@GetMapping("/{idCli}")
	public ClientDto getClientById(@PathVariable("idCli") Integer id) {
		return clientService.findById(id); 
	}
	
		
	//exemple URL: http://localhost:8080/appliSpring/api-bank/client
	@GetMapping("")
	public List<ClientDto> getCompteByCriteria() {
		    return clientService.findAll();
	}

	// URL: http://localhost:8080/appliSpring/api-bank/client
	//appelé en mode POST avec dans le corps de la requete:
	//    { "id" : null , "prenom" : "jean" , "nom" : "Bon" }
	// ou {  "prenom" : "jean" , "nom" : "Bon" }
	@PostMapping("")
	public ClientDto postClient(@RequestBody @Valid ClientDto clientDto) {
		return clientService.create(clientDto);//id auto incrémenté 
	}
	
	// URL: http://localhost:8080/appliSpring/api-bank/client
	//appelé en mode PUT avec dans le corps de la requete:
	//   { "id" : 3 , "prenom" : "jeanJean" , "nom" : "Aimare" }
	@PutMapping("")
	public ClientDto putCompte(@RequestBody  ClientDto clientDto) {
		return clientService.update(clientDto);
	}
	
	// URL: http://localhost:8080/appliSpring/api-bank/client/3
	//appelé en mode DELETE
	@DeleteMapping("/{idCli}")
    public ResponseEntity<?> deleteClient(@PathVariable("idCli") Integer id) {
		try {
			clientService.deleteById(id);
			//return new ResponseEntity<CompteDto>(HttpStatus.NO_CONTENT);//HttpStatus.NO_CONTENT=204
			return new ResponseEntity<InfoDto>( new InfoDto("client bien supprimé" , "id="+id) ,
					                             HttpStatus.OK);//HttpStatus.OK=200
		} catch (NotFoundException e) {
			return new ResponseEntity<ErrorDto>( new ErrorDto("client not found" ,e.getMessage()) ,
					                            HttpStatus.NOT_FOUND);//404_NOT_FOUND
		}
	}
	

}
