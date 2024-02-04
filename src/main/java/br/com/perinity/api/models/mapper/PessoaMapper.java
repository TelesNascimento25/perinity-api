package br.com.perinity.api.models.mapper;

import java.util.stream.Collectors;

import br.com.perinity.api.models.Departamento;
import br.com.perinity.api.models.Pessoa;
import br.com.perinity.api.models.DTO.PessoaDTO;
import br.com.perinity.api.models.DTO.TarefaDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.WebApplicationException;

@ApplicationScoped
public class PessoaMapper {

	public PessoaDTO toDTO(Pessoa pessoa) {
		PessoaDTO dto = new PessoaDTO();
		dto.setId(pessoa.getId());
		dto.setNome(pessoa.getNome());
		if (pessoa.getDepartamento() != null) {
			dto.setDepartamentoId(pessoa.getDepartamento().getId());
		}
		if (pessoa.getTarefas() != null) {
			dto.setTarefas(pessoa.getTarefas().stream().map(TarefaDTO::new).collect(Collectors.toList()));
			dto.setTotalHorasGastas(pessoa.getTarefas().stream().mapToLong(tarefa -> tarefa.getDuracao()).sum() / 60.0);
		}
		return dto;
	}

	public Pessoa toEntity(PessoaDTO dto) {
		Pessoa pessoa = new Pessoa();
		pessoa.setId(dto.getId());
		pessoa.setNome(dto.getNome());
		if (dto.getDepartamentoId() != null) {
			Departamento departamento = Departamento.findById(dto.getDepartamentoId());
			if (departamento == null) {
				throw new WebApplicationException(
						"Departamento with id of " + dto.getDepartamentoId() + " does not exist.", 404);
			}
			pessoa.setDepartamento(departamento);
		}
		if (dto.getTarefas() != null) {
			pessoa.setTarefas(dto.getTarefas().stream().map(TarefaDTO::toEntity).collect(Collectors.toList()));
		}
		return pessoa;
	}
}
