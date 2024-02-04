package br.com.perinity.api.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.com.perinity.api.models.Departamento;
import br.com.perinity.api.models.Pessoa;
import br.com.perinity.api.models.Tarefa;
import br.com.perinity.api.models.DTO.PessoaDTO;
import br.com.perinity.api.models.DTO.PessoaGastoDTO;
import br.com.perinity.api.models.mapper.PessoaMapper;
import br.com.perinity.api.repositories.PessoaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;

@ApplicationScoped
public class PessoaService {
	@Inject
	PessoaRepository repository;
	@Inject
	PessoaMapper mapper;

	public PessoaDTO adicionarPessoa(PessoaDTO pessoaDTO) {
		Pessoa pessoa = mapper.toEntity(pessoaDTO);
		Departamento departamento = Departamento.findById(pessoaDTO.getDepartamentoId());
		if (departamento == null) {
			throw new WebApplicationException(
					"Departamento with id of " + pessoaDTO.getDepartamentoId() + " does not exist.", 404);
		}
		pessoa.setDepartamento(departamento);
		repository.persist(pessoa);
		return mapper.toDTO(pessoa);
	}

	public PessoaDTO alterarPessoa(Long id, PessoaDTO pessoaDTO) {
		Pessoa pessoaExistente = repository.findById(id);
		if (pessoaExistente != null) {
			pessoaExistente.setNome(pessoaDTO.getNome());
			Departamento departamento = Departamento.findById(pessoaDTO.getDepartamentoId());
			if (departamento == null) {
				throw new WebApplicationException(
						"Departamento with id of " + pessoaDTO.getDepartamentoId() + " does not exist.", 404);
			}
			pessoaExistente.setDepartamento(departamento);
			repository.persist(pessoaExistente);
			return mapper.toDTO(pessoaExistente);
		}
		throw new WebApplicationException("Pessoa with id of " + id + " does not exist.", 404);
	}

	public void removerPessoa(Long id) {
		Pessoa pessoa = repository.findById(id);
		if (pessoa != null) {
			repository.delete(pessoa);
		} else {
			throw new WebApplicationException("Pessoa with id of " + id + " does not exist.", 404);
		}
	}

	public List<PessoaDTO> listarPessoas() {
		List<Pessoa> pessoas = repository.listAll();
		return pessoas.stream().map(mapper::toDTO).collect(Collectors.toList());
	}

	public List<PessoaGastoDTO> buscarGastos(String nome, LocalDate prazo) {
		List<Pessoa> pessoas = repository.buscarPorNome(nome);
		return pessoas.stream().map(pessoa -> calcularMediaHorasGastas(pessoa, prazo)).collect(Collectors.toList());
	}

	private PessoaGastoDTO calcularMediaHorasGastas(Pessoa pessoa, LocalDate prazo) {
		List<Tarefa> tarefasNoPrazo = pessoa.getTarefas().stream().filter(tarefa -> tarefa.getPrazo().equals(prazo))
				.collect(Collectors.toList());
		double mediaHorasGastas = tarefasNoPrazo.stream().mapToDouble(tarefa -> tarefa.getDuracao()).average()
				.orElse(0.0) / 60.0;

		PessoaGastoDTO dto = new PessoaGastoDTO();
		dto.setNome(pessoa.getNome());
		dto.setPrazo(prazo);
		dto.setMediaHorasGastas(mediaHorasGastas);
		return dto;
	}
}
