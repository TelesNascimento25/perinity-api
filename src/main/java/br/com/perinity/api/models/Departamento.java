package br.com.perinity.api.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Departamento extends PanacheEntityBase implements Serializable {
	
	private static final long serialVersionUID = 1432221187370227137L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	public String titulo;
	@OneToMany(mappedBy = "departamento", fetch = FetchType.EAGER)
    public List<Pessoa> pessoas = new ArrayList<>();
	public Departamento() {
	}
}
