package com.grupowl.unidac.desafio.domain.exception;

public class ColaboradorNaoEncontradoException extends EntidadeNaoEncontrada {

	private static final long serialVersionUID = 1L;

	public ColaboradorNaoEncontradoException(Long colaboradorId) {
		super(String.format("Colaborador de id %d não foi encontrado", colaboradorId));
	}

}
