
package Entity;

/**
 * @author Alessandro Basile
 * 
 */

/**
 * Classe per la gestione dell'entita' riga
 * formazione prezzo da associare ad prodotto
 */

public class Riga_Form_Prezzo
{
	/*
	 * Array di prezzi
	 * - da 0 - 4 prezzi da dettaglio
	 * - da 5 - 9 prezzi da ingrosso  
	 */
	private Double[] listini;
	private String cod_Azienda;
	private String cod_Articolo;
	
	/*	
	 * 	Costruttori possibili per inizializzare
	 * 	e popolare la struttura;
	 */
	public Riga_Form_Prezzo()
	{
		listini = new Double[9];
	}
	
	public Riga_Form_Prezzo(final String cod_AZ,
							final String cod_Pr,
							final Double[] list)
	{
		//	Assegno l'array dei prezzi	
		if(list.length == 9)
			this.listini = list;
		//Assegno i codici dell'azienda e del prodotto
		this.setCod_Articolo(cod_Pr);
		this.setCod_Azienda(cod_AZ);
	}
	
	public Riga_Form_Prezzo(final String cod_AZ,
							final String cod_Pr,
							final Double list1,
							final Double list2,
							final Double list3,
							final Double list4,
							final Double list5,
							final Double list6,
							final Double list7,
							final Double list8,
							final Double list9)
	{
		//	Assegno i codici dell'azienda e del prodotto
		this.setCod_Articolo(cod_Pr);
		this.setCod_Azienda(cod_AZ);

		//	Assegno i prezzi all'array dei prezzi
		listini = new Double[9];
		listini[0] = list1;
		listini[1] = list2;
		listini[2] = list3;
		listini[3] = list4;
		listini[4] = list5;
		listini[5] = list6;
		listini[6] = list7;
		listini[7] = list8;
		listini[8] = list9;
	}
	
	private static boolean check_Equals(Object o1, Object o2)
	{
		if(o1 != null && o2 != null)
			return o1.equals(o2);
		else if(o1 == null && o2 == null)
			return true;
		else return false;
	}
	
	//	Controllo se due rihe di formazione prezzo siano uguali
	public boolean equals(Riga_Form_Prezzo r2)
	{
		//	controllo i codici articolo e azienda di riferimento
		boolean uguale = true;
		if(check_Equals(cod_Articolo, r2.getCod_Articolo()) &&
				check_Equals(this.cod_Azienda, r2.getCod_Azienda()))
		{
			//	controllo che tutti i prezzi coincidano
			for(int i = 0; i < listini.length; i++)
			{
				if(!check_Equals(this.listini[i].doubleValue(), r2.getListino(i).doubleValue()))
				{
					uguale = false;
					break;
				}
			}
		} else uguale = false;
		
		return uguale;
	}
	
	public void setListino(int index, double valore)
	{
		if(index < listini.length && index > 0)
			this.listini[index-1] = valore;
	}
	
	public Double getListino(int index)
	{
		if(index < listini.length && index > 0)
			return this.listini[index-1];
		else return Double.NaN;
	}
	
	/*
	 * 	Metodo che restiuiscono rispettivamente i prezzi di ingrosso
	 *	e di dettaglio per un particolare prodotto
	 */
	public Double[] get_Dettaglio()
	{
		Double[] dettaglio = new Double[5];
		for(int i = 0; i < dettaglio.length; i++)
			dettaglio[i] = listini[i];
		return dettaglio;
	}
	
	public Double[] get_Ingrosso()
	{
		Double[] ingrosso = new Double[4];
		for(int i = 0; i < ingrosso.length; i++)
			ingrosso[i] = listini[i+5];
		return ingrosso;
	}

	/**
	 * @return the cod_Azienda
	 */
	public String getCod_Azienda() {
		return cod_Azienda;
	}

	/**
	 * @param cod_Azienda the cod_Azienda to set
	 */
	public void setCod_Azienda(String cod_Azienda) {
		this.cod_Azienda = cod_Azienda;
	}

	/**
	 * @return the cod_Articolo
	 */
	public String getCod_Articolo() {
		return cod_Articolo;
	}

	/**
	 * @param cod_Articolo the cod_Articolo to set
	 */
	public void setCod_Articolo(String cod_Articolo) {
		this.cod_Articolo = cod_Articolo;
	}

}
