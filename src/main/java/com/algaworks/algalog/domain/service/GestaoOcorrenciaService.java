package com.algaworks.algalog.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.model.Ocorrencia;
import com.algaworks.algalog.domain.repository.OcorrenciaRepository;

@Service
public class GestaoOcorrenciaService {

	@Autowired
	private GestaoEntregaService gestaoEntregaService;
	
	@Autowired
	private OcorrenciaRepository ocorrenciaRepository;
	
	public Ocorrencia registrar(Long entregaId, String descricao) {
		Entrega entrega = gestaoEntregaService.buscar(entregaId);
		
		Ocorrencia ocorrencia = new Ocorrencia();
		ocorrencia.setDataRegistro(OffsetDateTime.now());
		ocorrencia.setDescricao(descricao);
		ocorrencia.setEntrega(entrega);
		
		return ocorrenciaRepository.save(ocorrencia);
	}
	
}
