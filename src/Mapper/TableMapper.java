package Mapper;

import java.util.LinkedList;
import XML_in_out.Tag;
import XML_in_out.XML_Interface;

/**
 * @author Alessandro Basile
 *
 */
public class TableMapper
{
	private LinkedList<Tag> valori;
	private LinkedList<String> desc_valori; 
	
	public TableMapper(String nome)
	{
		//	Carico la lista dei tag che contengono i nome dei
		//	tag da ricercare nel file di mapper
		LinkedList<String> tmp = new LinkedList<String>();
		tmp.add("TAG");
		
		LinkedList<Tag> meta_inf = new LinkedList<Tag>();
		meta_inf = XML_Interface.readFile_XML("CONFIG/META_TABLE_MAPPER.xml",
											  tmp,
											  nome);
		
		//	Costruisco la nuova lista con i tag da ricercare nel
		//	file di mapper del DB
		tmp = new LinkedList<String>();
		for(Tag tg : meta_inf)
			tmp.add(tg.getContenuto());
		
		//	Salvo i valori trovati nella lista di tag della tabella
		valori = XML_Interface.readFile_XML("CONFIG/TABLE_MAPPER.xml",
											tmp,
											nome);
		//	Salvo i nomi dei tag che descrivono i valori presenti
		//	nella lista della tabella
		desc_valori = tmp;
	}
	
	/**
	 * Restiuisce il nome della tabella che si e' mappata
	 * @return String - null
	 */
	public String getTable_Name()
	{
		return XML_Interface.readValue_ForTagName(this.valori, "NOME_TAB");
	}
	
	/**
	 * Restiuisce il nome di una colonna della tabella che si e'
	 * mappata, cosiderata la descrizione assegnata a quella colonna
	 * @param meta_desc - meta descrizione della colonna
	 * @return String - null
	 */
	public String getColumnName(String meta_desc)
	{
		if(desc_valori.contains(meta_desc))
			return XML_Interface.readValue_ForTagName(this.valori, meta_desc);
		return null;
	}
	
	/**
	 * Restituisce tutti i valori che descrivono tutte le colonne
	 * della tabella mappata
	 * @return Linkedlist<String>
	 */
	public LinkedList<String> getMeta_Inf()
	{
		LinkedList<String> meta_inf = new LinkedList<String>();
		//	Prende tutti i valori tranna il nome della tabella che
		//	viene gestito attraverso un metodo specifico
		for(int index = 0; index < desc_valori.size(); index ++)
			if(!desc_valori.get(index).equals("NOME_TAB"))
				meta_inf.add(desc_valori.get(index));
		return meta_inf;
	}
	
	/**
	 * Restiuisce una stringa descrittiva delle colonne ordinate presenti
	 * nel mapper per una determinata tabella, separati da una virgola:
	 * es: values = "'Colonna1', 'Colonna2', 'Colonna3'"; 
	 * @return String
	 */
	public String describe_Columns()
	{
		int index;
		String update = "";
		for(index = 0; index < valori.size() -1; index++)
			if(!valori.get(index).getNomeTag().equalsIgnoreCase("NOME_TAB"))
				update += valori.get(index).getContenuto() + ", ";
		update += valori.get(index).getContenuto();
		return update;
	}
}
