package br.com.perinity.api.models;

import java.io.Serializable;
import java.time.LocalDate;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Tarefa extends PanacheEntityBase implements Serializable {
	
	private static final long serialVersionUID = -8574522383725735935L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	public String titulo;
	public String descricao;
	public LocalDate prazo;
	@ManyToOne(fetch = FetchType.EAGER)
	public Departamento departamento;
	public Long duracao;
	@ManyToOne(fetch = FetchType.EAGER)
	public Pessoa pessoa;
	public Boolean finalizado;
	public Tarefa() {
	}
}