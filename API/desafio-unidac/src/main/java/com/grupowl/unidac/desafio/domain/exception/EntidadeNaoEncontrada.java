package com.grupowl.unidac.desafio.domain.exception;

public class EntidadeNaoEncontrada extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public EntidadeNaoEncontrada(String msg) {
		super(msg);
	}
	
}
