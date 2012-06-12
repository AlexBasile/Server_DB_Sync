import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import Strutture.Anag_Clienti;
import Strutture.Aziende;
import Strutture.Categorie_Statistiche;
import Strutture.Raggruppamenti;
import Strutture.Zone;
import XML_in_out.*;
import DB_Connection.*;
import Entity.Azienda;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		try {
			
			/*	LETTUTA E SCRITTURA AZEINDE E RAGGRUPPAMENTI; */
			DbAccess.initConnection("mexal_telecom");
			transazioni_mexal tr_M = new transazioni_mexal("mexal_telecom");
			
			Aziende aziende = new Aziende();
			Raggruppamenti raggruppamenti = new Raggruppamenti();
			tr_M.read_Aziende(aziende, raggruppamenti);
			
			for(int i = 0; i < aziende.num_aziende(); i++)
			{
				System.out.println(aziende.getAzienda_fromIndex(i).toString());
			}
			
			for(String key : raggruppamenti.getCodici_Raggruppamenti())
			{
				System.out.print("Chiave: "+ key +" - valori: ");
				for(String cod : raggruppamenti.getAziende_fromGroup(key))
					System.out.print(cod + " ");
				System.out.print("\n");
			}
			
			Zone zo1 = new Zone();
			tr_M.read_Zone(zo1);
			
			Categorie_Statistiche cat = new Categorie_Statistiche();
			tr_M.read_Categorie(cat);
			
			Anag_Clienti anagrafica = new Anag_Clienti();
			tr_M.read_Anag_Clienti(anagrafica);
			
			System.out.println(anagrafica.num_Clienti());
			
			DbAccess.closeConnection();
			
			
			//	CREO LA CONNESSIONE AL DB LOCALE
			DbAccess.initConnection("localhost");
			Transazioni_DBSync update = new Transazioni_DBSync("localhost");
			
			//SCRIVO NEL DB I DATI
			update.write_Aziende_Raggruppamenti(aziende, raggruppamenti);
			update.write_Zone(zo1);
			update.write_Categorie(cat);
			update.write_Anag_Clienti(anagrafica);
			
			
			//LEGGO I DATI SCRITTI DAL DB
			Aziende az_test = new Aziende();
			Raggruppamenti ragg_test = new Raggruppamenti();
			Zone zone_test = new Zone();
			
			
			//		AZIENDE ---- RAGGRUPPAMENTI
			System.out.println("\n\n\t\tAZIENDE\n");
			update.read_Aziende(az_test, ragg_test);
			/*
			for(int i = 0; i < az_test.num_aziende(); i++)
			{
				System.out.println(az_test.getAzienda_fromIndex(i).toString());
			}
			System.out.println("\n\n\t\tRAGGRUPPAMENTI\n");
			for(String key : ragg_test.getCodici_Raggruppamenti())
			{
				System.out.print("Chiave: "+ key +" - valori: ");
				for(String cod : ragg_test.getAziende_fromGroup(key))
					System.out.print(cod + " ");
				System.out.print("\n");
			}
			*/
			//		ZONE
			System.out.println("\n\n\t\tZONE\n");
			update.read_Zone(zone_test);
			/*
			for(int i = 0; i < zone_test.num_Zone(); i++)
				System.out.println(zone_test.getZona_fromInt(i).toString());
			*/
			//		CATEGORIE
			Categorie_Statistiche cat_test = new Categorie_Statistiche();
			System.out.println("\n\n\t\tCATEGORIE\n");
			update.read_Categorie(cat_test);
			/*
			for(int i = 0; i < cat_test.num_categeries(); i++)
				System.out.println(cat_test.getCategory_fromIndex(i).toString());

			*/
			
			Anag_Clienti anag_test = new Anag_Clienti();
			System.out.println("\n\n\t\tCLIENTI\n");
			update.read_Anag_Clienti(anag_test);
			
			
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		
		
		DbAccess.closeConnection();
	}

}
