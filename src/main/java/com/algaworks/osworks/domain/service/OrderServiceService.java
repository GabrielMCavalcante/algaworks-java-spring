package com.algaworks.osworks.domain.service;

import java.time.OffsetDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.osworks.domain.exception.BusinessException;
import com.algaworks.osworks.domain.exception.EntityNotFoundException;
import com.algaworks.osworks.domain.model.Client;
import com.algaworks.osworks.domain.model.Comment;
import com.algaworks.osworks.domain.model.OrderService;
import com.algaworks.osworks.domain.model.OrderServiceStatus;
import com.algaworks.osworks.domains.repository.ClientsRepository;
import com.algaworks.osworks.domains.repository.CommentsRepository;
import com.algaworks.osworks.domains.repository.OrderServiceRepository;

@Service
public class OrderServiceService {
	
	@Autowired
	private OrderServiceRepository orderServiceRepository;
	
	@Autowired
	private ClientsRepository clientsRepository;
	
	@Autowired
	private CommentsRepository commentsRepository;
	
	public OrderService create(OrderService orderService) {
		
		Optional<Client> client = clientsRepository.findById(orderService.getClient().getId());
		
		if(!client.isPresent()) {
			throw new BusinessException("Não existe nenhum cliente com o id recebido.");
		}
		
		orderService.setStatus(OrderServiceStatus.OPEN);
		orderService.setOpenDate(OffsetDateTime.now());
		orderService.setClient(client.get());
		
		return orderServiceRepository.save(orderService);
	}
	
	public Comment createComment(String description, Long orderServiceId) {
		
		Optional<OrderService> order = orderServiceRepository.findById(orderServiceId);
		
		if(!order.isPresent()) {
			throw new EntityNotFoundException("Não existe nenhuma ordem de serviço com o id informado.");
		}
		
		Comment comment = new Comment(order.get(), description);
		comment.setSendDate(OffsetDateTime.now());
		
		return commentsRepository.save(comment);
	}
	
}
