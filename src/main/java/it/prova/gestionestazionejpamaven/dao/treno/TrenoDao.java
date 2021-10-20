package it.prova.gestionestazionejpamaven.dao.treno;

import java.util.List;

import it.prova.gestionestazionejpamaven.dao.IBaseDAO;
import it.prova.gestionestazionejpamaven.model.Treno;

public interface TrenoDao extends IBaseDAO<Treno>{

	public Treno findByIdFetchingStazione(Long id) throws Exception;
	
	public List<Integer> findNumeroAbitantiByTreno(Long id) throws Exception;
	
}
