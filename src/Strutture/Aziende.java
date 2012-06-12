package Strutture;
/**
 * Classe per la gestione delle aziende
 */

import java.util.LinkedList;
import Entity.Azienda;

/**
 * @author Alessandro Basile
 * @version 1.0
 */
public class Aziende
{
	/**
	 * Struttura di tipo lista per la gestione delle aziende
	 */
	private LinkedList<Azienda> factories;
	
	/**
	 * Costruttore delle aziende
	 */
	public Aziende()
	{
		factories = new LinkedList<Azienda>();
	}
	
	/**
	 * Restituisce una azienda ad un determinato indice della lista
	 * @param index - int
	 * @return Azienda - null
	 */
	public Azienda getAzienda_fromIndex(int index)
	{
		if(index < factories.size())
			return this.factories.get(index);
		else return null;
	}
	
	/**
	 * Aggiunge una nuova azienda alla lista delle aziende se non e'
	 * gia' presente
	 * @param Azienda az
	 */
	public void addAzienda(Azienda az)
	{
		if(!isAzienda_in(az))
		{
			this.factories.add(az);
		}
	}
	
	/**
	 * Restiusce il numero delle aziende presenti nella lista
	 * @return num - int
	 */
	public int num_aziende()
	{
		return this.factories.size();
	}
	
	/**
	 * Controlla se un azienda e' presente nella lista delle aziende
	 * @param Azienda - Az
	 * @return True - False
	 */
	public boolean isAzienda_in(Azienda az)
	{
		for(Azienda az1 : factories)
			if(az1.equals(az))
				return true;
		return false;
	}
	
	/**
	 * Controlla che due liste di azienda siano uguali
	 * @param Aziende az1
	 * @return true - false
	 */
	public boolean equals(Aziende az1)
	{
		boolean uguali = true;
		
		//	Controlla che la lunghezza delle due liste sia uguale
		if(this.factories.size() == az1.num_aziende())
		{
			//	Controlla che ogni azienda presente nella prima
			//	lista sia presente anche nella seconda;
			for(Azienda az : factories)
				if(!az1.isAzienda_in(az))
				{
					uguali = false;
					break;
				}
		}else uguali = false;
		return uguali;
	}
}
