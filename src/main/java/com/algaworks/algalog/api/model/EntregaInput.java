package com.algaworks.algalog.api.model;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class EntregaInput {

	@Valid
	@NotNull
	private DestinatarioInput destinatario;

	@NotNull
	private BigDecimal taxa;

	@Valid
	@NotNull
	private ClienteIdInput cliente;

}
