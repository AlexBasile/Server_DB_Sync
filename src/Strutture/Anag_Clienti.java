package Strutture;
/**
 * Classe per la gestione dell'Anagrafica Clienti
 * presente nel DB
 */

import java.util.LinkedList;

import Entity.Cliente;

/**
 * @author Alessandro Basile
 *
 */
public class Anag_Clienti
{
	/* Struttura per il salvataggio dei clienti */
	LinkedList<Cliente> anagrafica;
	
	//	COSTRUTTORE
	public Anag_Clienti()
	{
		anagrafica = new LinkedList<Cliente>();
	}
	
	/**
	 * Metodo che controlla se un cliente e' presenta
	 * in anagrafica in memoria
	 * @param Cliente c2
	 * @return true - false
	 */
	public boolean isIn_Anagrafica(Cliente c2)
	{
		for(Cliente c1 : anagrafica)
			if(c1.equals(c2))
				return true;
		return false;
	}
	
	/**
	 * Metodo che aggiunge un cliente all'anagrafica
	 * @param Cliente c2
	 */
	public void add_Cliente(Cliente c2)
	{
		if(!this.isIn_Anagrafica(c2))
			anagrafica.add(c2);
	}
	
	/**
	 * Metodo per ottenere il numero di clienti in anagrafica 
	 * @return int
	 */
	public int num_Clienti()
	{
		return this.anagrafica.size();
	}
	
	/**
	 * Metodo per ottenere un cliente presente ad un dato
	 * indice dell'anagrafica
	 * @param int index
	 * @return Cliente c1
	 */
	public Cliente getCliente_fromIndex(int index)
	{
		if(index >= 0 && index < this.num_Clienti())
			return this.anagrafica.get(index);
		else return null;
	}
	
	/**
	 * Metodo per controllare che due anagrafiche siano uguali
	 * @param Anag_Clienti an2
	 * @return true - false
	 */
	public boolean equals(Anag_Clienti an2)
	{
		boolean uguali = true;
		//Controlla prima la dimensione delle anagrafiche
		if(this.anagrafica.size() == an2.num_Clienti())
		{
			for(Cliente c1 : anagrafica)
			{
				if(!an2.isIn_Anagrafica(c1))
				{
					uguali = false;
					break;
				}
			}
		} else uguali = false;
		return uguali;
	}
}
