package it.prova.gestionestazionejpamaven.dao.stazione;

import it.prova.gestionestazionejpamaven.dao.IBaseDAO;
import it.prova.gestionestazionejpamaven.model.Stazione;


public interface StazioneDao extends IBaseDAO<Stazione> {

	public Stazione findByIdFetchingTreni(Long id) throws Exception;

}
