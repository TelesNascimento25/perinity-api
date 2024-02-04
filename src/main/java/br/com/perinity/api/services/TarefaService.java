package br.com.perinity.api.services;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import br.com.perinity.api.models.Departamento;
import br.com.perinity.api.models.Pessoa;
import br.com.perinity.api.models.Tarefa;
import br.com.perinity.api.models.DTO.PessoaDTO;
import br.com.perinity.api.models.DTO.TarefaDTO;
import br.com.perinity.api.models.mapper.TarefaMapper;
import br.com.perinity.api.repositories.DepartamentoRepository;
import br.com.perinity.api.repositories.PessoaRepository;
import br.com.perinity.api.repositories.TarefaRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;

@ApplicationScoped
public class TarefaService {

	@Inject
	TarefaRepository tarefaRepository;

	@Inject
	PessoaRepository pessoaRepository;

	@Inject
	TarefaMapper tarefaMapper;
	
	@Inject
	DepartamentoRepository departamentoRepository;

	@Transactional
	public TarefaDTO adicionarTarefa(TarefaDTO tarefaDTO) {
	    Tarefa tarefa = tarefaMapper.toEntity(tarefaDTO);
	    if (tarefaDTO.getDepartamentoId() != null) {
	        Departamento departamento = departamentoRepository.findById(tarefaDTO.getDepartamentoId());
	        if (departamento == null) {
	            throw new WebApplicationException("Departamento with id of " + tarefaDTO.getDepartamentoId() + " does not exist.", 404);
	        }
	        tarefa.setDepartamento(departamento);
	    }
	    if (tarefaDTO.getPessoa() != null && tarefaDTO.getPessoa().getId() != null) {
	        Pessoa pessoa = pessoaRepository.findById(tarefaDTO.getPessoa().getId());
	        if (pessoa == null) {
	            throw new WebApplicationException("Pessoa with id of " + tarefaDTO.getPessoa().getId() + " does not exist.", 404);
	        }
	        tarefa.setPessoa(pessoa);
	    }
	    tarefaRepository.persist(tarefa);
	    return tarefaMapper.toDTO(tarefa);
	}



	@Transactional
	public TarefaDTO alocarPessoa(Long id, PessoaDTO pessoaDTO) {
		Tarefa tarefa = tarefaRepository.findById(id);
		if (tarefa == null) {
			throw new WebApplicationException("Tarefa com o id: " + id + " não existe.", 404);
		}

		Pessoa pessoaExistente = pessoaRepository.findById(pessoaDTO.getId());
		if (pessoaExistente == null) {
			throw new WebApplicationException("Pessoa com o id: " + pessoaDTO.getId() + " não existe.", 404);
		}

		if (!pessoaExistente.getDepartamento().equals(tarefa.getDepartamento())) {
			throw new WebApplicationException("Pessoa and Tarefa não pertencem ao mesmo departamento", 400);
		}

		tarefa.setPessoa(pessoaExistente);
		tarefaRepository.persist(tarefa);

		return tarefaMapper.toDTO(tarefa);
	}

	@Transactional
	public TarefaDTO finalizarTarefa(Long id) {
		Tarefa tarefa = tarefaRepository.findById(id);
		if (tarefa == null) {
			throw new WebApplicationException("Tarefa with id of " + id + " does not exist.", 404);
		}

		tarefa.setFinalizado(true);
		tarefaRepository.persist(tarefa);

		return tarefaMapper.toDTO(tarefa);
	}

	public List<TarefaDTO> getTarefasPendentes() {
		List<Tarefa> tarefas = tarefaRepository.find("pessoa is null order by prazo").page(Page.ofSize(3)).list();

		if (tarefas.isEmpty()) {
			throw new WebApplicationException("Nenhuma tarefa pendente encontrada.", 404);
		}

		return tarefas.stream().map(tarefaMapper::toDTO).filter(Objects::nonNull).collect(Collectors.toList());
	}
}