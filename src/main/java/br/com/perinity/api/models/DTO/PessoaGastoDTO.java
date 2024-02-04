package br.com.perinity.api.models.DTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PessoaGastoDTO {
	private String nome;
	private LocalDate prazo;
	private Double mediaHorasGastas;
	public PessoaGastoDTO() {
	}
}