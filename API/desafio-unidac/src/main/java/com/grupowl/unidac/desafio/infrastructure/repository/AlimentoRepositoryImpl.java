package com.grupowl.unidac.desafio.infrastructure.repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.grupowl.unidac.desafio.domain.model.Alimento;
import com.grupowl.unidac.desafio.domain.repository.AlimentoRepository;

@Component
public class AlimentoRepositoryImpl implements AlimentoRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public Alimento salvar(Alimento alimento) {
		return alimento.isNovo() ? adicionar(alimento) : atualizar(alimento);
	}
	
	@SuppressWarnings("unchecked")
	public List<Alimento> buscarTodos() {
		Query query = entityManager.createNativeQuery("SELECT id, nome, colaborador_id FROM alimento", Alimento.class);
		
		return query.getResultList();
	}
	
	public Optional<Alimento> buscarPeloId(Long idAlimento) {
		Query query = entityManager.createNativeQuery("SELECT id, nome, colaborador_id FROM alimento WHERE id = :idAlimento", Alimento.class);
		query.setParameter("idAlimento", idAlimento);
		
		Alimento alimento = null;
		
		try {
			alimento = (Alimento) query.getSingleResult();
		} catch (NoResultException e) {
			
		}
		
		return Optional.ofNullable(alimento);
	}

	public Optional<Alimento> buscarPeloNome(String nome) {
		Query query = entityManager.createNativeQuery("SELECT id, nome, colaborador_id FROM alimento WHERE upper(nome) = upper(:nome)", Alimento.class);
		query.setParameter("nome", nome);
		
		Alimento alimento = null;
		
		try {
			alimento = (Alimento) query.getSingleResult();
		} catch (NoResultException e) {
			
		}
		
		return Optional.ofNullable(alimento);
	}
	
	public void detach(Alimento alimento) {
		entityManager.detach(alimento);
	}
	
	private Alimento adicionar(Alimento alimento) {
		Query query = entityManager.createNativeQuery("INSERT INTO alimento (nome, colaborador_id) VALUES (:nome, :colaboradorId) RETURNING id");
		query.setParameter("nome", alimento.getNome());
		query.setParameter("colaboradorId", alimento.getColaborador().getId());
		
		BigInteger alimentoId = (BigInteger)query.getSingleResult();
		
		alimento.setId(alimentoId.longValue());
		
		return alimento;
	}
	
	private Alimento atualizar(Alimento alimento) {
		Query query = entityManager.createNativeQuery("UPDATE alimento SET nome = :nome, colaborador_id = :colaboradorId WHERE id = :id");
		query.setParameter("id", alimento.getId());
		query.setParameter("nome", alimento.getNome());
		query.setParameter("colaboradorId", alimento.getColaborador().getId());
		query.executeUpdate();
		
		return alimento;
	}
	
}
