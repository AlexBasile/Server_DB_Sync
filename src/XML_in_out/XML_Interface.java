package XML_in_out;

/**
 * @author Alessandro Basile
 * @version 1.0
 */


import java.util.LinkedList;

/*
 * 	Interfaccia che permette di leggere e scrivere i documenti XML 
 */
public class XML_Interface
{	
	/*
	 * Metodo per la lettura da XML di un determinato numero di valori per alcuni TAG
	 * Restituisce
	 * 	-	una lista di oggetti di Tipo Tag
	 *  -	null se si verificano errori
	 */
	public static LinkedList<Tag> readFile_XML(String nomeFile, LinkedList<String> tags, String attributo)
	{
		return XML_Reader.readXML_fromFile(nomeFile, tags, attributo);	
	}
	
	/*
	 * Metodo per la scrittura di un file .XML
	 *  - 	nomeFile, percorso comprensivo del file escludendo l'estensione
	 *  - 	volaori, una lista di TAG
	 */
	public static void writeFile_XML(String nomeFile, LinkedList<Tag> valori)
	{
		XML_Writer.writeXML_toFile(nomeFile, valori);
	}
	
	/*
	 * Metodo per modificare un file XML precedentemente letto
	 * restituisce una nuova lista di TAG modificati come specificato:
	 * 	-	valori, lista contenente l'XML letto;
	 *  -	old, tag a cui applicare la modifica
	 *  -	nuovo, tag contenente la modifica
	 */
	public static LinkedList<Tag> changeXML(LinkedList<Tag> valori, Tag old, Tag nuovo)
	{
		//Ciclo fino a trovare nella lista di tag il tag che voglio modificare 
		int j = 0;
		while(j < valori.size())
		{
			Tag tmp1 = valori.get(j);
			if(tmp1.equals(old))
			{
				//quando lo trovo rimuovo il tag dalla lista
				valori.remove(j);
				//aggiungo alla lista il nuovo tag nella stessa posizione del vecchio
				valori.add(j, nuovo);
				//finisco il ciclo xke non ci sono tag ripetuti
				break;
			}else j++;
		}
		
		//restituisco la lista dei tag aggiornata
		return valori;
	}
	
}
