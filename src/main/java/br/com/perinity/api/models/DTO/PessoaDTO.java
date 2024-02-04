package br.com.perinity.api.models.DTO;

import java.util.List;
import java.util.stream.Collectors;

import br.com.perinity.api.models.Departamento;
import br.com.perinity.api.models.Pessoa;
import jakarta.ws.rs.WebApplicationException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PessoaDTO {
	public Long id;
	public String nome;
	public Long departamentoId;
	public List<TarefaDTO> tarefas;
	public Double totalHorasGastas;
	public Double mediaHorasGastas;

	public PessoaDTO() {
	}

	public PessoaDTO(Pessoa pessoa) {
		this.id = pessoa.getId();
		this.nome = pessoa.getNome();
		if (pessoa.getDepartamento() != null) {
			this.departamentoId = pessoa.getDepartamento().getId();
		}
		if (pessoa.getTarefas() != null) {
			this.tarefas = pessoa.getTarefas().stream().map(TarefaDTO::new).collect(Collectors.toList());
			this.totalHorasGastas = pessoa.getTarefas().stream().mapToLong(tarefa -> tarefa.getDuracao()).sum() / 60.0;
		}
	}

	public Pessoa toEntity() {
		Pessoa pessoa = new Pessoa();
		pessoa.setId(this.id);
		pessoa.setNome(this.nome);
		if (this.departamentoId != null) {
			Departamento departamento = Departamento.findById(this.departamentoId);
			if (departamento == null) {
				throw new WebApplicationException("Departamento with id of " + this.departamentoId + " does not exist.",
						404);
			}
			pessoa.setDepartamento(departamento);
		}
		if (this.tarefas != null) {
			pessoa.setTarefas(this.tarefas.stream().map(TarefaDTO::toEntity).collect(Collectors.toList()));
		}
		return pessoa;
	}

	public static PessoaDTO fromEntity(Pessoa pessoa) {
		return new PessoaDTO(pessoa);
	}
}
