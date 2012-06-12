package Entity;

import java.sql.Date;

/**
 * Classe per la gestione delle scadenze
 * delle fatture
 */

/**
 * @author Alessandro Basile
 * @version 1.0
 */
public class Scadenza
{
	private String cod_azienda;
	private String cod_agente_1;
	private String cod_agente_2;
	private String cod_cliente;
	private String sig;
	private Integer sdoc;
	private Integer numDoc;
	private Date dataDoc;
	private String ts;
	private Date dataSca;
	private Double impsca;
	private Double totdoc;
	
	//	COSTRUTTORI
	
	public Scadenza()
	{
		
	}
	
	public Scadenza(final String cod_az,
					final String cod_a1,
					final String cod_a2,
					final String cod_cl,
					final String sig,
					final Integer sdoc,
					final Integer numDoc,
					final Date dataDoc,
					final String ts,
					final Date dataSca,
					final Double impsca,
					final Double totdoc)
	{
		this.setCod_azienda(cod_az);
		this.setCod_agente_1(cod_a1);
		this.setCod_agente_2(cod_a2);
		this.setCod_cliente(cod_cl);
		this.setSig(sig);
		this.setSdoc(sdoc);
		this.setNumDoc(numDoc);
		this.setDataDoc(dataDoc);
		this.setTs(ts);
		this.setDataSca(dataSca);
		this.setImpsca(impsca);
		this.setTotdoc(totdoc);
	}
	
	//	METODO DI UGUALIANZA
	
	private static boolean check_Equals(Object o1, Object o2)
	{
		if(o1 != null && o2 != null)
			return o1.equals(o2);
		else if(o1 == null && o2 == null)
			return true;
		else return false;
	}
	
	public boolean equals(Scadenza s1)
	{
		return (check_Equals(this.cod_agente_1, s1.getCod_agente_1()) &&
				check_Equals(this.cod_agente_2, s1.getCod_agente_2()) &&
				check_Equals(this.cod_azienda, s1.getCod_azienda()) &&
				check_Equals(this.cod_cliente, s1.getCod_cliente()) &&
				check_Equals(this.sig, s1.getSig()) &&
				check_Equals(this.sdoc, s1.getSdoc()) &&
				check_Equals(this.numDoc, s1.getNumDoc()) &&
				check_Equals(this.dataDoc, s1.getDataDoc()) &&
				check_Equals(this.dataSca, s1.getDataSca()) &&
				check_Equals(this.ts, s1.getTs()) &&
				check_Equals(this.impsca, s1.getImpsca()) &&
				check_Equals(this.totdoc, s1.getTotdoc()));
	}

	
	//	METODI SETTER E GETTER

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
	 * @return the cod_agente_1
	 */
	public String getCod_agente_1() {
		return cod_agente_1;
	}

	/**
	 * @param cod_agente_1 the cod_agente_1 to set
	 */
	public void setCod_agente_1(String cod_agente_1) {
		this.cod_agente_1 = cod_agente_1;
	}

	/**
	 * @return the cod_agente_2
	 */
	public String getCod_agente_2() {
		return cod_agente_2;
	}

	/**
	 * @param cod_agente_2 the cod_agente_2 to set
	 */
	public void setCod_agente_2(String cod_agente_2) {
		this.cod_agente_2 = cod_agente_2;
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
	 * @return the sig
	 */
	public String getSig() {
		return sig;
	}

	/**
	 * @param sig the sig to set
	 */
	public void setSig(String sig) {
		this.sig = sig;
	}

	/**
	 * @return the sdoc
	 */
	public Integer getSdoc() {
		return sdoc;
	}

	/**
	 * @param sdoc the sdoc to set
	 */
	public void setSdoc(Integer sdoc) {
		this.sdoc = sdoc;
	}

	/**
	 * @return the dataDoc
	 */
	public Date getDataDoc() {
		return dataDoc;
	}

	/**
	 * @param dataDoc the dataDoc to set
	 */
	public void setDataDoc(Date dataDoc) {
		this.dataDoc = dataDoc;
	}

	/**
	 * @return the numDoc
	 */
	public Integer getNumDoc() {
		return numDoc;
	}

	/**
	 * @param numDoc the numDoc to set
	 */
	public void setNumDoc(Integer numDoc) {
		this.numDoc = numDoc;
	}

	/**
	 * @return the ts
	 */
	public String getTs() {
		return ts;
	}

	/**
	 * @param ts the ts to set
	 */
	public void setTs(String ts) {
		this.ts = ts;
	}

	/**
	 * @return the dataSca
	 */
	public Date getDataSca() {
		return dataSca;
	}

	/**
	 * @param dataSca the dataSca to set
	 */
	public void setDataSca(Date dataSca) {
		this.dataSca = dataSca;
	}

	/**
	 * @return the impsca
	 */
	public Double getImpsca() {
		return impsca;
	}

	/**
	 * @param impsca the impsca to set
	 */
	public void setImpsca(Double impsca) {
		this.impsca = impsca;
	}

	/**
	 * @return the totdoc
	 */
	public Double getTotdoc() {
		return totdoc;
	}

	/**
	 * @param totdoc the totdoc to set
	 */
	public void setTotdoc(Double totdoc) {
		this.totdoc = totdoc;
	}
}
