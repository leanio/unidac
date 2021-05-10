package com.grupowl.unidac.desafio.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.grupowl.unidac.desafio.api.dto.request.ColaboradorRequest;
import com.grupowl.unidac.desafio.api.dto.response.ColaboradorResponse;
import com.grupowl.unidac.desafio.api.mapper.ColaboradorCadastroRequestDissambler;
import com.grupowl.unidac.desafio.api.mapper.ColaboradorResponseAssembler;
import com.grupowl.unidac.desafio.domain.model.Colaborador;
import com.grupowl.unidac.desafio.domain.service.CadastroColaboradorService;
import com.grupowl.unidac.desafio.infrastructure.repository.ColaboradorRepositoryImpl;

@RestController
@RequestMapping("/colaboradores")
public class ColaboradorController {
	
	@Autowired
	private CadastroColaboradorService cadastroColaboradorService;
	
	@Autowired
	private ColaboradorCadastroRequestDissambler colaboradorCadastroRequestDissambler;
	
	@Autowired
	private ColaboradorResponseAssembler colaboradorResponseAssembler;
	
	@Autowired
	private ColaboradorRepositoryImpl colaboradorRepository;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ColaboradorResponse adicionar(@RequestBody @Valid ColaboradorRequest colaboradorRequest) {
		Colaborador colaborador = colaboradorCadastroRequestDissambler.toDomainObject(colaboradorRequest);
		colaborador = cadastroColaboradorService.salvar(colaborador);

		return colaboradorResponseAssembler.toResponse(colaborador);
	}
	
	@PutMapping("/{colaboradorId}")
	@ResponseStatus(HttpStatus.OK)
	public ColaboradorResponse atualizar(@PathVariable Long colaboradorId, @RequestBody @Valid ColaboradorRequest colaboradorRequest) {
		Colaborador colaborador = cadastroColaboradorService.buscarOuFalhar(colaboradorId);
		colaboradorCadastroRequestDissambler.copyToDomainObject(colaboradorRequest, colaborador);
		colaborador = cadastroColaboradorService.salvar(colaborador);
		
		return colaboradorResponseAssembler.toResponse(colaborador);
	}
	
	@DeleteMapping("/{colaboradorId}")
	@ResponseStatus(HttpStatus.OK)
	public void remover(@PathVariable Long colaboradorId) {
		cadastroColaboradorService.remover(colaboradorId);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ColaboradorResponse> buscarTodos() {
		List<Colaborador> colaboradores = colaboradorRepository.buscarTodos();
		
		return colaboradorResponseAssembler.toListResponse(colaboradores);
	}
	
	@GetMapping("/{colaboradorId}")
	@ResponseStatus(HttpStatus.OK)
	public ColaboradorResponse buscarPeloId(@PathVariable Long colaboradorId) {
		Colaborador colaborador = cadastroColaboradorService.buscarOuFalhar(colaboradorId);
		
		return colaboradorResponseAssembler.toResponse(colaborador);
	}

}
