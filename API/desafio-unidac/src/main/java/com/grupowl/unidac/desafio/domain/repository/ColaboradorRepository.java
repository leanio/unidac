package com.grupowl.unidac.desafio.domain.repository;

import java.util.List;
import java.util.Optional;

import com.grupowl.unidac.desafio.domain.model.Colaborador;

public interface ColaboradorRepository {

	Colaborador salvar(Colaborador colaborador);

	void remover(Long colaboradorId);

	List<Colaborador> buscarTodos();

	Optional<Colaborador> buscarPeloId(Long colaboradorId);

	Optional<Colaborador> buscarPeloCpf(String cpf);

	void detach(Colaborador colaborador);

}
