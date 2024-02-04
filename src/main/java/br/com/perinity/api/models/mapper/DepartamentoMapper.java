package br.com.perinity.api.models.mapper;

import br.com.perinity.api.models.Departamento;
import br.com.perinity.api.models.DTO.DepartamentoDTO;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DepartamentoMapper {

	public DepartamentoDTO toDTO(Departamento departamento) {
		DepartamentoDTO dto = new DepartamentoDTO();
		dto.setId(departamento.getId());
		dto.setTitulo(departamento.getTitulo());
		dto.setQuantidadePessoas((long) departamento.getPessoas().size());
		dto.setQuantidadeTarefas(
				departamento.getPessoas().stream().flatMap(pessoa -> pessoa.getTarefas().stream()).count());
		return dto;
	}

	public Departamento toEntity(DepartamentoDTO dto) {
		Departamento departamento = new Departamento();
		departamento.setId(dto.getId());
		departamento.setTitulo(dto.getTitulo());
		return departamento;
	}
}
