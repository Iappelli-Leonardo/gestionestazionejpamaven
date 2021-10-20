package it.prova.gestionestazionejpamaven.service;

import java.util.List;

import it.prova.gestionestazionejpamaven.dao.stazione.StazioneDao;
import it.prova.gestionestazionejpamaven.model.Stazione;
import it.prova.gestionestazionejpamaven.model.Treno;

public interface StazioneService {
	
	public List<Stazione> listAll() throws Exception;

	public Stazione caricaSingoloElemento(Long id) throws Exception;

	public Stazione caricaSingoloElementoEagerTreno(Long id) throws Exception;

	public void aggiorna(Stazione stazioneIntsance) throws Exception;

	public void inserisciNuovo(Stazione stazioneIntsance) throws Exception;

	public void rimuovi(Stazione stazioneIntsance) throws Exception;

	public void aggiungiTreno(Stazione StazioneInstance, Treno trenoInstance) throws Exception;

	public void creaECollegaStazioneETreno(Stazione stazioneTransientInstance, Treno trenoTransientInstance)
			throws Exception;

	// per injection
	public void setStazioneDAO(StazioneDao stazioneDao);
}
