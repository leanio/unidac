package com.grupowl.unidac.desafio.domain.exception;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.Builder;
import lombok.Getter;

@ControllerAdvice
public class UnidacExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<Erro> erros = Arrays.asList(
				Erro.builder().descricao(ex.getMessage()).build()
		);

		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);

	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<Erro> erros = criarListaErros(ex.getBindingResult());
		
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({EntidadeNaoEncontrada.class})
	public ResponseEntity<Object> handleEntidadeNaoEncontradaException(EntidadeNaoEncontrada ex, WebRequest request) {
		List<Erro> erros = Arrays.asList(
					Erro.builder().descricao(ex.getMessage()).build()
		);
		
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({RegraNegocioException.class})
	public ResponseEntity<Object> handleRegraNegocioException(RegraNegocioException ex, WebRequest request) {
		List<Erro> erros = Arrays.asList(
					Erro.builder().descricao(ex.getMessage()).build()
		);
		
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	public List<Erro> criarListaErros(BindingResult bindingResult) {
		return bindingResult.getFieldErrors().stream().map(erro -> {
			return Erro.builder()
					.descricao(erro.getDefaultMessage())
					.build();
		}).collect(Collectors.toList());
	}
	
	@Getter
	@Builder
	public static class Erro {
		private String descricao;
	}
	
}
