package com.grupowl.unidac.desafio.infrastructure.repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import com.grupowl.unidac.desafio.domain.model.Alimento;
import com.grupowl.unidac.desafio.domain.model.Colaborador;
import com.grupowl.unidac.desafio.domain.repository.ColaboradorRepository;

@Component
public class ColaboradorRepositoryImpl implements ColaboradorRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public Colaborador salvar(Colaborador colaborador) {
		return colaborador.isNovo() ? adicionar(colaborador) : atualizar(colaborador);
	}
	
	public void remover(Long colaboradorId) {
		Query queryAlimento = entityManager.createNativeQuery("DELETE FROM alimento WHERE colaborador_id = :colaboradorId");
		Query queryColaborador = entityManager.createNativeQuery("DELETE FROM colaborador WHERE id = :colaboradorId");
		
		queryAlimento.setParameter("colaboradorId", colaboradorId);
		queryColaborador.setParameter("colaboradorId", colaboradorId);
		
		queryAlimento.executeUpdate();
		int colaboradoresRemovidos = queryColaborador.executeUpdate();
		
		if (colaboradoresRemovidos == 0) {
			throw new EmptyResultDataAccessException(1);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Colaborador> buscarTodos() {
		Query query = entityManager.createNativeQuery("SELECT id, nome, cpf FROM colaborador", Colaborador.class);
		
		return query.getResultList();
	}
	
	public Optional<Colaborador> buscarPeloId(Long colaboradorId) {
		Query query = entityManager.createNativeQuery("SELECT id, nome, cpf FROM colaborador WHERE id = :id", Colaborador.class);
		query.setParameter("id", colaboradorId);
		
		Colaborador colaborador = null;
		
		try {
			colaborador = (Colaborador) query.getSingleResult();
		} catch (Exception e) {
			
		}
		
		return Optional.ofNullable(colaborador);
	}
	
	public Optional<Colaborador> buscarPeloCpf(String cpf) {
		Query query = entityManager.createNativeQuery("SELECT id, nome, cpf FROM colaborador WHERE cpf = :cpf", Colaborador.class);
		query.setParameter("cpf", cpf);
		
		Colaborador colaborador = null;
		
		try {
			colaborador = (Colaborador) query.getSingleResult();
		} catch (NoResultException e) {
			
		}
		
		return Optional.ofNullable(colaborador);
	}
	
	public void detach(Colaborador colaborador) {
		entityManager.detach(colaborador);
	}
	
	private Colaborador adicionar(Colaborador colaborador) {
		Query query = entityManager.createNativeQuery("INSERT INTO colaborador (nome, cpf) VALUES (:nome, :cpf) RETURNING id");
		query.setParameter("nome", colaborador.getNome());
		query.setParameter("cpf", colaborador.getCpf());
		
		BigInteger colaboradorId = (BigInteger)query.getSingleResult();
		
		colaborador.setId(colaboradorId.longValue());
		
		return colaborador;
	}
	
	private Colaborador atualizar(Colaborador colaborador) {
		Query queryColaborador = entityManager.createNativeQuery("UPDATE colaborador SET nome = :nome, cpf = :cpf WHERE id = :id");
		queryColaborador.setParameter("id", colaborador.getId());
		queryColaborador.setParameter("nome", colaborador.getNome());
		queryColaborador.setParameter("cpf", colaborador.getCpf());
		queryColaborador.executeUpdate();
	
		String idAlimentos = "0";
		for (Alimento alimento : colaborador.getAlimentos()) {
			if (!alimento.isNovo()) {
				idAlimentos += String.format(",%d", alimento.getId());
			}
		}
		
		String sqlAlimento = String.format("DELETE FROM alimento WHERE colaborador_id = %d AND id NOT IN (%s)", colaborador.getId(), idAlimentos);
		Query queryAlimento = entityManager.createNativeQuery(sqlAlimento);
		queryAlimento.executeUpdate();
		
		return colaborador;
	}

}
