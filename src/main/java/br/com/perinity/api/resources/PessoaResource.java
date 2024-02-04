package br.com.perinity.api.resources;

import java.time.LocalDate;
import java.util.List;

import br.com.perinity.api.models.DTO.PessoaDTO;
import br.com.perinity.api.models.DTO.PessoaGastoDTO;
import br.com.perinity.api.services.PessoaService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("pessoas")
@Consumes(value = MediaType.APPLICATION_JSON)
@Produces(value = MediaType.APPLICATION_JSON)
public class PessoaResource {
	@Inject
	PessoaService service;

	@POST
	@Transactional
	public Response adicionarPessoa(PessoaDTO pessoaDTO) {
		PessoaDTO pessoaAdicionada = service.adicionarPessoa(pessoaDTO);
		return Response.ok(pessoaAdicionada).status(201).build();
	}

	@PUT
	@Path("/{id}")
	@Transactional
	public Response alterarPessoa(@PathParam("id") Long id, PessoaDTO pessoaDTO) {
		PessoaDTO pessoaExistente = service.alterarPessoa(id, pessoaDTO);
		return Response.ok(pessoaExistente).build();
	}

	@DELETE
	@Path("/{id}")
	@Transactional
	public Response removerPessoa(@PathParam("id") Long id) {
		service.removerPessoa(id);
		return Response.ok("Pessoa deletada com sucesso").build();
	}

	@GET
	public List<PessoaDTO> listarPessoas() {
		return service.listarPessoas();
	}

	@GET
	@Path("/gastos")
	public List<PessoaGastoDTO> buscarGastos(@QueryParam("nome") String nome, @QueryParam("prazo") LocalDate prazo) {
		return service.buscarGastos(nome, prazo);
	}
}