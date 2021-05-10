package com.grupowl.unidac.desafio.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.grupowl.unidac.desafio.api.dto.response.ColaboradorResponse;
import com.grupowl.unidac.desafio.domain.model.Colaborador;

@Component
public class ColaboradorResponseAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public ColaboradorResponse toResponse(Colaborador colaborador) {
		return modelMapper.map(colaborador, ColaboradorResponse.class);
	}
	
	public List<ColaboradorResponse> toListResponse(List<Colaborador> colaboradores) {
		return colaboradores.stream().map(colaborador -> toResponse(colaborador)).collect(Collectors.toList());
	}
	
}
