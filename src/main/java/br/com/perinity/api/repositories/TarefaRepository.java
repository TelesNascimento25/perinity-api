package br.com.perinity.api.repositories;

import java.util.List;

import br.com.perinity.api.models.Tarefa;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TarefaRepository implements PanacheRepository<Tarefa> {
	public Tarefa findById(Long id) {
		return find("id", id).firstResult();
	}

	public List<Tarefa> listAll() {
		return listAll();
	}

	public void persist(Tarefa tarefa) {
		Tarefa.persist(tarefa);
	}

	public void delete(Long id) {
		Tarefa.deleteById(id);
	}
}