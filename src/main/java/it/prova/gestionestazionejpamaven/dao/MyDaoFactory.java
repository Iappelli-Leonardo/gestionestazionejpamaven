package it.prova.gestionestazionejpamaven.dao;

import it.prova.gestionestazionejpamaven.dao.citta.CittaDao;
import it.prova.gestionestazionejpamaven.dao.citta.CittaDaoImpl;
import it.prova.gestionestazionejpamaven.dao.stazione.StazioneDao;
import it.prova.gestionestazionejpamaven.dao.stazione.StazioneDaoImpl;
import it.prova.gestionestazionejpamaven.dao.treno.TrenoDao;
import it.prova.gestionestazionejpamaven.dao.treno.TrenoDaoImpl;

public class MyDaoFactory {

	private static CittaDao cittaDaoInstance = null;
	private static StazioneDao stazioneDaoInstance = null;
	private static TrenoDao trenoDaoInstance = null;

	public static CittaDao getCittaDAOInstance() {
		if (cittaDaoInstance == null)
			cittaDaoInstance = new CittaDaoImpl();

		return cittaDaoInstance;
	}

	public static StazioneDao getStazioneDAOInstance() {
		if (stazioneDaoInstance == null)
			stazioneDaoInstance = new StazioneDaoImpl();

		return stazioneDaoInstance;
	}

	public static TrenoDao getTrenoDAOInstance() {
		if (trenoDaoInstance == null)
			trenoDaoInstance = new TrenoDaoImpl();

		return trenoDaoInstance;
	}
}
