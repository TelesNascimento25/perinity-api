package br.com.perinity.api.services;

import java.util.List;
import java.util.stream.Collectors;

import br.com.perinity.api.models.Departamento;
import br.com.perinity.api.models.DTO.DepartamentoDTO;
import br.com.perinity.api.models.mapper.DepartamentoMapper;
import br.com.perinity.api.repositories.DepartamentoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class DepartamentoService {
	@Inject
	DepartamentoRepository repository;
	@Inject
	DepartamentoMapper mapper;

	public DepartamentoDTO adicionarDepartamento(DepartamentoDTO departamentoDTO) {
		Departamento departamento = mapper.toEntity(departamentoDTO);
		repository.persist(departamento);
		return mapper.toDTO(departamento);
	}

	public List<DepartamentoDTO> listarDepartamentos() {
		List<Departamento> departamentos = repository.listAll();
		return departamentos.stream().map(mapper::toDTO).collect(Collectors.toList());
	}
}
