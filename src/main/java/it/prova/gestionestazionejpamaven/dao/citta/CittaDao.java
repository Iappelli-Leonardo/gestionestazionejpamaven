package it.prova.gestionestazionejpamaven.dao.citta;


import it.prova.gestionestazionejpamaven.dao.IBaseDAO;
import it.prova.gestionestazionejpamaven.model.Citta;

public interface CittaDao extends IBaseDAO<Citta> {

	public Citta findByIdFetchingStazioni(Long id) throws Exception;

}
