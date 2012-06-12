package Mapper;
/**
 * Classe per la gestione del mapper dell'intero DB
 */

import java.util.HashMap;
import java.util.LinkedList;

import XML_in_out.Tag;
import XML_in_out.XML_Interface;

/**
 * @author Alessandro Basile
 * @version 1.0
 */
public class DB_Mapper
{
	//	Lista delle tabelle presenti nel database
	private LinkedList<Tag> tables;
	
	//	Lista del nome dei tag usati per identificare le tabelle
	private HashMap<String, String> desc_tables;
	
	//	HashMap usato per gestire il mapper, assegnando ad
	//	un nome tabella una tableMapper
	private HashMap<String, TableMapper> mapper;
	
	public DB_Mapper(String name)
	{
		//	Carico la lista dei tag che contengono i nome dei
		//	tag da ricercare nel file di mapper
		LinkedList<String> tmp = new LinkedList<String>();
		tmp.add("TAG");
		
		LinkedList<Tag> meta_inf = new LinkedList<Tag>();
		meta_inf = XML_Interface.readFile_XML("CONFIG/DB_MAPPER.xml",
											  tmp,
											  name);
		
		//	Costruisco la nuova lista con i tag da ricercare nel
		//	file di mapper del DB
		tmp = new LinkedList<String>();
		for(Tag tg : meta_inf)
			tmp.add(tg.getContenuto());
			
		//	Salvo i valori trovati nella lista di tag delle tabelle
		tables = XML_Interface.readFile_XML("CONFIG/DB_MAPPER.xml",
											tmp,
											name);
		//	Salvo i nomi dei tag che descrivono le tabelle presenti
		//	nella lista del DB
		desc_tables = new HashMap<String, String>();
		
		//	Struttura che gestisce la corrispondenza del mapper
		//	delle tabelle del DB
		mapper = new HashMap<String, TableMapper>();
		
		//	Creo un TableMapper per ogni tabella caricata dal
		//	file del mapper del DB
		for(Tag tg : tables)
		{
			desc_tables.put(tg.getNomeTag(), tg.getContenuto());
			this.mapper.put(tg.getNomeTag(), new TableMapper(tg.getContenuto()));
		}
	}
	
	public TableMapper getTable_fromDesc(String desc)
	{
		if(desc_tables.containsKey(desc))
			return mapper.get(desc);
		return null;
	}
	
	public String getDesc_FromName(String name)
	{
		for(String s1: desc_tables.keySet())
			if(desc_tables.get(s1).equalsIgnoreCase(name))
				return s1;
		return null;
	}
	
	public void printMeta_inf()
	{
		for(String s1 : desc_tables.keySet())
			System.out.println(s1);
	}
	

}
