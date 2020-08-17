package com.algaworks.osworks.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.osworks.domain.model.Client;

@RestController
public class ClientController {
	
	@GetMapping("/clientes")
	public List<Client> index() {
		var client1 = new Client();
		client1.setId(1L);
		client1.setName("João Carlos Romarão");
		client1.setEmail("joao.carlos@gmail.com");
		client1.setPhone("123456789");
		
		var client2 = new Client();
		client2.setId(2L);
		client2.setName("Maria Antonietta");
		client2.setEmail("maria.antonietta@hotmail.com");
		client2.setPhone("987654321");
		
		var clientList = new ArrayList<Client>();
		clientList.add(client1);
		clientList.add(client2);
		
		return clientList;
	}
	
}
