package DB_Connection;

/**
 * Classe per la gestione delle transizioni con il DB
 * di MEXAL
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

import XML_in_out.Tag;
import XML_in_out.XML_Interface;

import Entity.Azienda;
import Entity.Categoria;
import Entity.Cliente;
import Entity.Zona;
import Strutture.Anag_Clienti;
import Strutture.Categorie_Statistiche;
import Strutture.Raggruppamenti;
import Strutture.Aziende;
import Strutture.Zone;

/**
 * @author Alessandro Basile
 * @version 1.0
 */
public class transazioni_mexal extends TransazioniDB
{
	/**
	 * Strttura per gestire le aziende abilitate che devono
	 * essere importate da MEXAl (sono lette da file di config)
	 */
	private LinkedList<Tag> aziende;
	private String type;
	
	/**
	 * Costruttore Controlla connessione ed inizializza le
	 * strutture di controllo
	 * @throws DB_Not_Found_Exception
	 */
	public transazioni_mexal(String db) throws DB_Not_Found_Exception
	{
		//	controllo che la connessione presente sia effettivamente
		//	al databaase di mexal - Genero un'eccezzione se la connessione
		// non esiste o non coincide con quella al DB richiesto
		if(!DbAccess.check_connection(db))
			throw new DB_Not_Found_Exception();
		
		/*imposto i tag da adare a leggere dal file di configurazione */
		LinkedList<String> tags = new LinkedList<String>();
		tags.add("AZIENDA");
		
		// Leggo i nomi delle aziende da importare dal file di config
		aziende = XML_Interface.readFile_XML("CONFIG/aziende_abilitate.xml",
											 tags,
											 null);
		type = db;
	}
	
	/**
	 * Metodo per la standardizzazione del codice di raggruppamento
	 * elaborato x passare da un int ad una stringa aggiun gli 0
	 * Es. 4 --> 004, 80 --> 080
	 * @param Integer con il codice numerico 
	 * @return String con il codice generato
	 */
	private String raggruppamento(Integer cod)
	{
		String cod_rapp = Integer.toString(cod);
		if(cod_rapp.equals("0"))
			cod_rapp = "PE4";
		else if(cod_rapp.length() == 1)
			cod_rapp = "00".concat(cod_rapp);
		else if(cod_rapp.length() == 2)
			cod_rapp = "0".concat(cod_rapp);
		return cod_rapp;
	}
	
	/**
	 * controlla che l'azienda trovata si tra quelle da importare
	 * caricate dal file di configurazione
	 * @param cod_azienda
	 * @return true - false
	 */
	private boolean isValid_Azienda(String cod_azienda)
	{
		boolean valido = false;
		for(Tag t : aziende)
		{
			if(t.getContenuto().equals(cod_azienda))
			{
				valido = true;
				break;
			}
		}return valido;
	}
	
	/**
	 * controlla se il codice raggruppamento trovato sia tra quelli
	 * da importare impostato nel file di configurazione
	 * @param cod_ragg
	 * @return true - false
	 */
	private boolean isValid_Group(String cod_ragg)
	{
		boolean valido = false;
		for(Tag t : aziende)
		{
			if(t.getAttributo().equalsIgnoreCase(cod_ragg))
			{
				valido = true;
				break;
			}
		}return valido;
	}
	
