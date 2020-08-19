package com.algaworks.osworks.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.osworks.domain.exception.BusinessException;
import com.algaworks.osworks.domain.model.Client;
import com.algaworks.osworks.domains.repository.ClientRepository;

@Service
public class ClientRegisterService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	public Client save(Client client) {
		Client existingClient = clientRepository.findByEmail(client.getEmail());
		
		if (existingClient != null && !existingClient.equals(client)) {
			throw new BusinessException("Já existe um cliente cadastrado com este email."); 
		}
		
		return clientRepository.save(client);
	}
	
	public void delete(Long clientId) {
		clientRepository.deleteById(clientId);
	}
}
