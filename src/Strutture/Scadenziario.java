package Strutture;
/**
 * Classe per la gestione di piu' scadenze
 */

import java.util.HashSet;
import Entity.Scadenza;

/**
 * @author Alessandro Basile
 * @version 1.0
 */

public class Scadenziario
{
	/*
	 * Struttura che mi permette di memorizzare tutte le scadenze
	 * Viene usato l'hashset xke non e' importante l'ordine in cui
	 * vengono considerate ma deve essere valutata la presenza o no
	 * di una scadenza nella scadenziario
	 */
	private HashSet<Scadenza> scadenze;
	
	public Scadenziario()
	{
		scadenze = new HashSet<Scadenza>();
	}
	
	/**
	 * Controlla se una scadenza e' presente nello scadenziario
	 * @param s - Scadenza
	 * @return true- false
	 */
	public boolean contains(Scadenza s)
	{
		boolean trovato = false;
		for(Scadenza s1 : scadenze)
		{
			if(s1.equals(s))
			{
				trovato = true;
				break;
			}
		} return trovato;
	}
	
	/**
	 * Aggiunge una scadenza allo scadenziario
	 * @param s - Scadenza
	 */
	public void addScadenza(Scadenza s)
	{
		if(!this.contains(s))
			scadenze.add(s);
	}
	
	/**
	 * Numero di scadenze presenti nello scadenziario
	 * @return int 
	 */
	public int num_scadenze()
	{
		return scadenze.size();
	}
	
	/**
	 * Controlla se due scadenziari sono uguali
	 * @param s1
	 * @return
	 */
	public boolean equals(Scadenziario s1)
	{
		boolean uguali = true;
		if(this.scadenze.size() == s1.num_scadenze())
		{
			for(Scadenza s : scadenze)
			{
				if(!s1.contains(s))
				{
					uguali = false;
					break;
				}
			}
		} else uguali = false;
		return uguali;
	}
	
}
