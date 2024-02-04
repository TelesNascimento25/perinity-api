package br.com.perinity.api.repositories;

import java.util.List;
import br.com.perinity.api.models.Pessoa;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PessoaRepository implements PanacheRepository<Pessoa> {
	public Pessoa findById(Long id) {
		return find("id", id).firstResult();
	}

	public List<Pessoa> listAll() {
		return listAll(Sort.by("nome"));
	}

	public void persist(Pessoa pessoa) {
		Pessoa.persist(pessoa);
	}

	public void delete(Long id) {
		Pessoa.deleteById(id);
	}

	public List<Pessoa> buscarPorNome(String nome) {
		return Pessoa.find("nome", nome).list();
	}
}