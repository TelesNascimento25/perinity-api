package br.com.perinity.api.repositories;

import java.util.List;

import br.com.perinity.api.models.Departamento;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DepartamentoRepository implements PanacheRepository<Departamento> {

	public Departamento findById(Long id) {
		return find("id", id).firstResult();
	}

	public List<Departamento> listAll() {
		return listAll(Sort.by("titulo"));
	}

	public void persist(Departamento departamento) {
		Departamento.persist(departamento);
	}

	public void delete(Long id) {
		Departamento.deleteById(id);
	}
}
