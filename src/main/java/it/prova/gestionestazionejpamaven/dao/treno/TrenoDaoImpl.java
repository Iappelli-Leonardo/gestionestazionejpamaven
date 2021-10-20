package it.prova.gestionestazionejpamaven.dao.treno;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.gestionestazionejpamaven.model.Treno;

public class TrenoDaoImpl implements TrenoDao {

	private EntityManager entityManager;

	public List<Treno> list() throws Exception {
		return entityManager.createQuery("from Treno", Treno.class).getResultList();
	}

	public Treno get(Long id) throws Exception {
		return entityManager.find(Treno.class, id);
	}

	public void update(Treno input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		input = entityManager.merge(input);
		
	}

	public void insert(Treno input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(input);
	}

	public void delete(Treno input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(input));
		
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		
	}

	public Treno findByIdFetchingStazione(Long id) throws Exception {
		TypedQuery<Treno> query = entityManager
				.createQuery("select t FROM Treno t left join fetch t.stazioni s where t.id = :idTreno", Treno.class);
		query.setParameter("idTreno", id);
		return query.getResultList().stream().findFirst().orElse(null);
	}

	
	@Override
	public List<Integer> findNumeroAbitantiByTreno(Long id) throws Exception {
		TypedQuery<Integer> query = entityManager
				.createQuery("select c.numeroAbitanti FROM Citta c left join c.stazioni s left join s.treni t where t.id = :idTreno", Integer.class);
		query.setParameter("idTreno", id);
		return query.getResultList();
	}
	
	
	
}
