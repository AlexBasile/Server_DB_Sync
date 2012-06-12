package Entity;

/**
 * @author Alessandro Basile
 * @version 1.0
 */

/*
 * Classe per la gestione degli Agenti;
 */

public class Agente
{
	private String cod_agente;
	private String agente;
	private Integer zona;
	
	public Agente()
	{
		
	}
	
	public Agente(String cod_agente,
				  String agente,
				  Integer zona)
	{
		this.setCod_agente(cod_agente);
		this.setAgente(agente);
		this.setZona(zona);
	}
	
	private static boolean check_Equals(Object o1, Object o2)
	{
		if(o1 != null && o2 != null)
			return o1.equals(o2);
		else if(o1 == null && o2 == null)
			return true;
		else return false;
	}
	
	public boolean equals(Agente a2)
	{
		return (check_Equals(this.cod_agente, a2.getCod_agente()) &&
				check_Equals(this.agente, a2.getAgente()) &&
				check_Equals(this.zona, a2.getZona()));
	}

	public String getCod_agente() {
		return cod_agente;
	}

	public void setCod_agente(String cod_agente) {
		this.cod_agente = cod_agente;
	}

	public String getAgente() {
		return agente;
	}

	public void setAgente(String agente) {
		this.agente = agente;
	}

	public Integer getZona() {
		return zona;
	}

	public void setZona(Integer zona) {
		this.zona = zona;
	}
}
