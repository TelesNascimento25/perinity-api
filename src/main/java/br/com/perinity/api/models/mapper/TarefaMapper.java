package br.com.perinity.api.models.mapper;

import br.com.perinity.api.models.Tarefa;
import br.com.perinity.api.models.DTO.TarefaDTO;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TarefaMapper {

	public TarefaDTO toDTO(Tarefa tarefa) {
		TarefaDTO dto = new TarefaDTO();
		dto.setId(tarefa.getId());
		dto.setTitulo(tarefa.getTitulo());
		dto.setDescricao(tarefa.getDescricao());
		dto.setPrazo(tarefa.getPrazo());
		dto.setDepartamentoId(tarefa.getDepartamento().getId());
		dto.setDuracao(tarefa.getDuracao());
		if (tarefa.getPessoa() != null) {
			TarefaDTO.PessoaDTOId pessoaDTOId = new TarefaDTO.PessoaDTOId(tarefa.getPessoa());
			dto.setPessoa(pessoaDTOId);
		}
		dto.setFinalizado(tarefa.getFinalizado());
		return dto;
	}

	public Tarefa toEntity(TarefaDTO dto) {
		Tarefa tarefa = new Tarefa();
		tarefa.setId(dto.getId());
		tarefa.setTitulo(dto.getTitulo());
		tarefa.setDescricao(dto.getDescricao());
		tarefa.setPrazo(dto.getPrazo());
		tarefa.setDuracao(dto.getDuracao());
		tarefa.setFinalizado(dto.getFinalizado());
		return tarefa;
	}
}
