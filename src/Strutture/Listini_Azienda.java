package Strutture;

/**
 * @author Alessandro Basile
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import Entity.Riga_Form_Prezzo;

/**
 * Classe per la gestione di tutte le righe formazione
 * prezzo di tutte le aziende presenti
 */
public class Listini_Azienda
{
	/*
	 *	Gestione dell'anagrafica mediante una struttura di tipo HashMap
	 *	String - Considero un il Codice_Azienda come chiave del dizionario
	 *	ArrayList - Ad ogni chiave associo una lista di riga formazione prezzo 
	 */
	private HashMap<String, ArrayList<Riga_Form_Prezzo>> listini;
	
	//	Inizializzo la struttura dati
	public Listini_Azienda()
	{
		listini = new HashMap<String, ArrayList<Riga_Form_Prezzo>>();
	}
	
	/*
	 * 	Metodo per controllare l'esistenza di una riga di formazione
	 * 	prezzo all'interno della lista delle righe assegnata all'azienda
	 */
	private boolean check_Exist(final ArrayList<Riga_Form_Prezzo> listino,
								final Riga_Form_Prezzo riga)
	{
		for(Riga_Form_Prezzo r1 : listino)
		{
			if(r1.equals(riga))
				return true;
		} return false;	
	}
	
	/*
	 * 	Metodo per l'aggiunta di una riga formazione prezzo
	 *  alla lista associata ad un azienda
	 */
	public void add_Riga_in_Azienda(final String cod_azienda, final Riga_Form_Prezzo r1)
	{
		//	Considerto tutte le chiavi (ovvero i codici_azienda)
		//	presenti nella struttura 
		Set<String> chiavi = listini.keySet();

		//	Controllo che il codice azienda sia presente nelle chiavi
		//	della strattura (ovvero che esista una riga formazione prezzo
		//	associata) e aggiungo la nuova riga alla lista
		if(chiavi.contains(cod_azienda))
		{	
			//	Controllo per sicurezza che la riga non sia gia' presente
			//	nella lista dei quella azienda
			if(!this.check_Exist(listini.get(cod_azienda), r1))
			{
				listini.get(cod_azienda).add(r1);
			} else {
				//	Se la chiave non e' presente creo una lista di righe prezzo,
				//	Aggiungo la nuova riga e la associo alla nuova chiave
				ArrayList<Riga_Form_Prezzo> lista = new ArrayList<Riga_Form_Prezzo>();
				lista.add(r1);
				listini.put(cod_azienda, lista);
			}
		}
	}
	
	/*
	 * 	Metodo che restituisce l'Insieme delle aziende presenti
	 * 	nell'insieme dei listini, utilizzate come chiave;
	 */
	public Set<String> getAziende()
	{
		return this.listini.keySet();
	}
	
	/*
	 * 	Metodo che restituisce la lista delle righe formazione prezzo
	 * 	per un dato codice azienda;
	 */
	public ArrayList<Riga_Form_Prezzo> getRighe_fromAzienda(final String cod_az)
	{
		return this.listini.get(cod_az);
	}
	
	/*
	 * 	Metodo statico che permette il confronto tra due
	 * 	liste di righe di formazione prezzo di una anagrafica 
	 */
	private static boolean stesse_righe(final ArrayList<Riga_Form_Prezzo> c1,
										final ArrayList<Riga_Form_Prezzo> c2)
	{
		//	Variabile per controllare se le due liste sono uguali;
		boolean uguali = true;
		//	Variabile per controllo che le singole righe formaz siano uguali;
		boolean righe = false;
		
		//	Controllo che le due liste abbiano stesso numero di righe di formz;
		if(c1.size() == c2.size())
		{
			//	Se hanno lo stesso numero, ciclo su entrambe le liste
			//	per confrontare membro a membro se ci c'e' ugualianza
			//	tra tutti le righe di entrambe;
			for(Riga_Form_Prezzo x : c1)
			{
				for(Riga_Form_Prezzo y : c2)
				{
					//	Quando trovo l'elemento in comune setto a vero
					//	il flag che indica l'ugualianza della riga;
					if(x.equals(y))
						righe = true;
				}
				//	Se dopo il ciclo il flag e' rimasto a FALSE, la
				//	riga non e' stata trovato quindi il flag di 
				//	ugualianza viene settato a FALSE e si esce dai cicli;
				if(!righe)
				{
					uguali = false;
					break;
				} else {
					//	Se dopo il ciclo il flag e' passato a TRUE vuol
					//	dire che la riga e' stata trovata quindi 
					//	imposto di nuovo il controllo a FALSE per il
					//	ciclo successico
					righe = false;
				}
			}
		} else uguali = false;
		//	Se le lunghezze sono diverse le liste saranno sicuramente
		//	diverse a prescindere;
		return uguali;
	}
	
	/*
	 * 	Metodo per confrontare due listini_Aziende e controllare
	 *	se sono uguali: TRUE sono uguali, FALSE sn diverse; 
	 */
	public boolean check_differences(final Listini_Azienda db_sync)
	{
		//	Flag per controllare l'ugualianze delle anagrafiche
		boolean uguali = true;
		
		//	Considerto tutte le chiavi (ovvero i codici_azienda)
		//	presenti nella struttura; 
		Set<String> chiavi = listini.keySet();
		Set<String> chiavi_dbs = db_sync.getAziende();
		
		//	Controllo che il numero delle chiavi sia uguale
		//	per i due insiemi considerati;
		if(chiavi.size() == chiavi_dbs.size())
		{
			//	Per ogni chiave considero se e' contenuta nell'insieme
			//	delle chiavi della seconda anagrafica;
			for(String s1 : chiavi)
			{
				//	Se la chiave e' contenuta faccio il controllo che le
				//	due liste di righe associate a quella azienda siano le uguali;
				if(chiavi_dbs.contains(s1))
				{
					if(!stesse_righe(listini.get(s1), db_sync.getRighe_fromAzienda(s1)))
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
