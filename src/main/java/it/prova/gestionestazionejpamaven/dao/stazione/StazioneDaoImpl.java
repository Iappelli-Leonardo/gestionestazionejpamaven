package it.prova.gestionestazionejpamaven.dao.stazione;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.gestionestazionejpamaven.model.Stazione;

public class StazioneDaoImpl implements StazioneDao{

	private EntityManager entityManager;

	public List<Stazione> list() throws Exception {
		return entityManager.createQuery("from Stazione", Stazione.class).getResultList();
	}

	public Stazione get(Long id) throws Exception {
		return entityManager.find(Stazione.class, id);
	}

	public void update(Stazione input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		input = entityManager.merge(input);
		
	}

	public void insert(Stazione input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(input);
	}

	public void delete(Stazione input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(input));
		
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		
	}
	

	public Stazione findByIdFetchingTreni(Long id) throws Exception {
		TypedQuery<Stazione> query = entityManager
				.createQuery("select s FROM Stazione s left join fetch s.treni t where s.id = :idStazione", Stazione.class);
		query.setParameter("idStazione", id);
		return query.getResultList().stream().findFirst().orElse(null);
	}
	

}
