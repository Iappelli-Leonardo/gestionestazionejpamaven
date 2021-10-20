package it.prova.gestionestazionejpamaven.dao.citta;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.gestionestazionejpamaven.model.Citta;


public class CittaDaoImpl implements CittaDao{

	private EntityManager entityManager;

	public List<Citta> list() throws Exception {
		return entityManager.createQuery("from Citta", Citta.class).getResultList();
	}

	public Citta get(Long id) throws Exception {
		return entityManager.find(Citta.class, id);
	}

	public void update(Citta input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		input = entityManager.merge(input);
		
	}

	public void insert(Citta input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(input);
	}

	public void delete(Citta input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(input));
		
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		
	}

	public Citta findByIdFetchingStazioni(Long id) throws Exception {
		TypedQuery<Citta> query = entityManager
				.createQuery("select c FROM Citta c left join fetch c.stazioni s where c.id = :idCitta", Citta.class);
		query.setParameter("idCitta", id);
		return query.getResultList().stream().findFirst().orElse(null);
	}
	
	

}
