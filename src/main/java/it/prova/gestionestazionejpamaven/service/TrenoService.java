package it.prova.gestionestazionejpamaven.service;

import java.util.List;

import it.prova.gestionestazionejpamaven.dao.treno.TrenoDao;
import it.prova.gestionestazionejpamaven.model.Stazione;
import it.prova.gestionestazionejpamaven.model.Treno;

public interface TrenoService {

	public List<Treno> listAll() throws Exception;

	public Treno caricaSingoloElemento(Long id) throws Exception;

	public Treno caricaSingoloElementoEagerArticoli(Long id) throws Exception;

	public void aggiorna(Treno trenoInstance) throws Exception;

	public void inserisciNuovo(Treno trenoInstance) throws Exception;

	public void rimuovi(Treno trenoInstance) throws Exception;

	public void aggiungiTreno(Treno trenoInstance, Stazione stazioneInstance) throws Exception;

	public void creaECollegaTrenoEStazione(Treno trenoTransientInstance, Stazione stazioneTransientInstance)
			throws Exception;

	public void scollegaTreno(Stazione stazioneInstance, Treno trenoInstance) throws Exception;

	public List<Integer> cercaNumeroAbitantiByTreno(Long id) throws Exception;

	// per injection
	public void setTrenoDAO(TrenoDao trenoDao);

}
