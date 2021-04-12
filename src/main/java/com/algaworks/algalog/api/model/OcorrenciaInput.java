package com.algaworks.algalog.api.model;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class OcorrenciaInput {

	@NotBlank
	private String descricao;

}
