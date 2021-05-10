package com.grupowl.unidac.desafio.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.grupowl.unidac.desafio.api.dto.response.AlimentoResponse;
import com.grupowl.unidac.desafio.domain.model.Alimento;

@Component
public class AlimentoResponseAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public AlimentoResponse toResponse(Alimento alimento) {
		return modelMapper.map(alimento, AlimentoResponse.class);
	}
	
	public List<AlimentoResponse> toListResponse(List<Alimento> alimentos) {
		return alimentos.stream().map(alimento -> toResponse(alimento)).collect(Collectors.toList());
	}
	
}
