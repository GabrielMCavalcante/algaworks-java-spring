package com.algaworks.osworks.domains.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.osworks.domain.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{
	
	List<Client> findByName(String name);
	List<Client> findByNameContaining(String name);
	Client findByEmail(String email);
	
}
