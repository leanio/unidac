package com.grupowl.unidac.desafio.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.grupowl.unidac.desafio.api.dto.response.AlimentoResponse;
import com.grupowl.unidac.desafio.api.mapper.AlimentoResponseAssembler;
import com.grupowl.unidac.desafio.domain.model.Alimento;
import com.grupowl.unidac.desafio.domain.repository.AlimentoRepository;

@RestController
@RequestMapping("/alimentos")
public class AlimentoController {
	
	@Autowired
	private AlimentoRepository alimentoRepository;
	
	@Autowired
	private AlimentoResponseAssembler alimentoResponseAssembler;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<AlimentoResponse> buscarTodos() {
		List<Alimento> alimentos = alimentoRepository.buscarTodos();
		
		return alimentoResponseAssembler.toListResponse(alimentos);
	}

}
