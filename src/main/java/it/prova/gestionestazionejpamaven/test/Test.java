package it.prova.gestionestazionejpamaven.test;

import it.prova.gestionestazionejpamaven.dao.EntityManagerUtil;
import it.prova.gestionestazionejpamaven.model.Citta;
import it.prova.gestionestazionejpamaven.model.Stazione;
import it.prova.gestionestazionejpamaven.model.Treno;
import it.prova.gestionestazionejpamaven.service.CittaService;
import it.prova.gestionestazionejpamaven.service.MyServiceFactory;
import it.prova.gestionestazionejpamaven.service.StazioneService;
import it.prova.gestionestazionejpamaven.service.TrenoService;

public class Test {
	public static void main(String[] args) {

		CittaService cittaServiceInstance = MyServiceFactory.getCittaServiceInstance();
		StazioneService stazioneServiceInstance = MyServiceFactory.getStazioneServiceInstance();
		TrenoService trenoServiceInstance = MyServiceFactory.getTrenoServiceInstance();

		try {

			System.out.println("In tabella Treno ci sono " + cittaServiceInstance.listAll().size() + " elementi.");
			System.out
					.println("In tabella Stazione ci sono " + stazioneServiceInstance.listAll().size() + " elementi.");
			System.out.println("In tabella Citta ci sono " + trenoServiceInstance.listAll().size() + " elementi.");

			System.out.println(
					"*****************************************inizio********************************************************");

			testInserisciNuovaCitta(cittaServiceInstance);
			
			testInserimentoNuovoStazione(cittaServiceInstance, stazioneServiceInstance);

			testCollegaTrenoAStazione(cittaServiceInstance, stazioneServiceInstance, trenoServiceInstance);
			
			testInserimentoNuovaStazioneECitta(cittaServiceInstance, stazioneServiceInstance);
			
			
			//testCollegaTrenoAStazioneACitta(cittaServiceInstance, stazioneServiceInstance, trenoServiceInstance);
			
			
			//testCollegaTrenoAStazioneACittaERemoveCitta(cittaServiceInstance, stazioneServiceInstance, trenoServiceInstance);	
		
			//testUnaCittaUnaStazioneDueTreniCancellaCitta(cittaServiceInstance, stazioneServiceInstance, trenoServiceInstance);
			
			//testNumeroAbitantiByTreno(cittaServiceInstance, stazioneServiceInstance, trenoServiceInstance);
			
			System.out.println(
					"******************************************fine****************************************************");
			
			System.out.println("In tabella Genere ci sono " + cittaServiceInstance.listAll().size() + " elementi.");
			System.out.println("In tabella Cd ci sono " + stazioneServiceInstance.listAll().size() + " elementi.");
			System.out.println("In tabella Cd ci sono " + trenoServiceInstance.listAll().size() + " elementi.");

		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			// questa è necessaria per chiudere tutte le connessioni quindi rilasciare il
			// main
			EntityManagerUtil.shutdown();
		}
	}
	
	private static void testInserisciNuovaCitta(CittaService cittaServiceInstance) throws Exception {
		System.out.println(".......testInserisciNuovaCitta inizio.............");

		Citta cittaInstance = new Citta("roma",281);
		cittaServiceInstance.inserisciNuovo(cittaInstance);
		if (cittaInstance.getId() == null)
			throw new RuntimeException("testInserisciNuovaCitta fallito ");

		System.out.println(".......testInserisciNuovaCitta fine: PASSED.............");
	}
	
	private static void testInserimentoNuovoStazione(CittaService cittaServiceInstance,
			StazioneService stazioneServiceInstance) throws Exception {
		System.out.println(".......testInserimentoNuovoStazione inizio.............");

		Citta cittaInstance = new Citta("terni",234);
		cittaServiceInstance.inserisciNuovo(cittaInstance);

		Stazione stazioneInstance = new Stazione("huola", "via franco 12", cittaInstance);
		stazioneServiceInstance.inserisciNuovo(stazioneInstance);
		if (stazioneInstance.getId() == null)
			throw new RuntimeException("testInserimentoNuovoStazione fallito ");

		System.out.println(".......testInserimentoNuovoStazione fine: PASSED.............");
	}
	
