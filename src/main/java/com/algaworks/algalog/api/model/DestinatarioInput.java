package com.algaworks.algalog.api.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class DestinatarioInput {

	@NotBlank
	@Size(max = 60)
	private String nome;
	
	@NotBlank
	@Size(max = 255)
	private String logradouro;
	
	@Size(max = 30)
	private String numero;
	
	@Size(max = 60)
	private String complemento;
	
	@NotBlank
	@Size(max = 30)
	private String bairro;

}
