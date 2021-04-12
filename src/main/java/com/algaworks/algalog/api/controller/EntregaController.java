package com.algaworks.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.algaworks.algalog.api.assembler.EntregaAssembler;
import com.algaworks.algalog.api.model.EntregaInput;
import com.algaworks.algalog.api.model.EntregaModel;
import com.algaworks.algalog.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algalog.domain.exception.NegocioException;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.repository.EntregaRepository;
import com.algaworks.algalog.domain.service.GestaoEntregaService;

@RestController
@RequestMapping("/entregas")
public class EntregaController {

	@Autowired
	private GestaoEntregaService gestaoEntregaService;
	
	@Autowired
	private EntregaRepository entregaRepository;
	
	@Autowired
	private EntregaAssembler entregaAssembler;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntregaModel criar(@Valid @RequestBody EntregaInput entregaInput) {
		try {
			Entrega novaEntrega = entregaAssembler.toEntity(entregaInput);
			Entrega entregaCriada = gestaoEntregaService.criar(novaEntrega);
			
			return entregaAssembler.toModel(entregaCriada);
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@GetMapping
	public List<EntregaModel> listar() {
		return entregaAssembler.toCollectionModel(entregaRepository.findAll());
	}
	
	@GetMapping("/{entregaId}")
	public ResponseEntity<EntregaModel> buscar(@PathVariable Long entregaId) {
		Entrega entrega = gestaoEntregaService.buscar(entregaId);
		
		EntregaModel entregaModel = entregaAssembler.toModel(entrega);
		return ResponseEntity.ok(entregaModel);
		
//		Optional<Entrega> entrega = entregaRepository.findById(entregaId);
//		
//		if (entrega.isPresent()) {
//			EntregaModel entregaModel = toModel(entrega.get());
//			return ResponseEntity.ok(entregaModel);
//		}
//		
//		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{entregaId}/finalizacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finalizar(@PathVariable Long entregaId) {
		gestaoEntregaService.finalizar(entregaId);
	}
	
}
