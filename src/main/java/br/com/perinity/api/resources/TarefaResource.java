package br.com.perinity.api.resources;

import java.util.List;

import br.com.perinity.api.models.DTO.PessoaDTO;
import br.com.perinity.api.models.DTO.TarefaDTO;
import br.com.perinity.api.services.TarefaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/tarefas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TarefaResource {

	@Inject
	TarefaService tarefaService;

	@POST
	public Response adicionarTarefa(TarefaDTO tarefaDTO) {
		TarefaDTO tarefaResponse = tarefaService.adicionarTarefa(tarefaDTO);
		return Response.ok(tarefaResponse).status(201).build();
	}

	@PUT
	@Path("/alocar/{id}")
	public Response alocarPessoa(@PathParam("id") Long id, PessoaDTO pessoaDTO) {
		TarefaDTO tarefaDTO = tarefaService.alocarPessoa(id, pessoaDTO);
		return Response.ok(tarefaDTO).build();
	}

	@PUT
	@Path("/finalizar/{id}")
	public Response finalizarTarefa(@PathParam("id") Long id) {
		TarefaDTO tarefaDTO = tarefaService.finalizarTarefa(id);
		return Response.ok("Tarefa finalizada com sucesso!").build();
	}

	@GET
	@Path("/pendentes")
	public List<TarefaDTO> getTarefasPendentes() {
		return tarefaService.getTarefasPendentes();
	}
}
