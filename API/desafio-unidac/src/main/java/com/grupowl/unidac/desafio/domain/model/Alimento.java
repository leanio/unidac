package com.grupowl.unidac.desafio.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "alimento")
public class Alimento {
	
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@EqualsAndHashCode.Include
	@Column(name = "nome", nullable = false, unique = true)
	private String nome;
	
	@ManyToOne
	private Colaborador colaborador;
	
	public boolean isNovo() {
		return id == null;
	}
	
	public void setNome(String nome) {
		this.nome = nome.trim().toUpperCase();
	}

}
