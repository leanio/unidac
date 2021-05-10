package com.grupowl.unidac.desafio.domain.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupowl.unidac.desafio.domain.exception.AlimentoNaoEncontradoException;
import com.grupowl.unidac.desafio.domain.exception.RegraNegocioException;
import com.grupowl.unidac.desafio.domain.model.Alimento;
import com.grupowl.unidac.desafio.domain.repository.AlimentoRepository;

@Service
public class CadastroAlimentoService {
	
	@Autowired
	private AlimentoRepository alimentoRepository;
	
	@Transactional
	public Alimento salvar(Alimento alimento) {
		alimentoRepository.detach(alimento);
		
		String nome = alimento.getNome();
		
		Optional<Alimento> alimentoNome = alimentoRepository.buscarPeloNome(nome);
		
		if (alimentoNome.isPresent() && !alimento.equals(alimentoNome.get())) {
			throw new RegraNegocioException(String.format("O alimento '%s' já foi cadastrado", nome));
		}
		
		return alimentoRepository.salvar(alimento);
	}
	
	public Alimento buscarOuFalhar(Long alimentoId) {
		return alimentoRepository.buscarPeloId(alimentoId).orElseThrow(() -> new AlimentoNaoEncontradoException(alimentoId));
	}

}
