package com.grupowl.unidac.desafio.api.mapper;

import java.util.HashSet;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.grupowl.unidac.desafio.api.dto.request.ColaboradorRequest;
import com.grupowl.unidac.desafio.domain.model.Colaborador;

@Component
public class ColaboradorCadastroRequestDissambler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Colaborador toDomainObject(ColaboradorRequest colaboradorRequest) {
		Colaborador colaborador = modelMapper.map(colaboradorRequest, Colaborador.class);
		colaborador.getAlimentos().forEach(alimento -> alimento.setColaborador(colaborador));
		
		return colaborador;
	}
	
	public void copyToDomainObject(ColaboradorRequest colaboradorRequest, Colaborador colaborador) {
		colaborador.setAlimentos(new HashSet<>());
		modelMapper.map(colaboradorRequest, colaborador);
		
		colaborador.getAlimentos().forEach(alimento -> alimento.setColaborador(colaborador));
	}

}
