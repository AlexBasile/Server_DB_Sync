package DB_Connection;
/**	
 * Classe per la gestione delle transizioni con il DB
 * Sincronizzato 
 */


import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.ResultSet;
import java.sql.Statement;

import Entity.Azienda;
import Entity.Categoria;
import Entity.Cliente;
import Entity.Zona;
import Mapper.DB_Mapper;
import Mapper.TableMapper;
import Strutture.Anag_Clienti;
import Strutture.Aziende;
import Strutture.Categorie_Statistiche;
import Strutture.Raggruppamenti;
import Strutture.Zone;


/**
 * @author Alessandro Basile
 * @version 1.0
 */
public class Transazioni_DBSync extends TransazioniDB {
	
	//Mapper della struttura del DB_Syncronizz
	private static DB_Mapper mapper_sync;
	private String type;

	/**
	 * Strtture che caricano in memoria i metadati che
	 * descrivono la struttura del DB
	 */
	//private static LinkedList<Tag> aziende;
	//private static LinkedList<Tag> raggruppamenti;
	//private static LinkedList<Tag> zone;
	
	//	COSTRUTTORE
	public Transazioni_DBSync(final String db) throws DB_Not_Found_Exception
	{
		//	controllo che la connessione presente sia effettivamente
		//	al databaase sincronizzato - Genero un'eccezzione se la
		//	connessione non esiste o non coincide con quella richiesta
		if(!DbAccess.check_connection(db))
			throw new DB_Not_Found_Exception();
		
		//Popola la lista dei tag delle strtture
		//aziende = read_Meta_Azienda();
		//raggruppamenti = read_Meta_Raggruppamenti();
		//zone = read_Meta_Zone();
		
		//	Costruisco il Mapper
		mapper_sync = new DB_Mapper("DB_Sync");
		type = db;
	}
	
