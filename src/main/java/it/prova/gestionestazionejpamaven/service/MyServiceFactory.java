package it.prova.gestionestazionejpamaven.service;

import it.prova.gestionestazionejpamaven.dao.MyDaoFactory;

public class MyServiceFactory {

	private static CittaService ordineServiceInstance = null;
	private static StazioneService stazioneServiceInstance = null;
	private static TrenoService trenoServiceInstance = null;

	public static CittaService getCittaServiceInstance() {
		if (ordineServiceInstance == null)
			ordineServiceInstance = new CittaServiceImpl();

		ordineServiceInstance.setCittaDAO(MyDaoFactory.getCittaDAOInstance());

		return ordineServiceInstance;
	}

	public static StazioneService getStazioneServiceInstance() {
		if (stazioneServiceInstance == null)
			stazioneServiceInstance = new StazioneServiceImpl();

		stazioneServiceInstance.setStazioneDAO(MyDaoFactory.getStazioneDAOInstance());

		return stazioneServiceInstance;
	}

	public static TrenoService getTrenoServiceInstance() {
		if (trenoServiceInstance == null)
			trenoServiceInstance = new TrenoServiceImpl();

		trenoServiceInstance.setTrenoDAO(MyDaoFactory.getTrenoDAOInstance());

		return trenoServiceInstance;
	}

}