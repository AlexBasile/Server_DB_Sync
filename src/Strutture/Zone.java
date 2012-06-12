package Strutture;
/**
 * Classe per la gestione delle Zone
 */

import java.util.LinkedList;
import Entity.Zona;

/**
 * @author Alessandro Basile
 * @version 1.0
 */
public class Zone
{
	LinkedList<Zona> locations;
	
	public Zone()
	{
		locations = new LinkedList<Zona>();
	}
	
	public boolean isIn_Zone(Zona z1)
	{
		for(Zona z : locations)
			if(z.equals(z1))
				return true;
		return false;
	}
	
	public void add_Zona(Zona z1)
	{
		if(!isIn_Zone(z1))
			this.locations.add(z1);
	}
	
	public int num_Zone()
	{
		return this.locations.size();
	}
	
	public Zona getZona_fromInt(int index)
	{
		if(index < locations.size() && index >= 0)
			return locations.get(index);
		else return null;
	}
	
	public boolean equals(Zone zo2)
	{
		boolean uguali = true;
		
		//	Controlla che la lunghezza delle due liste sia uguale
		if(this.locations.size() == zo2.num_Zone())
		{
			//	Controlla che ogni azienda presente nella prima
			//	lista sia presente anche nella seconda;
			for(Zona zo : locations)
			{
				if(!zo2.isIn_Zone(zo))
				{
					uguali = false;
					break;
				}
			}
		}else uguali = false;
		return uguali;
	}
}