	/* (non-Javadoc)
	 * @see DB_Connection.TransazioniDB#read_Anag_Clienti(Strutture.Anag_Clienti)
	 */
	@Override
	public void read_Anag_Clienti(Anag_Clienti anag) throws DB_Not_Found_Exception
	{
		if(anag == null)
			anag = new Anag_Clienti();
		
		try {
			//	Leggo da file la query delle aziende
			FileReader reader = new FileReader("QUERY_DB_Sync/Clienti_ANAG.sql");
			BufferedReader in = new BufferedReader(reader);
			
			//	Carico il mapper della tabelle delle aziende dal mapper del DB
			TableMapper cli = mapper_sync.getTable_fromDesc("CLIENTI_ANAGRAFICA");
			
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
				//	Estrapolo le informazioni dai recordo risultati
				String cod_ragg = result.getString(cli.getColumnName("ID_RAG"));
				String cod_cli = result.getString(cli.getColumnName("ID"));
				String rag_soc = result.getString(cli.getColumnName("RAGIONE_S"));
				String piva = result.getString(cli.getColumnName("PIVA"));
				String indirizzo = result.getString(cli.getColumnName("INDIRIZZO"));
				Integer cap = new Integer(result.getInt(cli.getColumnName("CAP")));
				String local = result.getString(cli.getColumnName("LOCALITA"));
				String pr = result.getString(cli.getColumnName("PROVINCIA"));
				String local_spe = result.getString(cli.getColumnName("SPEDIZIONE"));
				String tel = result.getString(cli.getColumnName("TELEFONO"));
				
				//	Creo un cliente con le informazioni ritrovate
				Cliente tmp = new Cliente(cod_ragg, cod_cli, rag_soc, piva, indirizzo, cap, local, pr, local_spe, tel);
				//	Aggiungo il cliente appena creato
				anag.add_Cliente(tmp);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void write_Anag_Clienti(final Anag_Clienti anag) throws DB_Not_Found_Exception
	{
		if(DbAccess.check_connection(type))
		{
			try {
				//	Catico il mapper della tabella dei clienti dal mapper del DB
				TableMapper ang_cli = mapper_sync.getTable_fromDesc("CLIENTI_ANAGRAFICA");
				
				//	Preparo l'esecuzione della query e la lettura del risultato;
				Statement stmt = DbAccess.getConnection().createStatement();
				
				//	Cancello tutti i precedenti valori presenti nella tabella;
				stmt.executeUpdate("DELETE FROM "+ ang_cli.getTable_Name() +" WHERE TRUE");
				
				//	Creo la query di UPDATE
				String update = "INSERT INTO "+ ang_cli.getTable_Name() + " (";
				
				// carico i valori delle colonne della tabella
				update += ang_cli.describe_Columns() + ") VALUES ";
				
				for(int i = 0; i < anag.num_Clienti(); i++)
				{
					String values = "("+ anag.getCliente_fromIndex(i).StringToDb() + ")";
					String query = update + values;
					stmt.executeUpdate(query);
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			throw new DB_Not_Found_Exception();
		}
	}
	
	
	/**
	 * Funzione privata statica che legge da DB sincronizzato
	 * le aziende salvate
	 * @param az - Aziende
	 */
	private void read_Aziende(Aziende az)
	{
		if(az == null)
			az = new Aziende();
		
		try {
			//	Leggo da file la query delle aziende
			FileReader reader = new FileReader("QUERY_DB_Sync/Azienda.sql");
			BufferedReader in = new BufferedReader(reader);
			
			//	Carico il mapper della tabelle delle aziende dal mapper del DB
			TableMapper azie = mapper_sync.getTable_fromDesc("AZIENDE");
			
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
				//	Estrapolo tutte le informazioni dalle varie colonne
				String cod = result.getString(azie.getColumnName("ID"));
				String nome = result.getString(azie.getColumnName("RAGIONE_S"));
				String descrizione = result.getString(azie.getColumnName("DESCRIZIONE"));
				String pIVA = result.getString(azie.getColumnName("PIVA"));
				String indirizzo = result.getString(azie.getColumnName("INDIRIZZO"));
				Integer cap = new Integer(result.getInt(azie.getColumnName("CAP")));
				String comune = result.getString(azie.getColumnName("LOCALITA"));
				String provincia = result.getString(azie.getColumnName("PROVINCIA"));
				
				//	Popole la struttura per le aziende e per i raggruppamenti
				Azienda tmp = new Azienda(cod, nome, pIVA, descrizione, indirizzo, comune, cap.intValue(), provincia);
				az.addAzienda(tmp);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void read_Raggruppamenti(Raggruppamenti rg)
	{
		try {
			//	Leggo da file la query delle aziende
			FileReader reader = new FileReader("QUERY_DB_Sync/Raggruppamenti.sql");
			BufferedReader in = new BufferedReader(reader);
			
			//	Carico il mapper della tabella dei raggrupp dal mapper del DB
			TableMapper ragg = mapper_sync.getTable_fromDesc("RAGGRUPPAMENTI");
			
			//	Leggo la query dal file
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
				String cod_az = result.getString(ragg.getColumnName("ID_AZIENDA"));
				String cod_rg = result.getString(ragg.getColumnName("ID_RAG"));
				rg.addAzienda_forGroup(cod_az, cod_rg);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see DB_Connection.TransazioniDB#read_Aziende(Strutture.Aziende, Strutture.Raggruppamenti, java.lang.String)
	 */
	/**
	 * Legge dal database le aziende ed i rispettivi raggruppamenti a cui fanno parte 
	 */
	@Override
	public void read_Aziende(Aziende aziende, Raggruppamenti raggruppamenti) throws DB_Not_Found_Exception 
	{
		//	Controlla che la connessione al DB sia del tipo giusto
		if(DbAccess.check_connection(type))
		{
			//	Se le variabili in input non sono inzializzate le inizializzo qui
			if(aziende == null) aziende = new Aziende();
			if(raggruppamenti == null) raggruppamenti = new Raggruppamenti();
			
			//	Carico le aziende dal DB
			read_Aziende(aziende);
			//	Carico i raggruppamenti dal DB
			read_Raggruppamenti(raggruppamenti);
		}else{
			// Se il tipo di connessione non coincide allora assegno null alle strutture
			// e sollevo un eccezzione di tipo DB non trovato;
			aziende = null;
			raggruppamenti = null;
			throw new DB_Not_Found_Exception();
		}
	}
	
	/**
	 * Scrive le aziende nel database sincronizzato
	 * @param Aziende
	 */
	private void write_Aziende(final Aziende az)
	{
		try {
			
			//	Carico il mapper della tabelle delle aziende dal mapper del DB
			TableMapper azie = mapper_sync.getTable_fromDesc("AZIENDE");
			
			//	Preparo l'esecuzione della query e la lettura del risultato;
			Statement stmt = DbAccess.getConnection().createStatement();
			
			//	Cancello tutti i precedenti valori presenti nella tabella;
			stmt.executeUpdate("DELETE FROM "+ azie.getTable_Name() +" WHERE TRUE");
			
			//	Creo la query di UPDATE
			String update = "INSERT INTO "+ azie.getTable_Name() + " (";
			
			//	Carico i valori delle colonne della tabella	
			update += azie.describe_Columns() + ") VALUES ";
			
			//	Inserisco tutti i valori presenta nella struttura Aziende
			for(int i = 0; i < az.num_aziende(); i++)
			{
				String values = "("+ az.getAzienda_fromIndex(i).StringToDb() + ")";
				String query = update + values;
				stmt.executeUpdate(query);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void write_raggr(final Raggruppamenti rg)
	{
		try {
			//	Preparo l'esecuzione della query e la lettura del risultato;
			Statement stmt = DbAccess.getConnection().createStatement();
			
			//	Carico il mapper della tabella dei raggrupp dal mapper del DB
			TableMapper ragg = mapper_sync.getTable_fromDesc("RAGGRUPPAMENTI");

			//	Cancello tutti i precedenti valori presenti nella tabella;
			stmt.executeUpdate("DELETE FROM "+ ragg.getTable_Name() +" WHERE TRUE");
			
			//	Creo la Query di update
			String update = "INSERT INTO "+ ragg.getTable_Name() + " (";
			
			//	Carico i valori delle colonne della tabella
			update += ragg.describe_Columns() + ") VALUES ";

			//	Inserisco nel DB i raggruppamenti
			for(String key : rg.getCodici_Raggruppamenti())
			{
				for(String cod : rg.getAziende_fromGroup(key))
				{
					String values = "(\""+ cod +"\", \""+ key +"\")";
					String query = update + values;
					stmt.executeUpdate(query);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void write_Aziende_Raggruppamenti(final Aziende az, final Raggruppamenti rg)
											 throws DB_Not_Found_Exception  
	{
		if(DbAccess.check_connection(type))
		{
			//	Scrivo le aziende nel DB
			write_Aziende(az);
			// Scrivo i raggruppamenti nel DB
			write_raggr(rg);
		} else {
			throw new DB_Not_Found_Exception();
		}
	}

	public void write_Zone(final Zone zo) throws DB_Not_Found_Exception
	{
		if(DbAccess.check_connection(type))
		{
			try {
				//	Preparo l'esecuzione della query e la lettura del risultato;
				Statement stmt = DbAccess.getConnection().createStatement();
				
				//	Carico il mapper della tabella dei raggrupp dal mapper del DB
				TableMapper zon = mapper_sync.getTable_fromDesc("ZONE");

				//	Cancello tutti i precedenti valori presenti nella tabella;
				stmt.executeUpdate("DELETE FROM "+ zon.getTable_Name() +" WHERE TRUE");
				
				//	Creo la Query di update
				String update = "INSERT INTO "+ zon.getTable_Name() + "(";
				update += zon.describe_Columns() + ") VALUES ";
				
				//	Inserisco le zone nel DB
				for(int i = 0; i < zo.num_Zone(); i++)
				{
					String values = zo.getZona_fromInt(i).values();
					String query = update + values;
					stmt.executeUpdate(query);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else {
			throw new DB_Not_Found_Exception();
		}
	}
	
	public void write_Categorie(final Categorie_Statistiche cat_s) throws DB_Not_Found_Exception
	{
		
		if(DbAccess.check_connection(type))
		{
			try {
				//	Preparo l'esecuzione della query e la lettura del risultato;
				Statement stmt = DbAccess.getConnection().createStatement();
				
				//	Carico il mapper della tabella delle categorie dal DB;
				TableMapper categor = mapper_sync.getTable_fromDesc("CATEGORIE_CLIENTI");

				//	Cancello tutti i precedenti valori presenti nella tabella;
				stmt.executeUpdate("DELETE FROM "+ categor.getTable_Name() +" WHERE TRUE");
				
				//	Creo la Query di update
				String update = "INSERT INTO " + categor.getTable_Name() +" (";
				update += categor.describe_Columns() + ") VALUES";
				
				//	Insersco le categorie nel DB
				for(int i = 0; i < cat_s.num_categeries(); i++)
				{
					String values = cat_s.getCategory_fromIndex(i).values();
					String query = update + values;
					stmt.executeUpdate(query);
				}
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else 
			throw new DB_Not_Found_Exception();
		
	}
		
	public void read_Categorie(Categorie_Statistiche cat_s) throws DB_Not_Found_Exception
	{
		if(DbAccess.check_connection(type))
		{
			if(cat_s == null)
				cat_s = new Categorie_Statistiche();
			
			try {
				//	Leggo da file la query delle zone
				FileReader reader = new FileReader("QUERY_DB_Sync/Cat_Clienti.sql");
				BufferedReader in = new BufferedReader(reader);
				
				//	Carico il mapper della tabella delle categorie
				TableMapper categor = mapper_sync.getTable_fromDesc("CATEGORIE_CLIENTI");
				
				//	Ottimizzo la query
				String query = "";
				String line ="";
				while((line = in.readLine()) != null)
					query = query.concat(line);
				
				//	Rimuovo gli eventuali ritorni a capo che potrebbero
				//	provocare errori in fasi esecuzioni sul DB
				query = query.replace("\n", " ");
					
				//	Preparo l'esecuzione della query e la lettura del risultato
				Statement stmt = DbAccess.getConnection().createStatement();
				ResultSet result;
				result = stmt.executeQuery(query);
				
				//	Analizzo il resultSet ed estrapolo le informazioni di
				//	ho bisono e le inserisco nella struttura
				while(result.next())
				{
					String cod = result.getString(categor.getColumnName("ID_AZIENDA"));
					Integer cod_cat = new Integer(result.getInt(categor.getColumnName("ID")));
					String descrizione = result.getString(categor.getColumnName("DESCRIZIONE"));
					
					//	Creo un'azienda e la aggiunga alla lista delle zone
					Categoria nuova = new Categoria(cod, cod_cat, descrizione);
					cat_s.add_category(nuova);
				}
	
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		} else {
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
				FileReader reader = new FileReader("QUERY_DB_Sync/Zone.sql");
				BufferedReader in = new BufferedReader(reader);
				
				//	Carico il mapper della tabella delle zone dal mapper del DB
				TableMapper locations = mapper_sync.getTable_fromDesc("ZONE");
				
				//	Ottimizzo la query
				String query = "";
				String line = "";
				while ((line = in.readLine()) != null)
					query = query.concat(line);
				
				//	Rimuovo gli eventuali ritorni a capo che potrebbero
				//	provocare errori in fasi esecuzioni sul DB
				query = query.replace("\n", " ");
					
				//	Preparo l'esecuzione della query e la lettura del risultato
				Statement stmt = DbAccess.getConnection().createStatement();
				ResultSet result;
				result = stmt.executeQuery(query);
				
				//	Analizzo il resultSet ed estrapolo le informazioni di
				//	ho bisono e le inserisco nella struttura
				while(result.next())
				{
					String cod = result.getString(locations.getColumnName("ID_AZIENDA"));
					Integer cod_zona = new Integer(result.getInt(locations.getColumnName("ID")));
					String descrizione = result.getString(locations.getColumnName("DESCRIZIONE"));
					
					//	Creo un'azienda e la aggiunga alla lista delle zone
					Zona nuova = new Zona(cod_zona, cod, descrizione);
					zone.add_Zona(nuova);
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		} else {
			zone = null;
			throw new DB_Not_Found_Exception();
		}	
	}

}
