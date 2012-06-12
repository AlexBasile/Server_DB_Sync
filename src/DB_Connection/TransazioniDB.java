package DB_Connection;
/**
 * Classe per la gestione delle transazioni con il DB
 */

import Strutture.Anag_Clienti;
import Strutture.Aziende;
import Strutture.Categorie_Statistiche;
import Strutture.Raggruppamenti;
import Strutture.Zone;

/**
 * @author Alessandro Basile
 * @version 1.0
 */
public abstract class TransazioniDB
{
	/**
	 * Funzione per leggere le aziende presenti nel database,
	 * vengono organizzati in una struttura che contiene le 
	 * aziende ed una struttura che associa ad ogni azienda
	 * il suo codice di raggruppamento;
	 * @param aziende
	 * @param raggruppamenti
	 * @throws DB_Not_Found_Exception
	 */
	public abstract void read_Aziende(Aziende aziende, Raggruppamenti raggruppamenti)
									  throws DB_Not_Found_Exception;
	
	/**
	 * Metodo per la lettura delle zone dal DB, i dati sono 
	 * organizzati in una struttura che associa ad ogni zona
	 * la propria descrizione e il relativo COD_AZIENDA;
	 * @param zone
	 * @throws DB_Not_Found_Exception
	 */
	public abstract void read_Zone(Zone zone) throws DB_Not_Found_Exception;

	/**
	 * Metodo per la lettura delle categorie statistiche dal DB
	 * i dati sono organizzati in una struttura che associa da 
	 * ogni categoria un identificativa ed un COD_AZIENDA relativo;
	 * @param cat_s
	 * @throws DB_Not_Found_Exception
	 */
	public abstract void read_Categorie(Categorie_Statistiche cat_s) throws DB_Not_Found_Exception;
	
	/**
	 * Metodo per le lettura dell'anangrafica del DB dal Database
	 * @param Anag_Clienti anag
	 * @throws DB_Not_Found_Exception
	 */
	public abstract void read_Anag_Clienti(Anag_Clienti anag) throws DB_Not_Found_Exception;
	
}
