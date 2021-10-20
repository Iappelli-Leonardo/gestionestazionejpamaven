package it.prova.gestionestazionejpamaven.service;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestionestazionejpamaven.dao.EntityManagerUtil;
import it.prova.gestionestazionejpamaven.dao.citta.CittaDao;
import it.prova.gestionestazionejpamaven.model.Citta;
import it.prova.gestionestazionejpamaven.model.Stazione;

public class CittaServiceImpl implements CittaService {

	private CittaDao cittaDAO;

	public List<Citta> listAll() throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			cittaDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return cittaDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	public Citta caricaSingoloElemento(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			cittaDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return cittaDAO.get(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	public Citta caricaSingoloElementoEagerStazione(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			cittaDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return cittaDAO.findByIdFetchingStazioni(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	public void aggiorna(Citta cittaInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			cittaDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			cittaDAO.update(cittaInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
		
	}

	public void inserisciNuovo(Citta cittaInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			cittaDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			cittaDAO.insert(cittaInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
		
	}

	public void rimuovi(Citta cittaInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			cittaDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			cittaDAO.delete(cittaInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}

	}

	public void aggiungiStazione(Citta cittaInstance, Stazione stazioneInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			cittaDAO.setEntityManager(entityManager);

			// 'attacco' alla sessione di hibernate i due oggetti
			// così jpa capisce che se risulta presente quel cd non deve essere inserito
			stazioneInstance = entityManager.merge(stazioneInstance);
			// attenzione che genereInstance deve essere già presente (lo verifica dall'id)
			// se così non è viene lanciata un'eccezione
			cittaInstance = entityManager.merge(cittaInstance);

			cittaInstance.getStazioni().add(stazioneInstance);
			stazioneInstance.setCitta(cittaInstance);
			// l'update non viene richiamato a mano in quanto
			// risulta automatico, infatti il contesto di persistenza
			// rileva che cdInstance ora è dirty vale a dire che una sua
			// proprieta ha subito una modifica (vale anche per i Set ovviamente)
			// inoltre se risultano già collegati lo capisce automaticamente grazie agli id

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
		
	}

	public void creaECollegaCittaEStazione(Citta cittaTransientInstance, Stazione stazioneTransientInstance)
			throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			cittaDAO.setEntityManager(entityManager);

			// collego le due entità: questa cosa funziona grazie al fatto che ho
			// CascadeType.PERSIST, CascadeType.MERGE dentro l'owner della relazione (Cd in
			// questo caso)
			cittaTransientInstance.getStazioni().add(stazioneTransientInstance);

			// ********************** IMPORTANTE ****************************
			// se io rimuovo i cascade, non funziona più e si deve prima salvare il genere
			// (tramite genereDAO iniettando anch'esso) e poi
			// sfruttare i metodi addTo o removeFrom dentro Cd:
			// GenereDAO genereDAO = MyDaoFactory.getGenereDAOInstance();
			// genereDAO.setEntityManager(entityManager);
			// genereDAO.insert(genereTransientInstance);
			// cdTransientInstance.addToGeneri(genereTransientInstance);
			// in questo caso però se il genere è già presente non ne tiene conto e
			// inserirebbe duplicati, ma è logico
			// ****************************************************************

			// inserisco il cd
			cittaDAO.insert(cittaTransientInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	public void setCittaDAO(CittaDao cittaDao) {
		this.cittaDAO = cittaDao;
		
	}

	

}
