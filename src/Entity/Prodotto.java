package Entity;

/**
 * @author Alessandro Basile
 * @version 1.0
 */

/*
 * Classe per la gestione dei Prodotti;
 */
public class Prodotto
{
	private String cod_Articolo;
	private String cod_Azienda;
	private String descrizione;
	private String unit_misura;
	private Double confezioni;
	private String iva;
	private String fornitore;
	private String nota1;
	private String nota2;
	
	public Prodotto()
	{
		
	}
	
	public Prodotto(String cod_Art,
					String cod_Az,
					String descr,
					String um,
					Double conf,
					String iva,
					String fornitore,
					String nota1,
					String nota2)
	{
		this.setCod_Articolo(cod_Art);
		this.setCod_Azienda(cod_Az);
		this.setDescrizione(descr);
		this.setUnit_misura(um);
		this.setConfezioni(conf);
		this.setIva(iva);
		this.setFornitore(fornitore);
		this.setNota1(nota1);
		this.setNota2(nota2);
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
	 * Metodo per la gestione dell'ugualianza tra entita prodotto
	 * @param p2 - Prodotto
	 * @return true - false
	 */
	public boolean equals(Prodotto p2)
	{
		return (check_Equals(this.cod_Articolo, p2.getCod_Articolo()) &&
				check_Equals(this.cod_Azienda, p2.getCod_Azienda()) &&
				check_Equals(this.confezioni, p2.getConfezioni()) &&
				check_Equals(this.descrizione, p2.getDescrizione()) &&
				check_Equals(this.fornitore, p2.getFornitore()) &&
				check_Equals(this.iva, p2.getIva()) &&
				check_Equals(this.nota1, p2.getNota1()) &&
				check_Equals(this.nota2, p2.getNota2()) &&
				check_Equals(this.unit_misura, p2.getUnit_misura()));
	}

	public String getCod_Articolo() {
		return cod_Articolo;
	}

	public void setCod_Articolo(String cod_Articolo) {
		this.cod_Articolo = cod_Articolo;
	}

	public String getCod_Azienda() {
		return cod_Azienda;
	}

	public void setCod_Azienda(String cod_Azienda) {
		this.cod_Azienda = cod_Azienda;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getUnit_misura() {
		return unit_misura;
	}

	public void setUnit_misura(String unit_misura) {
		this.unit_misura = unit_misura;
	}

	public Double getConfezioni() {
		return confezioni;
	}

	public void setConfezioni(Double conf) {
		this.confezioni = conf;
	}

	public String getIva() {
		return iva;
	}

	public void setIva(String iva) {
		this.iva = iva;
	}

	public String getNota1() {
		return nota1;
	}

	public void setNota1(String nota1) {
		this.nota1 = nota1;
	}

	public String getNota2() {
		return nota2;
	}

	public void setNota2(String nota2) {
		this.nota2 = nota2;
	}

	public String getFornitore() {
		return fornitore;
	}

	public void setFornitore(String fornitore) {
		this.fornitore = fornitore;
	}
	

}
