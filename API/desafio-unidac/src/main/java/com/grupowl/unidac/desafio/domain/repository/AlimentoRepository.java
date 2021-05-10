package com.grupowl.unidac.desafio.domain.repository;

import java.util.Optional;

import com.grupowl.unidac.desafio.domain.model.Alimento;

public interface AlimentoRepository {

	Alimento salvar(Alimento alimento);

	Optional<Alimento> buscarPeloId(Long alimentoId);

	Optional<Alimento> buscarPeloNome(String nome);

	void detach(Alimento alimento);

}
