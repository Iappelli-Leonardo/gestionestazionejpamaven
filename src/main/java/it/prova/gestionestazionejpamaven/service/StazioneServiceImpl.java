package it.prova.gestionestazionejpamaven.service;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestionestazionejpamaven.dao.EntityManagerUtil;
import it.prova.gestionestazionejpamaven.dao.stazione.StazioneDao;
import it.prova.gestionestazionejpamaven.model.Stazione;
import it.prova.gestionestazionejpamaven.model.Treno;

public class StazioneServiceImpl implements StazioneService{

	private StazioneDao stazioneDAO;

	public List<Stazione> listAll() throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			stazioneDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return stazioneDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	public Stazione caricaSingoloElemento(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			stazioneDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return stazioneDAO.get(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	public Stazione caricaSingoloElementoEagerTreno(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			stazioneDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return stazioneDAO.findByIdFetchingTreni(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	public void aggiorna(Stazione cittaInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			stazioneDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			stazioneDAO.update(cittaInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
		
	}

	public void inserisciNuovo(Stazione cittaInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			stazioneDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			stazioneDAO.insert(cittaInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
		
	}

	public void rimuovi(Stazione cittaInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			stazioneDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			stazioneDAO.delete(cittaInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}

	}

	public void aggiungiTreno(Stazione stazioneInstance, Treno trenoInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			stazioneDAO.setEntityManager(entityManager);

			// 'attacco' alla sessione di hibernate i due oggetti
			// così jpa capisce che se risulta presente quel cd non deve essere inserito
			stazioneInstance = entityManager.merge(stazioneInstance);
			// attenzione che genereInstance deve essere già presente (lo verifica dall'id)
			// se così non è viene lanciata un'eccezione
			trenoInstance = entityManager.merge(trenoInstance);

			stazioneInstance.getTreni().add(trenoInstance);
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

	public void creaECollegaStazioneETreno(Stazione stazioneTransientInstance, Treno trenoTransientInstance)
			throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			stazioneDAO.setEntityManager(entityManager);
			
			stazioneTransientInstance = entityManager.merge(stazioneTransientInstance);
			trenoTransientInstance = entityManager.merge(trenoTransientInstance);

			trenoTransientInstance.addToStazioni(stazioneTransientInstance);


			// inserisco il cd
			stazioneDAO.insert(stazioneTransientInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	public void setStazioneDAO(StazioneDao stazioneDao) {
		this.stazioneDAO = stazioneDao;
		
	}

}
