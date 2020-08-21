package com.algaworks.osworks.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.osworks.api.model.CommentModel;
import com.algaworks.osworks.api.model.CommentsInputModel;
import com.algaworks.osworks.domain.exception.EntityNotFoundException;
import com.algaworks.osworks.domain.model.Comment;
import com.algaworks.osworks.domain.model.OrderService;
import com.algaworks.osworks.domain.service.OrderServiceService;
import com.algaworks.osworks.domains.repository.CommentsRepository;
import com.algaworks.osworks.domains.repository.OrderServiceRepository;

@RestController
@RequestMapping("/ordens-servico/{orderServiceId}/comentarios")
public class CommentsController {
	
	@Autowired
	private CommentsRepository commentsRepository;
	
	@Autowired
	private OrderServiceService orderServiceService;
	
	@Autowired
	private OrderServiceRepository orderServiceRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private CommentModel toCommentModel(Comment comment) {
		return modelMapper.map(comment, CommentModel.class);
	}
	
	private List<CommentModel> toCommentModelList(List<Comment> comments) {
		return comments.stream()
				.map(comment -> toCommentModel(comment))
				.collect(Collectors.toList());
	}
	
	@GetMapping
	public List<CommentModel> searchComments(@PathVariable Long orderServiceId) {
		Optional<OrderService> order = orderServiceRepository.findById(orderServiceId);
		
		if(!order.isPresent()) {
			throw new EntityNotFoundException("Não existe nenhuma ordem de serviço com o id informado.");
		}
		
		return toCommentModelList(order.get().getComments());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CommentModel createComment(@Valid @RequestBody CommentsInputModel commentInput, 
			@PathVariable Long orderServiceId) {
		
		return toCommentModel(orderServiceService.createComment(commentInput.getDescription(), orderServiceId));
	}
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public CommentModel deleteComment(@Valid @RequestBody CommentsInputModel commentInput,
			@PathVariable Long orderServiceId) {
		
		Optional<OrderService> order = orderServiceRepository.findById(orderServiceId);
		
		if(!order.isPresent()) {
			throw new EntityNotFoundException("Não existe nenhuma ordem de serviço com o id informado.");
		}
		
		Optional<Comment> comment = commentsRepository.findById(commentInput.getComment_id());
		
		if(!comment.isPresent()) {
			throw new EntityNotFoundException("Não existe nenhum comentário com o id informado. " + 
					"Talvez ele já tenha sido excluído.");
		}
		
		commentsRepository.deleteById(commentInput.getComment_id());
		return toCommentModel(comment.get());
	}
}
