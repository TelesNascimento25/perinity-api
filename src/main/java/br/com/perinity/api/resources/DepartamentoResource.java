package br.com.perinity.api.resources;

import java.util.List;

import br.com.perinity.api.models.DTO.DepartamentoDTO;
import br.com.perinity.api.services.DepartamentoService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("departamentos")
@Consumes(value = MediaType.APPLICATION_JSON)
@Produces(value = MediaType.APPLICATION_JSON)
public class DepartamentoResource {

	@Inject
	DepartamentoService service;

	@POST
	@Transactional
	public Response adicionarDepartamento(DepartamentoDTO departamentoDTO) {
		DepartamentoDTO departamentoAdicionado = service.adicionarDepartamento(departamentoDTO);
		return Response.ok(departamentoAdicionado.toEntity()).status(201).build();
	}

	@GET
	public List<DepartamentoDTO> listarDepartamentos() {
		return service.listarDepartamentos();
	}

}