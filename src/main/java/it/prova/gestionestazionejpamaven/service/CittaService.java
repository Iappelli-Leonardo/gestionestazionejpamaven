package it.prova.gestionestazionejpamaven.service;

import java.util.List;

import it.prova.gestionestazionejpamaven.dao.citta.CittaDao;
import it.prova.gestionestazionejpamaven.model.Citta;
import it.prova.gestionestazionejpamaven.model.Stazione;



public interface CittaService {

	public List<Citta> listAll() throws Exception;

	public Citta caricaSingoloElemento(Long id) throws Exception;
	
	public Citta caricaSingoloElementoEagerStazione(Long id) throws Exception;

	public void aggiorna(Citta cittaInstance) throws Exception;

	public void inserisciNuovo(Citta cittaInstance) throws Exception;

	public void rimuovi(Citta cittaInstance) throws Exception;

	public void aggiungiStazione(Citta CittaInstance, Stazione stazioneInstance) throws Exception;
	
	public void creaECollegaCittaEStazione(Citta cittaTransientInstance, Stazione stazioneTransientInstance) throws Exception;

	// per injection
	public void setCittaDAO(CittaDao cittaDao);
}
