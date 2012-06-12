package Entity;
/**
 * Classe per definizione del tipo Categoria
 */

/**
 * @author Alessandro Basile
 * @version 1.0
 */
public class Categoria
{
	private String cod_azienda;
	private Integer cod_categoria;
	private String descrizione;
	
	public Categoria()
	{
		
	}
	
	public Categoria(final String cod_az,
					 final Integer cod_cat,
					 final String descriz)
	{
		this.setCod_categoria(cod_cat);
		this.setCod_azienda(cod_az);
		this.setDescrizione(descriz);
	}
	
	private static boolean check_Equals(Object o1, Object o2)
	{
		if(o1 != null && o2 != null)
			return o1.equals(o2);
		else if(o1 == null && o2 == null)
			return true;
		else return false;
	}
	
	/**
	 * Metodo per verifcare l'ugualianza tra due categoria
	 * @param Categoria c2
	 * @return true - false
	 */
	public boolean equals(Categoria c2)
	{
		return (check_Equals(this.cod_azienda, c2.getCod_azienda()) &&
				check_Equals(this.cod_categoria, c2.getCod_categoria()) &&
				check_Equals(this.descrizione, c2.getDescrizione()));
	}
	
	public String toString()
	{
		return cod_azienda + " " + cod_categoria + " " + descrizione;
	}
	
	public String values()
	{
		return "(\""+ this.cod_azienda + "\", " + this.cod_categoria + ", \"" + this.descrizione + "\")";
	}

	/**
	 * @return the cod_azienda
	 */
	public String getCod_azienda() {
		return cod_azienda;
	}

	/**
	 * @param cod_azienda the cod_azienda to set
	 */
	public void setCod_azienda(String cod_azienda) {
		this.cod_azienda = cod_azienda;
	}

	/**
	 * @return the cod_categoria
	 */
	public Integer getCod_categoria() {
		return cod_categoria;
	}

	/**
	 * @param cod_categoria the cod_categoria to set
	 */
	public void setCod_categoria(Integer cod_categoria) {
		this.cod_categoria = cod_categoria;
	}

	/**
	 * @return the descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * @param descrizione the descrizione to set
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}
