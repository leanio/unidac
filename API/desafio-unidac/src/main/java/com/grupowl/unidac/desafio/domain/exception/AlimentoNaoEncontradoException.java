package com.grupowl.unidac.desafio.domain.exception;

public class AlimentoNaoEncontradoException extends EntidadeNaoEncontrada {

	private static final long serialVersionUID = 1L;

	public AlimentoNaoEncontradoException(Long alimentoId) {
		super(String.format("Alimento de id %d não foi encontrado", alimentoId));
	}

}
