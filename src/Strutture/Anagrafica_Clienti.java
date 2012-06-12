package Strutture;

/**
 * @author Alessandro Basile
 * @version 1.0
 */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;
import Entity.Cliente;


	// TODO - RIVEDERE IN BASE AL DATABASE; 
/*
 * Classe per la gestione dell'anagrafica clienti
 */
public class Anagrafica_Clienti
{
	/*
	 *	Gestione dell'anagrafica mediante una struttura di tipo HashMap
	 *	String - Considero un il Codice_Azienda come chiave del dizionario
	 *	LinkedList - Ad ogni chiave associo una lista di clienti 
	 */
	private HashMap<String,LinkedList<Cliente>> anagrafica;
	
	public Anagrafica_Clienti()
	{
		//	Inizializzo la struttura
		anagrafica = new HashMap<String, LinkedList<Cliente>>();
	}
	
	/*
	 * Metodo privato per il controllo di esistenza di un cliente
	 * in una lista clienti associata ad un azienda
	 */
	private boolean check_Exist(final LinkedList<Cliente> lista, final Cliente c2)
	{
		for(Cliente c1 : lista)
		{
			if(c1.equals(c2))
				return true;
		}return false;
	}
	
	/*
	 * 	Metodo per l'aggiunta di un cliente alla lista
	 *  associata ad un azienda
	 */
	public void add_Cliente_in_Azienda(final String cod_azienda, final Cliente nuovo)
	{
		//	Considerto tutte le chiavi (ovvero i codici_azienda)
		//	presenti nella struttura 
		Set<String> chiavi = anagrafica.keySet();
		
		//	Controllo che il codice azienda sia presente nelle chiavi
		//	della strattura (ovvero che esista una lista clienti
		//	associata) e aggiungo il nuovo cliente alla lista
		if(chiavi.contains(cod_azienda))
		{
			//	Controllo per sicurezza che il cliente non sia gia' presente
			//	nella lista dei quella azienda
			if(!this.check_Exist(anagrafica.get(cod_azienda), nuovo))
			{
				anagrafica.get(cod_azienda).add(nuovo);
			}
			
		} else {
			//	Se la chiave non e' presente creo una lista di clienti,
			//	Aggiungo il nuovo cliente e la associo alla nuova chiave
			LinkedList<Cliente> lista = new LinkedList<Cliente>();
			lista.add(nuovo);
			anagrafica.put(cod_azienda, lista);
		}
	}
	
	/*
	 * 	Metodo che restituisce l'Insieme delle aziende presenti
	 * 	nell'anagrafica, utilizzate come chiave;
	 */
	public Set<String> getAziende()
	{
		return this.anagrafica.keySet();
	}
	
	/*
	 * 	Metodo che restituisce la lista dei clienti
	 * 	per un dato codice azienda;
	 */
	public LinkedList<Cliente> getClienti_fromAzienda(final String cod_az)
	{
		return this.anagrafica.get(cod_az);
	}
	
	/*
	 * 	Metodo statico che permette il confronto tra due
	 * liste di clienti di una anagrafica 
	 */
	private static boolean stessi_clienti(final LinkedList<Cliente> c1, final LinkedList<Cliente> c2)
	{
		//	Variabile per controllare se le due liste sono uguali;
		boolean uguali = true;
		//	Variabile per controllo che i singoli clienti siano uguali;
		boolean clienti = false;
		
		//	Controllo che le due liste abbiano stesso numero di Clienti;
		if(c1.size() == c2.size())
		{
			//	Se hanno lo stesso numero ciclo su entrambe le liste
			//	per confrontare membro a membro se ci c'e' ugualianza
			//	tra tutti i clienti di entrambe;
			for(Cliente x : c1)
			{
				for(Cliente y : c2)
				{
					//	Quando trovo l'elemento in comune setto a vero
					//	il flag che indica l'ugualianza del cliente;
					if(x.equals(y))
						clienti = true;
				}
				//	Se dopo il ciclo il flag e' rimasto a FALSE, il
				//	cliente non e' stato trovato quindi il flag di 
				//	ugualianza viene settato a FALSE e si esce dai cicli;
				if(!clienti)
				{
					uguali = false;
					break;
				} else {
					//	Se dopo il ciclo il flag e' passato a TRUE vuol
					//	dire che il cliente e' stato trovato quindi 
					//	imposto di nuovo il controllo a FALSE per il
					//	ciclo successico
					clienti = false;
				}
			}
		} else uguali = false;
		//	Se le lunghezze sono diverse le liste saranno sicuramente
		//	diverse a prescindere;
		return uguali;
	}
	
	/*
	 * 	Metodo per confrontare due anagrafiche e controllare
	 *	se sono uguali: TRUE sono uguali, FALSE sn diverse; 
	 */
	public boolean check_differences(final Anagrafica_Clienti db_sync)
	{
		//	Flag per controllare l'ugualianze delle anagrafiche
		boolean uguali = true;
		
		//	Considerto tutte le chiavi (ovvero i codici_azienda)
		//	presenti nella struttura; 
		Set<String> chiavi = anagrafica.keySet();
		Set<String> chiavi_dbs = db_sync.getAziende();
		
		//	Controllo che il numero delle chiavi sia uguale
		//	per i due insiemi considerati;
		if(chiavi.size() == chiavi_dbs.size())
		{
			//	Per ogni chiave considero che e' contenuta nell'insieme
			//	delle chiavi della seconda anagrafica;
			for(String s1 : chiavi)
			{
				//	Se la chiave e' contenuta faccio il controllo che le
				//	due liste clienti per quella azienda siano le uguali;
				if(chiavi_dbs.contains(s1))
				{
					if(!stessi_clienti(anagrafica.get(s1), db_sync.getClienti_fromAzienda(s1)))
					{
						//	appena ne trovo uno diverso imposto il flag a
						//	FALSE e finisco il ciclo
						uguali = false;
						break;
					}
				}
				//	Se esiste almeno una chiave non contenuta nel secondo
				//	insieme di chiavi allora saranno sicuramente differenti;
				else{
					uguali = false;
					break;
				}
			}	
		}
		//	Se gli insiemi hanno lunghezza differente allora
		//	sarano sicuramente differenti;
		else uguali = false;
		
		return uguali;
	}

}
