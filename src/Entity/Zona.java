package Entity;

/**
 * @author Alessandro Basile
 * @version 1.0
 */
/*
 * Classe per la gestione dei Prodotti;
 */

public class Zona
{
	private Integer id_zona;
	private String cod_azienda;
	private String desc;
	
	public Zona()
	{
		
	}
	
	public Zona(Integer id_zona,
				String Cod_Azienda,
				String desc)
	{
		this.setId_zona(id_zona);
		this.setCod_azienda(Cod_Azienda);
		this.setDesc(desc);
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
	 * Metodo per gestione delle ugualianza tra zone
	 * @param z1 - Zona
	 * @return true- false
	 */
	public boolean equals(Zona z1)
	{
		return (check_Equals(this.id_zona, z1.getId_zona()) &&
				check_Equals(this.cod_azienda, z1.getCod_azienda()) &&
				check_Equals(this.desc, z1.getDesc()));
	}
	
	public String toString()
	{
		return this.cod_azienda + " " + this.id_zona + " " + this.desc; 
	}
	
	public String values()
	{
		return "("+ this.id_zona + ", \""+ this.desc + "\", \"" + this.cod_azienda + "\")";
	}

	/**
	 * @return the id_zona
	 */
	public Integer getId_zona() {
		return id_zona;
	}

	/**
	 * @param id_zona the id_zona to set
	 */
	public void setId_zona(Integer id_zona) {
		this.id_zona = id_zona;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
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
}