	private static void testCollegaTrenoAStazione(CittaService cittaServiceInstance,
			StazioneService stazioneServiceInstance, TrenoService trenoServiceInstance) throws Exception {
		System.out.println(".......testCollegaTrenoAdStazione inizio.............");

		Citta cittaInstance = new Citta("peppe",573);
		cittaServiceInstance.inserisciNuovo(cittaInstance);

		Stazione stazioneInstance = new Stazione("huola", "via franco 12", cittaInstance);
		stazioneServiceInstance.inserisciNuovo(stazioneInstance);
		if (stazioneInstance.getId() == null)
			throw new RuntimeException("testCollegaTrenoAStazione fallito: inserimento articolo non riuscito ");

		Treno nuovoTreno = new Treno("trenoross", "sasa");
		trenoServiceInstance.inserisciNuovo(nuovoTreno);
		if (nuovoTreno.getId() == null)
			throw new RuntimeException("testCollegaTrenoAStazione fallito: categoria non inserito ");

		// collego
		trenoServiceInstance.aggiungiTreno(nuovoTreno, stazioneInstance);

		// ricarico eager per forzare il test
		Stazione articoloReloaded = stazioneServiceInstance
				.caricaSingoloElementoEagerTreno(stazioneInstance.getId());
		if (articoloReloaded.getTreni().isEmpty())
			throw new RuntimeException("testCollegaTrenoAStazione fallito: categoria non collegato ");

		System.out.println(".......testCollegaTrenoAdStazione fine: PASSED.............");
	}
	
	private static void testInserimentoNuovaStazioneECitta(CittaService cittaServiceInstance,
			StazioneService stazioneServiceInstance) throws Exception {
		System.out.println(".......testInserimentoNuovaStazioneECitta inizio.............");

		Citta cittaInstance = new Citta("terni",234);
		cittaServiceInstance.inserisciNuovo(cittaInstance);

		Stazione stazioneInstance1 = new Stazione("romolo", "via romola 12", cittaInstance);
		Stazione stazioneInstance2 = new Stazione("pompelmi", "via francesco 32", cittaInstance);
		
		stazioneServiceInstance.inserisciNuovo(stazioneInstance1);
		stazioneServiceInstance.inserisciNuovo(stazioneInstance2);
		if (stazioneInstance1.getId() == null)
			throw new RuntimeException("testInserimentoNuovaStazioneECitta1 fallito ");
		if (stazioneInstance2.getId() == null)
			throw new RuntimeException("testInserimentoNuovaStazioneECitta2 fallito ");

		System.out.println(".......testInserimentoNuovaStazioneECitta fine: PASSED.............");
	}
	
	private static void testCollegaTrenoAStazioneACitta(CittaService cittaServiceInstance,
			StazioneService stazioneServiceInstance, TrenoService trenoServiceInstance) throws Exception {
		System.out.println(".......testCollegaTrenoAStazioneACitta inizio.............");

		Citta cittaInstance = new Citta("terni",234);
		cittaServiceInstance.inserisciNuovo(cittaInstance);

		Stazione stazioneInstance= new Stazione("roccaforte", "via loreti 732", cittaInstance);
		
		stazioneServiceInstance.inserisciNuovo(stazioneInstance);
		
		if (stazioneInstance.getId() == null)
			throw new RuntimeException("testCollegaTrenoAStazioneACitta fallito ");

		Treno nuovoTreno1 = new Treno("regionale", "ross");
		Treno nuovoTreno2 = new Treno("frecciarossa", "veloc");
		
		trenoServiceInstance.inserisciNuovo(nuovoTreno1);
		trenoServiceInstance.inserisciNuovo(nuovoTreno2);
		
		if (nuovoTreno1.getId() == null)
			throw new RuntimeException("testCollegaTrenoAStazioneACitta1 fallito: categoria non inserito ");
		if (nuovoTreno2.getId() == null)
			throw new RuntimeException("testCollegaTrenoAStazioneACitta2 fallito: categoria non inserito ");

		// collego
		trenoServiceInstance.aggiungiTreno(nuovoTreno1, stazioneInstance);
		trenoServiceInstance.aggiungiTreno(nuovoTreno2, stazioneInstance);

		// ricarico eager per forzare il test
		Stazione articoloReloaded = stazioneServiceInstance
				.caricaSingoloElementoEagerTreno(stazioneInstance.getId());
		if (articoloReloaded.getTreni().isEmpty())
			throw new RuntimeException("testCollegaTrenoAStazioneACitta fallito: categoria non collegato ");

		System.out.println(".......testCollegaTrenoAStazioneACitta fine: PASSED.............");
	}
	
	
	private static void testCollegaTrenoAStazioneACittaERemoveCitta(CittaService cittaServiceInstance,
			StazioneService stazioneServiceInstance, TrenoService trenoServiceInstance) throws Exception {
		System.out.println(".......testCollegaTrenoAStazioneACittaERemoveCitta inizio.............");

		
		Citta cittaInstance = new Citta("frascati",543);
		cittaServiceInstance.inserisciNuovo(cittaInstance);

		Stazione stazioneInstance= new Stazione("pontevecchio", "via orlandi 432", cittaInstance);
		
		stazioneServiceInstance.inserisciNuovo(stazioneInstance);
		
		if (stazioneInstance.getId() == null)
			throw new RuntimeException("testCollegaTrenoAStazioneACittaERemoveCitta fallito ");
		

		Treno nuovoTreno1 = new Treno("regionale", "ross");
		Treno nuovoTreno2 = new Treno("frecciarossa", "veloc");
		
		trenoServiceInstance.inserisciNuovo(nuovoTreno1);
		trenoServiceInstance.inserisciNuovo(nuovoTreno2);
		
		if (nuovoTreno1.getId() == null)
			throw new RuntimeException("testCollegaTrenoAStazioneACittaERemoveCitta fallito: categoria non inserito ");
		if (nuovoTreno2.getId() == null)
			throw new RuntimeException("testCollegaTrenoAStazioneACittaERemoveCitta fallito: categoria non inserito ");

		// collego
		trenoServiceInstance.aggiungiTreno(nuovoTreno1, stazioneInstance);
		trenoServiceInstance.aggiungiTreno(nuovoTreno2, stazioneInstance);

		// ricarico eager per forzare il test
		Stazione articoloReloaded = stazioneServiceInstance
				.caricaSingoloElementoEagerTreno(stazioneInstance.getId());
		if (articoloReloaded.getTreni().isEmpty())
			throw new RuntimeException("testCollegaTrenoAStazioneACittaERemoveCitta fallito: categoria non collegato ");
		
		System.out.println(".......testCollegaTrenoAStazioneACittaERemoveCitta fine: PASSED.............");
	}
	
