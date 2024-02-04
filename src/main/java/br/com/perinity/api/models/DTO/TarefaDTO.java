package br.com.perinity.api.models.DTO;

import java.io.Serializable;
import java.time.LocalDate;

import br.com.perinity.api.models.Pessoa;
import br.com.perinity.api.models.Tarefa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TarefaDTO implements Serializable {
	private static final long serialVersionUID = -379262240622684420L;
	public Long id;
	public String titulo;
	public String descricao;
	public LocalDate prazo;
	public Long departamentoId;
	public Long duracao;
	public PessoaDTOId pessoa;
	public Boolean finalizado;

	@AllArgsConstructor
	@Getter
	@Setter
	public static class PessoaDTOId {
		public Long id;
		public String nome;

		public PessoaDTOId(Pessoa pessoa) {
			this.id = pessoa.id;
			this.nome = pessoa.nome;
		}
	}

	public Tarefa toEntity() {
		Tarefa tarefa = new Tarefa();
		tarefa.setTitulo(this.titulo);
		tarefa.setDescricao(this.descricao);
		tarefa.setPrazo(this.prazo);
		tarefa.setDuracao(this.duracao);
		tarefa.setFinalizado(this.finalizado);
		return tarefa;
	}

	public TarefaDTO(Tarefa tarefa) {
		this.id = tarefa.getId();
		this.titulo = tarefa.getTitulo();
		this.descricao = tarefa.getDescricao();
		this.prazo = tarefa.getPrazo();
		this.departamentoId = tarefa.getDepartamento() != null ? tarefa.getDepartamento().getId() : null;
		this.duracao = tarefa.getDuracao();
		this.pessoa = tarefa.getPessoa() != null ? new PessoaDTOId(tarefa.getPessoa()) : null;
		this.finalizado = tarefa.getFinalizado();
	}

	public TarefaDTO() {
	}
}