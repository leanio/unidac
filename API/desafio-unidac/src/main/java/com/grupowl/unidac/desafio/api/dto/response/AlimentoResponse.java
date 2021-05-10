package com.grupowl.unidac.desafio.api.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlimentoResponse {
	
	private Long id;
	
	private String nome;
	
	private ColaboradorPersonalizadoResponse colaborador;
	
	@Getter
	@Setter
	public static class ColaboradorPersonalizadoResponse {
		
		private String cpf;
		
	}

}