	public static void testUnaCittaUnaStazioneDueTreniCancellaCitta(CittaService cittaServiceInstance,
			StazioneService stazioneServiceInstance, TrenoService trenoServiceInstance) throws Exception {

		System.out.println("######################## testUnaCittaUnaStazioneDueTreniCancellaCitta inizio ########################");


		Citta cittaInstance = new Citta("frascati",543);
		cittaServiceInstance.inserisciNuovo(cittaInstance);

		Stazione stazioneInstance= new Stazione("pontevecchio", "via orlandi 432", cittaInstance);
		
		stazioneServiceInstance.inserisciNuovo(stazioneInstance);
		Citta cittaReloaded = cittaServiceInstance.caricaSingoloElemento(cittaInstance.getId());
		if (cittaReloaded.getStazioni().size() != 1)
			throw new RuntimeException(
					"testUnaCittaUnaStazioneDueTreniCancellaCitta fallito: la stazione non è stata collegata");
		
		if (stazioneInstance.getId() == null)
			throw new RuntimeException("testCollegaTrenoAStazioneACittaERemoveCitta fallito ");
		

		Treno nuovoTreno1 = new Treno("regionale", "ross");
		Treno nuovoTreno2 = new Treno("frecciarossa", "veloc");
		
		trenoServiceInstance.inserisciNuovo(nuovoTreno1);
		trenoServiceInstance.inserisciNuovo(nuovoTreno2);
		
		if (nuovoTreno1.getId() == null)
			throw new RuntimeException("testCollegaTrenoAStazioneACittaERemoveCitta fallito: categoria non inserito ");
		if (nuovoTreno2.getId() == null)
			throw new RuntimeException("testCollegaTrenoAStazioneACittaERemoveCitta fallito: categoria non inserito ");

		// collego
		trenoServiceInstance.aggiungiTreno(nuovoTreno1, stazioneInstance);
		trenoServiceInstance.aggiungiTreno(nuovoTreno2, stazioneInstance);

		// ricarico eager per forzare il test
		Stazione stazioneReloaded = stazioneServiceInstance
				.caricaSingoloElementoEagerTreno(stazioneInstance.getId());
		if (stazioneReloaded.getTreni().isEmpty())
			throw new RuntimeException("testCollegaTrenoAStazioneACittaERemoveCitta fallito: categoria non collegato ");
		
		//
		
//		disaccoppio la stazione dai treni

		trenoServiceInstance.scollegaTreno(stazioneReloaded, nuovoTreno1);

		trenoServiceInstance.scollegaTreno(stazioneReloaded, nuovoTreno2);

		Stazione stazioneReloaded2 = stazioneServiceInstance.caricaSingoloElementoEagerTreno(stazioneReloaded.getId());
		if (stazioneReloaded2.getTreni().size() != 0)
			throw new RuntimeException(
					"testUnaCittaUnaStazioneDueTreniCancellaCitta fallito: le due stazion non sono state disaccoppiate");

//		cancello la stazione
		stazioneServiceInstance.rimuovi(stazioneReloaded2);

		Citta cittaReloaded2 = cittaServiceInstance.caricaSingoloElemento(cittaReloaded.getId());
		if (cittaReloaded2.getStazioni().size() != 0)
			throw new RuntimeException(
					"testUnaCittaUnaStazioneDueTreniCancellaCitta fallito: la stazione non è stata rimossa");

//		cancello la citta
		cittaServiceInstance.rimuovi(cittaReloaded2);
		Citta cittaReloaded3 = cittaServiceInstance.caricaSingoloElemento(cittaReloaded2.getId());
		if (cittaReloaded3 != null)
			throw new RuntimeException("testInserimentoNuovaCitta fallito: non ho rimosso la citta ");

		
		
		System.out.println(
				"##################### testUnaCittaUnaStazioneDueTreniCancellaCitta fine: PASSED ########################");
	}
	
	
	private static void testNumeroAbitantiByTreno(CittaService cittaServiceInstance,
			StazioneService stazioneServiceInstance, TrenoService trenoServiceInstance) throws Exception {
		System.out.println(".......testNumeroAbitantiByTreno inizio.............");

		//creazione blocco 1
		Citta citta1 = new Citta("forlì" , 30);
		cittaServiceInstance.inserisciNuovo(citta1);

		Stazione stazione1 = new Stazione("Trieste", "Via Forte 11", citta1);
		stazioneServiceInstance.inserisciNuovo(stazione1);

		Treno treno1 = new Treno("frecciaverde", "asd");
		trenoServiceInstance.inserisciNuovo(treno1);
		
		stazioneServiceInstance.aggiungiTreno(stazione1, treno1);
	
	
		//creazione blocco 2
		Citta citta2 = new Citta("napoli", 230);
		cittaServiceInstance.inserisciNuovo(citta2);

		Stazione stazione2 = new Stazione("Termini" , "Via Termini", citta2);
		stazioneServiceInstance.inserisciNuovo(stazione2);
		
		Treno treno2 = new Treno("FrecciaBianca" , "23ds");
		trenoServiceInstance.inserisciNuovo(treno2);
		
		stazioneServiceInstance.aggiungiTreno(stazione2, treno2);
		

		//creazione blocco 3
		Citta citta3 = new Citta("napoli", 436);
		cittaServiceInstance.inserisciNuovo(citta3);

		Stazione stazione3 = new Stazione("tuscolana" , "Via tusolana", citta3);
		stazioneServiceInstance.inserisciNuovo(stazione3);
		
		Treno treno3 = new Treno("FrecciaBianca" , "23ds");
		trenoServiceInstance.inserisciNuovo(treno3);
		
		stazioneServiceInstance.aggiungiTreno(stazione3, treno3);

		//creazione blocco 4
		Citta citta4 = new Citta("torino", 543);
		cittaServiceInstance.inserisciNuovo(citta4);

		Stazione stazione4 = new Stazione("ungaretti" , "Via ugo", citta4);
		stazioneServiceInstance.inserisciNuovo(stazione4);
		
		Treno treno4 = new Treno("frecciafianca" , "23ds");
		trenoServiceInstance.inserisciNuovo(treno4);
		
		Long idTrenoTorinoId = treno4.getId();
		
		
		//faccio collegamenti
		stazioneServiceInstance.aggiungiTreno(stazione1, treno4);
		stazioneServiceInstance.aggiungiTreno(stazione2, treno4);
		stazioneServiceInstance.aggiungiTreno(stazione3, treno4);
		stazioneServiceInstance.aggiungiTreno(stazione4, treno4);
		
		
		if(trenoServiceInstance.cercaNumeroAbitantiByTreno(idTrenoTorinoId).size() == 0) {
			throw new RuntimeException("testNumeroAbitantiByTreno fallito");
		}
		System.out.println(".......testNumeroAbitantiByTreno fine: PASSED.....");
		
	}
	
	
	
}
