package Strutture;
/**
 * Classe per la gestione dei movimenti di magazzino
 */

import java.util.HashSet;
import Entity.Movimento_Magazzino;

/**
 * @author Alessandro Basile
 * @versione 1.0
 */
public class Movimenti_Magazzino
{
	/*
	 * Strttura per memorizzare i movimenti di magazzino
	 * usato per valutare la presenza oppure no di un
	 * movimento nella struttura, non ho bisogno di
	 * considerare l'ordine di apparizzione
	 */
	private HashSet<Movimento_Magazzino> movimenti;
	
	public Movimenti_Magazzino()
	{
		movimenti = new HashSet<Movimento_Magazzino>();
	}
	
	/**
	 * Controlla se una scadenza e' presente nella lista movimenti
	 * @param m - Movimento di magazzino
	 * @return true- false
	 */
	public boolean contains(Movimento_Magazzino m)
	{
		boolean trovato = false;
		for(Movimento_Magazzino m1 : movimenti)
		{
			if(m1.equals(m))
			{
				trovato = true;
				break;
			}
		} return trovato;
	}
	
	/**
	 * Aggiunge un movimento ai movimenti di magazzino
	 * @param m - Movimento
	 */
	public void addMovimento(Movimento_Magazzino m)
	{
		if(!this.contains(m))
			movimenti.add(m);
	}
	
	/**
	 * Numero di movimenti presenti
	 * @return int 
	 */
	public int num_scadenze()
	{
		return movimenti.size();
	}
	
	/**
	 * Controlla se due liste di movimenti sono uguali
	 * @param m1 - lista con cui confrontare
	 * @return true - false 
	 */
	public boolean equals(Movimenti_Magazzino m1)
	{
		boolean uguali = true;
		if(this.movimenti.size() == m1.num_scadenze())
		{
			for(Movimento_Magazzino m : movimenti)
			{
				if(!m1.contains(m))
				{
					uguali = false;
					break;
				}
			}
		} else uguali = false;
		return uguali;
	}
}
