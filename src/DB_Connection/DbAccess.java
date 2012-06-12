package DB_Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

import XML_in_out.Tag;
import XML_in_out.XML_Interface;

public class DbAccess {
	
	private static String DRIVER_CLASS;
	private static String DBMS;
	private static String SERVER;
	private static String DB;
	private static String PORT;
	private static String USER_ID;
	private static String PASSW;
	
	private static Connection conn;
	
	//FUNZIONE PER SETTARE I PARAMENTRI DI CONNESSIONE 
	//OGNI VOLTA CHE SI ATTIVA LA CONNESSIONE
	private static void setParameters(String type)
	{
		/*imposto i tag da adare a leggere dal file di configurazione */
		LinkedList<String> tags = new LinkedList<String>();
		tags.add("DRIVER_CLASS");
		tags.add("DBMS");
		tags.add("SERVER");
		tags.add("PORT");
		tags.add("DB");
		tags.add("USER_ID");
		tags.add("PASSW");
		
		//Leggo il contenuto dei tag dal file XML
		LinkedList<Tag> parametri = XML_Interface.readFile_XML("CONFIG/connection_db.xml",
																tags,
																type);
		
		//Setto i parametri di connessione in base al contenuto dei tag
		for(Tag t : parametri)
		{
			if(t.getNomeTag().equalsIgnoreCase("DRIVER_CLASS")) {
				DRIVER_CLASS = t.getContenuto();
			}if(t.getNomeTag().equalsIgnoreCase("DBMS")) {
				DBMS = t.getContenuto();
			}if(t.getNomeTag().equalsIgnoreCase("SERVER")) {
				SERVER = t.getContenuto();
			}if(t.getNomeTag().equalsIgnoreCase("PORT")) {
				PORT = t.getContenuto();
			}if(t.getNomeTag().equalsIgnoreCase("DB")) {
				DB = t.getContenuto();
			}if(t.getNomeTag().equalsIgnoreCase("USER_ID")) {
				USER_ID = t.getContenuto();
			}if(t.getNomeTag().equalsIgnoreCase("PASSW")) {
				PASSW = t.getContenuto();
			}
		}
	}
	
	//FUNZIONE PER INIZIALIZZARE LA CONNESSIONE AL DATABASE
	public static void initConnection(String type)
	{
		//Setto i paramentri della connessione
		DbAccess.setParameters(type);
		System.out.println(DRIVER_CLASS);
		String ConnectionString =DBMS+"://" + SERVER + ":" + PORT + "/" + DB;
		System.out.println(ConnectionString);
		
		
		//Inizializzo la connessione al database;
		try{
    		Class.forName(DRIVER_CLASS).newInstance();
    		
    	}catch(Exception e){
    		System.out.println("impossibile trovate il driver:"+DRIVER_CLASS);
    	}
    	try {
    		conn = DriverManager.getConnection(ConnectionString, USER_ID,PASSW);
    	}catch(SQLException e){
    		System.out.println("Impossibile connettersi al database");
    		e.printStackTrace();
    	}
	}
	
	//RESTITUISCE L'OGGETTO CHE PERMETTE LA GESTIONE DELLA CONNESSIONE
	public static Connection getConnection(){
    	return conn;
    }
	
	//PERMETTE DI CHIUDERE LA CONNESSIONE
	public static void closeConnection(){
    	try{
    		conn.close();
    	}catch (SQLException e){
    		System.out.println("Impossibile chiudere la connessione");
    	 }
    }
	
	
	/**
	 * Metodo per controllare che la connessione esista e quale sia
	 * il database su cui sta leggendo/scrivendo 
	 * @param type - indica il tipo di connessione che si vuole verificare
	 * @return true - false
	 */
	public static boolean check_connection(String type)
	{
		boolean uguale = true;
		if(DbAccess.getConnection() != null)
		{
			/*imposto i tag da adare a leggere dal file di configurazione */
			LinkedList<String> tags = new LinkedList<String>();
			tags.add("DB");
			tags.add("USER_ID");
		
			//Leggo il contenuto dei tag dal file XML
			LinkedList<Tag> parametri = XML_Interface.readFile_XML("CONFIG/connection_db.xml",
																	tags,
																	type);
			for(Tag t : parametri)
			{
				if(t.getNomeTag().equalsIgnoreCase("DB")) {
					if(!t.getContenuto().equals(DB))
						uguale = false;
				}if(t.getNomeTag().equalsIgnoreCase("USER_ID")) {
					if(!t.getContenuto().equals(USER_ID))
						uguale = false;
				} if(!uguale) break;
			}
			
		}else uguale = false;
		return uguale;
	}

}
