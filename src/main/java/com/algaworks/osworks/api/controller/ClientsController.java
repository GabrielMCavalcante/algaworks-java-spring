package com.algaworks.osworks.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
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

import com.algaworks.osworks.api.model.ClientModel;
import com.algaworks.osworks.domain.model.Client;
import com.algaworks.osworks.domain.service.ClientRegisterService;
import com.algaworks.osworks.domains.repository.ClientsRepository;

@RestController
@RequestMapping("/clientes")
public class ClientsController {
	
	@Autowired
	private ClientsRepository clientRepository;
	
	@Autowired
	private ClientRegisterService clientServiceRegister;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private ClientModel toModel(Client client) {
		return modelMapper.map(client, ClientModel.class);
	}
	
	private List<ClientModel> toModelList(List<Client> clientList) {
		return clientList.stream()
				.map(client -> toModel(client))
				.collect(Collectors.toList());
	}
	
	@GetMapping
	public List<ClientModel> index() {
		return toModelList(clientRepository.findAll());
	}
	
	@GetMapping("/{clientId}")
	public ResponseEntity<ClientModel> search(@PathVariable Long clientId) {
		Optional<Client> client = clientRepository.findById(clientId);
		
		if(client.isPresent()) {
			return ResponseEntity.ok(toModel(client.get()));
		} 
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClientModel create(@Valid @RequestBody Client clientData) {
		return toModel(clientServiceRegister.save(clientData));
	}
	
	@PutMapping("/{clientId}")
	public ResponseEntity<ClientModel> update(@Valid @PathVariable Long clientId, 
			@RequestBody Client updateClientData) {
		
		if(!clientRepository.existsById(clientId)) {
			return ResponseEntity.notFound().build();
		}
		
		updateClientData.setId(clientId);
		updateClientData = clientServiceRegister.save(updateClientData);
		
		return ResponseEntity.ok(toModel(updateClientData));
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
