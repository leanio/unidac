package com.grupowl.unidac.desafio.api.dto.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ColaboradorResponse {
	
	private Long id;
	
	private String nome;
	
	private String cpf;
	
	private List<AlimentoPersonalizadoResponse> alimentos;
	
	@Getter
	@Setter
	public static class AlimentoPersonalizadoResponse {
		
		private Long id;
		
		private String nome;
		
	}

}
