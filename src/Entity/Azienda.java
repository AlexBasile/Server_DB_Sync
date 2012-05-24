package Entity;
/**
 * @author Alessandro Basile
 * @version 1.0
 */
/*
 * Classe per la gestione delle Aziende;
 */
public class Azienda
{
	private String cod_Azienda;
	private String ragione_soc;
	private String p_IVA;
	private String descrizione;
	private String indirizzo;
	private String localita;
	private int cap;
	private String prov;
	
	public Azienda()
	{
		
	}
	
	public Azienda(String cod_Azienda,
				   String ragione_soc,
				   String p_IVA,
				   String descrizione,
				   String indirizzo,
				   String localita,
				   int cap,
				   String prov)
	{
		this.setCod_Azienda(cod_Azienda);
		this.setRagione_soc(ragione_soc);
		this.setP_IVA(p_IVA);
		this.setDescrizione(descrizione);
		this.setIndirizzo(indirizzo);
		this.setLocalita(localita);
		this.setCap(cap);
		this.setProv(prov);
	}

	public String getCod_Azienda() {
		return cod_Azienda;
	}

	public void setCod_Azienda(String cod_Azienda) {
		this.cod_Azienda = cod_Azienda;
	}

	public String getRagione_soc() {
		return ragione_soc;
	}

	public void setRagione_soc(String ragione_soc) {
		this.ragione_soc = ragione_soc;
	}

	public String getP_IVA() {
		return p_IVA;
	}

	public void setP_IVA(String p_IVA) {
		this.p_IVA = p_IVA;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getLocalita() {
		return localita;
	}

	public void setLocalita(String localita) {
		this.localita = localita;
	}

	public int getCap() {
		return cap;
	}

	public void setCap(int cap) {
		this.cap = cap;
	}

	public String getProv() {
		return prov;
	}

	public void setProv(String prov) {
		this.prov = prov;
	}
	
	

}
