package com.grupowl.unidac.desafio.domain.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.grupowl.unidac.desafio.domain.exception.ColaboradorNaoEncontradoException;
import com.grupowl.unidac.desafio.domain.exception.EntidadeEmUso;
import com.grupowl.unidac.desafio.domain.model.Colaborador;
import com.grupowl.unidac.desafio.domain.repository.ColaboradorRepository;

@Service
public class CadastroColaboradorService {
	
	@Autowired
	private ColaboradorRepository colaboradorRepository;
	
	@Autowired
	private CadastroAlimentoService cadastroAlimentoService;
	
	@Transactional
	public Colaborador salvar(Colaborador colaborador) {
		colaboradorRepository.detach(colaborador);
		
		String cpf = colaborador.getCpf();

		Optional<Colaborador> colaboradorCpf = colaboradorRepository.buscarPeloCpf(cpf);
		
		if (colaboradorCpf.isPresent() && !colaborador.equals(colaboradorCpf.get())) {
			throw new EntidadeEmUso(String.format("O CPF %s se encontra em uso", cpf));
		}
		
		Colaborador colaboradorSalvo = colaboradorRepository.salvar(colaborador);
		
		colaborador.getAlimentos().forEach(alimento -> {
			
			if (!alimento.isNovo()) {
				cadastroAlimentoService.buscarOuFalhar(alimento.getId());
			}
			
			cadastroAlimentoService.salvar(alimento);
		});
			
		return colaboradorSalvo;
	}
	
	@Transactional
	public void remover(Long colaboradorId) {
		try {
			colaboradorRepository.remover(colaboradorId);			
		} catch(EmptyResultDataAccessException e) {
			throw new ColaboradorNaoEncontradoException(colaboradorId);
		}
	}
	
	public Colaborador buscarOuFalhar(Long colaboradorId) {
		return colaboradorRepository.buscarPeloId(colaboradorId).orElseThrow(() -> new ColaboradorNaoEncontradoException(colaboradorId));
	}
	
}
