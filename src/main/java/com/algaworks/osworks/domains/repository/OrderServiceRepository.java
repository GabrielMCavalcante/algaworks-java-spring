package com.algaworks.osworks.domains.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.osworks.domain.model.OrderService;

@Repository
public interface OrderServiceRepository extends JpaRepository<OrderService, Long> {
	
}