	/*
	 * (non-Javadoc)
	 * @see DB_Connection.TransazioniDB#read_Aziende(java.util.LinkedList, java.util.HashMap)
	 */
	/**
	 * Legge dal database le aziende abilitate e le divide nelle due strutture in input
	 * Aziende - Lista delle azziende con rispettiva anagrafica
	 * Raggruppamenti - Associa ad ogni azienda 
	 */
	public void read_Aziende(Aziende aziende, Raggruppamenti raggruppamenti)
									 throws DB_Not_Found_Exception
	{
		//	Controlla che la connessione al DB sia del tipo giusto
		if(DbAccess.check_connection(type))
		{
			//	Se le variabili in input non sono inzializzate le inizializzo qui
			if(aziende == null) aziende = new Aziende();
			if(raggruppamenti == null) raggruppamenti = new Raggruppamenti();
			
			try {
				//	Leggo da file la query delle aziende
				FileReader reader = new FileReader("QUERY/aziende.sql");
				BufferedReader in = new BufferedReader(reader);
				
				String query = "";
				String line = "";
				while ((line = in.readLine()) != null )
					query = query.concat(line);
				
				//	Rimuovo gli eventuali ritorni a capo che potrebbero
				//	provocare errori in fasi esecuzioni sul DB
				query = query.replace("\n", " ");
				
				//	Preparo l'esecuzione della query e la lettura del risultato
				Statement stmt = DbAccess.getConnection().createStatement();
				ResultSet result;
				result = stmt.executeQuery(query);
				
				while(result.next())
				{
					//	Per ogni riga controllo che il rispettivo cod_azienda
					//	faccia parte delle aziende abilitate 
					String cod = result.getString("COD_AZIENDA");
					if(this.isValid_Azienda(cod))
					{
						//	Estrapolo tutte le informazioni dalle varie colonne
						Integer raggrup = new Integer(result.getInt("RAGGRUPPAMENTO"));
						String nome = result.getString("NOME_AZIENDA");
						String descrizione = result.getString("DESCRIZIONE");
						String pIVA = result.getString("PIVA");
						String indirizzo = result.getString("INDIRIZZO");
						Integer cap = new Integer(result.getInt("CAP"));
						String comune = result.getString("COMUNE");
						String provincia = result.getString("PROVINCIA");
						
						//	Formatto il codice di raggruppamento
						String cod_rag = this.raggruppamento(raggrup);
						
						//	Popole le strutture per le aziende e per i raggruppamenti
						Azienda tmp = new Azienda(cod, nome, pIVA, descrizione, indirizzo, comune, cap.intValue(), provincia);
						aziende.addAzienda(tmp);
						raggruppamenti.addAzienda_forGroup(tmp.getCod_Azienda(), cod_rag);
					}	
				}
				
			} catch (Exception e)
			{
				//	Catturo eventuali eccezioni sollevate durante l'esecuzione
				e.printStackTrace();
			}	
		}else{
			// Se il tipo di connessione non coincide allora assegno null alle strutture
			// e sollevo un eccezzione di tipo DB non trovato;
			aziende = null;
			raggruppamenti = null;
			throw new DB_Not_Found_Exception();
		}
	}
	
	
	public void read_Categorie(Categorie_Statistiche cat_s) throws DB_Not_Found_Exception
	{
		//	Controlla che connessione al DB sia del tipo giusto
		if(DbAccess.check_connection(type))
		{
			//	Controllo se la struttura non è stato inizializzata
			if(cat_s == null)
				cat_s = new Categorie_Statistiche();
			
			try{
				//	Leggo il file che contiene la query
				FileReader reader = new FileReader("QUERY/cat_clienti_fornitori.sql");
				BufferedReader in = new BufferedReader(reader);
				//	Estrapolo il testo della query
				String query = "";
				String line = "";
				while((line = in.readLine()) != null)
					query = query.concat(line);
				
				//	Rimuovo gli eventuali ritorni a capo che potrebbero
				//	provocare errori in fasi esecuzioni sul DB
				query = query.replace("\n", " ");
				
				//	Preparo l'esecuzione della query e la lettura del risultato
				Statement stmt = DbAccess.getConnection().createStatement();
				ResultSet result;
				result = stmt.executeQuery(query);
				
				while(result.next())
				{
					//	Per ogni riga controllo che il rispettivo cod_azienda
					//	faccia parte delle aziende abilitate 
					String cod = result.getString("AZIENDA");
					if(this.isValid_Azienda(cod))
					{
						Integer cod_cat = new Integer(result.getInt("CODICE"));
						String descrizione = result.getString("DESCRIZIONE");
						
						Categoria cat = new Categoria(cod, cod_cat, descrizione);
						cat_s.add_category(cat);
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
			}	
		}else{
			//	Se la connessione non esiste o non coincide sollevo
			// 	un'eccezione di tipo DB non trovato 
			cat_s = null;
			throw new DB_Not_Found_Exception();
		}
		
	}
	
	
	/* (non-Javadoc)
	 * @see DB_Connection.TransazioniDB#read_Zone(Strutture.Zone, java.lang.String)
	 */
	@Override
	public void read_Zone(Zone zone) throws DB_Not_Found_Exception
	{
		//	Controlla che la connessione al DB sia del tipo giusto
		if(DbAccess.check_connection(type))
		{
			if(zone == null) zone = new Zone();
			
			try{
				//	Leggo da file la query delle zone
				FileReader reader = new FileReader("QUERY/zone.sql");
				BufferedReader in = new BufferedReader(reader);
				
				String query = "";
				String line = "";
				while ((line = in.readLine()) != null )
					query = query.concat(line);
				
				//	Rimuovo gli eventuali ritorni a capo che potrebbero
				//	provocare errori in fasi esecuzioni sul DB
				query = query.replace("\n", " ");
				
				//	Preparo l'esecuzione della query e la lettura del risultato
				Statement stmt = DbAccess.getConnection().createStatement();
				ResultSet result;
				result = stmt.executeQuery(query);
				
				//	Elaboro il reault set
				while(result.next())
				{
					//	Per ogni riga controllo che il rispettivo cod_azienda
					//	faccia parte delle aziende abilitate 
					String cod = result.getString("AZIENDA");
					if(this.isValid_Azienda(cod))
					{
						Integer cod_zona = new Integer(result.getInt("COD_ZONA"));
						String descrizione = result.getString("DESC_ZONA");
						
						//	Creo un'azienda e la aggiunga alla lista delle zone
						Zona nuova = new Zona(cod_zona, cod, descrizione);
						zone.add_Zona(nuova);
					}
				}
				
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		} else {
			//	SE LA CONNESSIONE NON ESISTE O NON CORRISPONDE A QUELLA RICHIESTA
			//	VIENE SOLLEVATA UN'ECCEZIONE 
			zone = null;
			throw new DB_Not_Found_Exception();
		}
	}

	/* (non-Javadoc)
	 * @see DB_Connection.TransazioniDB#read_Anag_Clienti(Strutture.Anag_Clienti)
	 */
	@Override
	public void read_Anag_Clienti(Anag_Clienti anag) throws DB_Not_Found_Exception
	{
			//	Controlla che la connessione al DB sia del tipo giusto
			if(DbAccess.check_connection(type))
			{
				if(anag == null)
					anag = new Anag_Clienti();
				
				try{
					//	LEGGO LA QUERY DELL'ANAGRAFICA DA FILE
					FileReader reader = new FileReader("QUERY/clienti_anagrafica.sql");
					BufferedReader in = new BufferedReader(reader);
					
					String query = "";
					String line = "";
					while ((line = in.readLine()) != null )
						query = query.concat(line);
					
					//	Rimuovo gli eventuali ritorni a capo che potrebbero
					//	provocare errori in fasi esecuzioni sul DB
					query = query.replace("\n", " ");
					
					//	Preparo l'esecuzione della query e la lettura del risultato
					Statement stmt = DbAccess.getConnection().createStatement();
					ResultSet result;
					result = stmt.executeQuery(query);
					
					//	Elaboro il reault set
					while(result.next())
					{
						//	Per ogni riga controllo che il rispettivo cod_azienda
						//	faccia parte delle aziende abilitate 
						String cod_ragg = result.getString("RAGGRUPPAMENTO");
						if(this.isValid_Group(cod_ragg))
						{
							//	Estrapolo il contenuto di ogni tupla;
							String conto = result.getString("CONTO");
							String rag_soc = result.getString("RAGSOC");
							String indirizzo = result.getString("INDIRIZZO");
							Integer cap = new Integer(result.getInt("CAP"));
							String local = result.getString("LOCALITA");
							String pr = result.getString("PROV");
							String local_spe = result.getString("LOC_SPED");
							String tel = result.getString("TEL");
							String piva = result.getString("PIVA");
							
							//	Creo un cliente con le informazioni ritrovate
							Cliente tmp = new Cliente(cod_ragg, conto, rag_soc, piva, indirizzo, cap, local, pr, local_spe, tel);
							//	Aggiungo il cliente appena creato
							anag.add_Cliente(tmp);
						}
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
				
			} else {
				//	SE LA CONNESSIONE NON ESISTE O NON CORRISPONDE A QUELLA RICHIESTA
				//	VIENE SOLLEVATA UN'ECCEZIONE 
				anag = null;
				throw new DB_Not_Found_Exception();
			}	
	}
	
}
