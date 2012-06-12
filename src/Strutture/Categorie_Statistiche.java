package Strutture;
/**
 * Classe per le gestione di tutte le categorie
 * presenti nel DB
 */

import java.util.LinkedList;
import Entity.Categoria;

/**
 * @author Alessandro Basile
 * @version 1.0
 */

public class Categorie_Statistiche
{
	LinkedList<Categoria> cats;
	
	public Categorie_Statistiche()
	{
		cats = new LinkedList<Categoria>();
	}
	
	public boolean isIn_Categories(Categoria c2)
	{
		for(Categoria c1 : cats)
			if(c1.equals(c2))
				return true;
		return false;
	}
	
	public void add_category(Categoria c2)
	{
		if(!this.isIn_Categories(c2))
			cats.add(c2);
	}
	
	public int num_categeries()
	{
		return this.cats.size();
	}
	
	public Categoria getCategory_fromIndex(int index)
	{
		if(index >= 0 && index < this.cats.size())
			return this.cats.get(index);
		else return null;
	}
	
	public boolean equals(Categorie_Statistiche cs2)
	{
		boolean uguali = true;
		//Controlla che le due stutture abbiano stessa lunghezza
		if(this.cats.size() == cs2.num_categeries())
		{
			for(Categoria ca1 : cats)
			{
				if(!cs2.isIn_Categories(ca1))
				{
					uguali = false;
					break;
				}
			}
		}else uguali = false;
		return uguali;
	}
}
