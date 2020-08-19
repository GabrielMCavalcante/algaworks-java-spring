package com.algaworks.osworks.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.osworks.domain.model.Client;
import com.algaworks.osworks.domain.service.ClientRegisterService;
import com.algaworks.osworks.domains.repository.ClientRepository;

@RestController
@RequestMapping("/clientes")
public class ClientController {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ClientRegisterService clientServiceRegister;
	
	@GetMapping
	public List<Client> index() {
		return clientRepository.findAll();
	}
	
	@GetMapping("/{clientId}")
	public ResponseEntity<Client> search(@PathVariable Long clientId) {
		Optional<Client> client = clientRepository.findById(clientId);
		
		if(client.isPresent()) {
			return ResponseEntity.ok(client.get());
		} 
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Client create(@Valid @RequestBody Client clientData) {
		return clientServiceRegister.save(clientData);
	}
	
	@PutMapping("/{clientId}")
	public ResponseEntity<Client> update(@Valid @PathVariable Long clientId, 
			@RequestBody Client updateClientData) {
		
		if(!clientRepository.existsById(clientId)) {
			return ResponseEntity.notFound().build();
		}
		
		updateClientData.setId(clientId);
		updateClientData = clientServiceRegister.save(updateClientData);
		
		return ResponseEntity.ok(updateClientData);
	}
	
	@DeleteMapping("/{clientId}")
	public ResponseEntity<Void> delete(@PathVariable Long clientId){
		if(!clientRepository.existsById(clientId)) {
			return ResponseEntity.notFound().build();
		}
		
		clientServiceRegister.delete(clientId);
		
		return ResponseEntity.ok().build();
	}
}
