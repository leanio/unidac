package com.grupowl.unidac.desafio.api.dto.request;

import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CPF;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ColaboradorRequest {
	
	@NotBlank(message = "O campo nome é obrigatório")
	private String nome;
	
	@NotBlank(message = "O campo CPF é obrigatório")
	@CPF(message = "CPF inválido")
	private String cpf;
	
	@Valid
	private Set<AlimentoPersonalizadoRequest> alimentos;
	
	@Getter
	@Setter
	@EqualsAndHashCode
	public static class AlimentoPersonalizadoRequest {
		
		private Long id;
		
		@NotBlank(message = "O campo nome é obrigatório")
		private String nome;
		
		public void setNome(String nome) {
			this.nome = nome.trim().toUpperCase();
		}
	} 

}
