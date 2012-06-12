package Strutture;
/**
 * Classe per la gestione dei Raggruppamenti delle aziende
 */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author Alessandro Basile
 * @version 1.0
 */
public class Raggruppamenti
{
	/**
	 * Struttura per memorizzare il codice di raggruppamento e tutte
	 * le azienda che fanno riferimento a quel codice
	 */
	private HashMap<String, LinkedList<String>> groups;
	
	public Raggruppamenti()
	{
		//	inizializzo la strttura
		groups = new HashMap<String, LinkedList<String>>();
	}
	
	/**
	 * Metodo per avere tutti codici raggruppamenti presenti
	 * @return Set<String> contenete le chiavi - cod_aziende
	 */
	public Set<String> getCodici_Raggruppamenti()
	{
		return groups.keySet();
	}
	
	public boolean contiene_raggr(String key)
	{
		return (this.groups.containsKey(key));
	}
	
	/**
	 * Metodo per avere tutti i codici aziende connessi ad un
	 * codice di raggruppamento
	 * @param group - codice raggruppamento considerato
	 * @return LinkedList<String>
	 */
	public LinkedList<String> getAziende_fromGroup(String group)
	{
		return groups.get(group);
	}
	
	/**
	 * Metodo per aggiungere un codice azieda alla lista di codici
	 * azienda assegnati ad un particolare cod_raggruppamento
	 * @param cod_aziena
	 * @param cod_raggruppamento
	 */
	public void addAzienda_forGroup(String cod_a, String cod_r)
	{
		//	controllo se tra le chiavi e' gia' presente il codice
		//	raggruppamento considerato
		if(groups.containsKey(cod_r))
		{
			//	controllo che la lista assegnata non contenga gia'
			//	il codice azienda considerato
			if(!groups.get(cod_r).contains(cod_a))
				//	Se non e' presente lo aggiungo alla lista
				groups.get(cod_r).add(cod_a);
		}
		//	Se non e' contenuto allora devo creare una lista
		//	aggiungere il valore ad assegnarla a quel codice
		else {
			LinkedList<String> nuova = new LinkedList<String>();
			nuova.add(cod_a);
			groups.put(cod_r, nuova);
		}
	}
	
	/**
	 * Controllo se due strutture raggruppamento sono uguali
	 * @param r1 - Raggruppamento
	 * @return True - False
	 */
	public boolean equals(Raggruppamenti r1)
	{
		boolean uguali = true;
		boolean aziende = false;
		
		//	Considero le chiavi delle due strutture di raggruppamento
		Set<String> r1_key = r1.getCodici_Raggruppamenti();
		Set<String> local_key = groups.keySet();
		
		//	Controllo che la lunghezza degli insieme di chiavi sia uguale
		if(r1_key.size() == local_key.size())
		{
			//	Controllo membro a membro che ogni chiave sia presente
			//	nella lista di chiavi dell'altra struttura
			for(String s1 : local_key)
			{
				//	Controlla se tra le chiavi di r1 e' presente s1
				if(!r1_key.contains(s1))
				{
					//	Se non e' presente, non sono uguali si ferma
					//	il ciclo e restituisco false
					uguali = false;
					break;
				}
				//	Altrimenti confronto tutte le stringhe delle
				//	lista dei codici_azienda per quella chiave 
				else {
					for(String s2 : groups.get(s1))
					{
						for(String s3 : r1.getAziende_fromGroup(s1))
						{
							//	quando trovo la stringa uguale imposto il
							//	flag di controllo a true
							if(s2.equals(s3))
							{
								aziende = true;
								break;
							}
						}
						//	Se le il flag e' rimasto false vuol dire che sono
						//	diverse allora esco dal ciclo
						if(!aziende)
						{
							uguali = false;
							break;
						} else aziende = false;
					}
				}
			}
		}
		//	Se la grandezza degli insiemi delle chiavi sono diversi 
		//	sono sicuramente diverse le strutture;
		else uguali = false;
		return uguali;
	}
}
