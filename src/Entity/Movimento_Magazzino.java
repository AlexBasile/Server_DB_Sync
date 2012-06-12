package Entity;

import java.sql.Date;

/**
 * Classe per la gestione dell'entita' movimento
 * di magazzino
 */


/**
 * @author Alessandro Basile
 * @version 1.0
 */
public class Movimento_Magazzino
{
	private String cod_azienda;
	private String cod_prodotto;
	private String cod_cliente;
	private String cod_agente;
	private Double quantita;
	private Date data_doc;
	private Double importo;
	private Double sconto;
	
	//	COSTRUTTURORI
	
	public Movimento_Magazzino()
	{
		
	}
	
	public Movimento_Magazzino(final String cod_az,
							   final String cod_pr,
							   final String cod_cl,
							   final String cod_ag,
							   final Double qnt,
							   final Date data_doc,
							   final Double importo,
							   final Double sconto)
	{
		this.setCod_azienda(cod_az);
		this.setCod_prodotto(cod_pr);
		this.setCod_cliente(cod_cl);
		this.setCod_agente(cod_ag);
		this.setQuantita(qnt);
		this.setData_doc(data_doc);
		this.setImporto(importo);
		this.setSconto(sconto);
	}
	
	//	METODI USATI PER LA MODIFICA
	
	private static boolean check_Equals(Object o1, Object o2)
	{
		if(o1 != null && o2 != null)
			return o1.equals(o2);
		else if(o1 == null && o2 == null)
			return true;
		else return false;
	}
	
	/**
	 * Confronta due movimenti di magazzino
	 * @param mm2 - movimento di magazzino
	 * @return
	 */
	public boolean equals(Movimento_Magazzino mm2)
	{
		return (check_Equals(this.cod_azienda, mm2.getCod_azienda()) &&
				check_Equals(this.cod_prodotto, mm2.getCod_prodotto()) &&
				check_Equals(this.cod_cliente, mm2.getCod_cliente()) &&
				check_Equals(this.cod_agente, mm2.getCod_agente()) &&
				check_Equals(this.quantita, mm2.getQuantita()) &&
				check_Equals(this.data_doc, mm2.getData_doc()) &&
				check_Equals(this.importo, mm2.getImporto()) &&
				check_Equals(this.sconto, mm2.getSconto()));
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
	 * @return the cod_prodotto
	 */
	public String getCod_prodotto() {
		return cod_prodotto;
	}

	/**
	 * @param cod_prodotto the cod_prodotto to set
	 */
	public void setCod_prodotto(String cod_prodotto) {
		this.cod_prodotto = cod_prodotto;
	}

	/**
	 * @return the cod_cliente
	 */
	public String getCod_cliente() {
		return cod_cliente;
	}

	/**
	 * @param cod_cliente the cod_cliente to set
	 */
	public void setCod_cliente(String cod_cliente) {
		this.cod_cliente = cod_cliente;
	}

	/**
	 * @return the cod_agente
	 */
	public String getCod_agente() {
		return cod_agente;
	}

	/**
	 * @param cod_agente the cod_agente to set
	 */
	public void setCod_agente(String cod_agente) {
		this.cod_agente = cod_agente;
	}

	/**
	 * @return the quantita
	 */
	public Double getQuantita() {
		return quantita;
	}

	/**
	 * @param qnt the quantita to set
	 */
	public void setQuantita(Double qnt) {
		this.quantita = qnt;
	}

	/**
	 * @return the data_doc
	 */
	public Date getData_doc() {
		return data_doc;
	}

	/**
	 * @param data_doc the data_doc to set
	 */
	public void setData_doc(Date data_doc) {
		this.data_doc = data_doc;
	}

	/**
	 * @return the importo
	 */
	public Double getImporto() {
		return importo;
	}

	/**
	 * @param importo the importo to set
	 */
	public void setImporto(Double importo) {
		this.importo = importo;
	}

	/**
	 * @return the sconto
	 */
	public Double getSconto() {
		return sconto;
	}

	/**
	 * @param sconto the sconto to set
	 */
	public void setSconto(Double sconto) {
		this.sconto = sconto;
	}
}
