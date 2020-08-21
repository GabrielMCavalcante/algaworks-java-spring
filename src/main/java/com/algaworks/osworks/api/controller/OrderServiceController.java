package com.algaworks.osworks.api.controller;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.osworks.api.model.OrderServiceInputModel;
import com.algaworks.osworks.api.model.OrderServiceModel;
import com.algaworks.osworks.domain.exception.BusinessException;
import com.algaworks.osworks.domain.exception.EntityNotFoundException;
import com.algaworks.osworks.domain.model.OrderService;
import com.algaworks.osworks.domain.model.OrderServiceStatus;
import com.algaworks.osworks.domain.service.OrderServiceService;
import com.algaworks.osworks.domains.repository.OrderServiceRepository;

@RestController
@RequestMapping("/ordens-servico")
public class OrderServiceController {
	
	@Autowired
	private OrderServiceService orderServiceService;
	
	@Autowired
	private OrderServiceRepository orderServiceRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private OrderServiceModel toOrderServiceModel(OrderService orderService) {
		return modelMapper.map(orderService, OrderServiceModel.class);
	}
	
	private List<OrderServiceModel> toOrderServiceModelList(List<OrderService> orderServices) {
		return orderServices.stream()
				.map(orderService -> toOrderServiceModel(orderService))
				.collect(Collectors.toList());
	}
	
	@GetMapping
	public List<OrderServiceModel> index() {
		return toOrderServiceModelList(orderServiceRepository.findAll());
	}

	@GetMapping("/{orderServiceId}")
	public ResponseEntity<OrderServiceModel> search(@PathVariable Long orderServiceId) {
		
		Optional<OrderService> orderService = orderServiceRepository.findById(orderServiceId);
		
		if(!orderService.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		OrderServiceModel orderServiceModel = toOrderServiceModel(orderService.get());
		
		return ResponseEntity.ok(orderServiceModel);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrderServiceModel create(@Valid @RequestBody OrderServiceInputModel orderServiceInput) {
		OrderService orderService = modelMapper.map(orderServiceInput, OrderService.class);
		return toOrderServiceModel(orderServiceService.create(orderService));
	}
	 
	@PutMapping("/{orderServiceId}/{method}")
	@ResponseStatus(HttpStatus.OK)
	public void finishOrderService(@PathVariable Long orderServiceId, @PathVariable String method) {
		
		Optional<OrderService> order = orderServiceRepository.findById(orderServiceId);
		
		if(!order.isPresent()) {
			throw new EntityNotFoundException("Nenhuma ordem de serviço encontrada com o id informado.");
		}
		
		OrderService finishedOrder = order.get();
		
		switch(method.toLowerCase()) {
			case "finalizar":
				{
					OrderServiceStatus currentStatus = finishedOrder.getStatus();
					if (currentStatus.equals(OrderServiceStatus.OPEN))
						finishedOrder.setStatus(OrderServiceStatus.FINISHED);
					else throw new BusinessException("Esta ordem de serviço já está finalizada/cancelada.");
					break;
				}
			case "cancelar":
				{
					OrderServiceStatus currentStatus = finishedOrder.getStatus();
					if (currentStatus.equals(OrderServiceStatus.OPEN))
						finishedOrder.setStatus(OrderServiceStatus.CANCELED);
					else throw new BusinessException("Esta ordem de serviço já está finalizada/cancelada.");
					break;
				}
			default: 
				throw new EntityNotFoundException("Não existe um status de ordem de serviço " + 
						"com o método informado. Os métodos existentes são FINALIZAR e CANCELAR.");
		}
		finishedOrder.setFinishDate(OffsetDateTime.now());
		orderServiceRepository.save(finishedOrder);
	}
	
}
