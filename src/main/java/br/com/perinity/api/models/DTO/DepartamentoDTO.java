package br.com.perinity.api.models.DTO;

import br.com.perinity.api.models.Departamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DepartamentoDTO {
	public Long id;
	public String titulo;
	public Long quantidadePessoas;
	public Long quantidadeTarefas;

	public DepartamentoDTO() {
	}

	public Departamento toEntity() {
		Departamento departamento = new Departamento();
		departamento.setId(this.id);
		departamento.setTitulo(this.titulo);
		return departamento;
	}

	public static DepartamentoDTO fromEntity(Departamento departamento) {
		DepartamentoDTO dto = new DepartamentoDTO();
		dto.setId(departamento.getId());
		dto.setTitulo(departamento.getTitulo());
		dto.setQuantidadePessoas((long) departamento.getPessoas().size());
		dto.setQuantidadeTarefas(
				departamento.getPessoas().stream().flatMap(pessoa -> pessoa.getTarefas().stream()).count());
		return dto;
	}
}
